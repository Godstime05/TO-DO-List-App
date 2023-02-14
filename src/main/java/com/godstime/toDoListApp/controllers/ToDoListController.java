package com.godstime.toDoListApp.controllers;

import com.godstime.toDoListApp.models.Task;
import com.godstime.toDoListApp.prompts.*;
import com.godstime.toDoListApp.utils.DateCart;
import com.godstime.toDoListApp.utils.ProjectCart;
import com.godstime.toDoListApp.utils.ReadFromFile;
import com.godstime.toDoListApp.utils.SaveTasks;

import java.util.*;

public class ToDoListController {
    public static Map<String, Task>tasks = new LinkedHashMap<>();
    public static boolean appRunning = true;

    public void startApp(){
        showAppTitle();
        while (ToDoListController.appRunning){
            DisplayMode();
            int actionNum = readAction();
            executeAction(actionNum);
        }
    }

    private void executeAction(int actionNum) {
        Mode mode;
        switch (actionNum){
            case Mode.ADD_TASK:
                mode = new AddTask();
                mode.showModeInfo();
                String command = mode.readUserInput();
                if (!command.equals("0")) mode.executeMode(command);
                break;

            case Mode.MARK_AS_DONE:
                if (tasks.size()>0){
                    mode = new MarkTaskAsDone();
                    mode.showModeInfo();
                    String id = mode.readUserInput();
                    if (!id.equals("0")) mode.executeMode(id);
                }
                else {
                    System.out.println("Your TODO List is empty, add tasks first!");

                }
                break;
            case Mode.REMOVE_TASK:
                if (tasks.size() > 0) {
                    mode = new RemoveTask();
                    mode.showModeInfo();
                    String id = mode.readUserInput();
                    if (!id.equals("0")) mode.executeMode(id);
                } else {
                    System.out.println("Your list is empty, add tasks first! ");
                }
                break;

            case Mode.EDIT_TASK:
                if (tasks.size() > 0) {
                    mode = new EditTask();
                    mode.showModeInfo();
                    String editCommand = mode.readUserInput();
                    if (!editCommand.equals(0))
                        mode.executeMode(editCommand);
                } else {
                    System.out.println("Your list is empty, add tasks first! ");
                }
                break;

            case Mode.DISPLAY_ALL_TASKS:
                if (tasks.size() > 0) {
                    mode = new DisplayTasks();
                    mode.showModeInfo();
                    mode.executeMode(null);
                } else {
                    System.out.println("Your list is empty, add tasks first! ");
                }
                break;

            case Mode.SORT_TASKS_BY_DATE:
                mode = new DateCart();
                mode.executeMode(null);
                break;

            case Mode.SORT_TASKS_PROJECT:
                mode = new ProjectCart();
                mode.executeMode(null);
                break;

            case Mode.SAVE_TASKS_TO_FILE:
                if (tasks.size() > 0) {

                    mode = new SaveTasks();
                    mode.showModeInfo();
                    String path = mode.readUserInput();
                    if (!path.equals("0"))
                        mode.executeMode(path);
                } else {
                    System.out.println("There are no tasks to be saved!");
                }
                break;

            case Mode.READ_FROM_FILE:
                mode = new ReadFromFile();
                mode.showModeInfo();
                String path = mode.readUserInput();
                if (!path.equals("0"))
                    mode.executeMode(path);
                break;

            case Mode.EXIT:
                appRunning = false;
                break;

        }
    }

    private int readAction() {
        List<Integer>availableMode = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        while (true){
            try {
                System.out.println("ENTER MODE NUMBER");
                Scanner scanner = new Scanner(System.in);
                int modeNum = scanner.nextInt();

                if (availableMode.contains(modeNum)){
                    return modeNum;
                }else {
                    System.out.println("Please enter a valid mode_Number from the list: ");

                }
            }catch (Exception e){
                System.out.println("Mode_Number must be a number ");
            }
        }
    }

    private void DisplayMode() {
        System.out.println("======================================");
        System.out.println("1. ADD A TASK");
        System.out.println("2. MARK TASK AS DONE");
        System.out.println("3. REMOVE TASK");
        System.out.println("4. EDIT TASK");
        System.out.println("5. DISPLAY ALL TASKS");
        System.out.println("6. SORT TASKS BY DATE");
        System.out.println("7. SORT TASK BY DESCRIPTION");
        System.out.println("8. SAVE TASKS TO FILE");
        System.out.println("9. READ FROM FILE");
        System.out.println("10. EXIT");
        System.out.println("======================================");
    }

    private void showAppTitle() {
        System.out.println("=================WELCOME TO GODSTIME=====================");
        System.out.println("TO DO LIST APPLICATION");

    }
}
