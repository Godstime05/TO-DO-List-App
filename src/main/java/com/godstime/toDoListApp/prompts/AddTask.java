package com.godstime.toDoListApp.prompts;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.models.Task;
import com.godstime.toDoListApp.utils.DateCart;

import java.util.Scanner;

public class AddTask extends Mode {
    @Override
    public void showModeInfo() {
        System.out.println("===============================================");
        System.out.println("To add a new task, please follow the instructions and press ENTER:");
        System.out.println("com.godstime.TodoListApp.Task Name, Due Date (format: dd-mm-yyyy), Task Title");
        System.out.println("-----------------------------------------------");
        System.out.println("Enter 0 to RETURN");

    }

    @Override
    public String readUserInput() {
        while (true){
            System.out.println("");
            System.out.print("Enter Information: ");
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();

            if (!userInput.equals("0")){
                String[] parts = userInput.split(",");
                if (parts.length == 5){
                    if (DateCart.isDateValid("dd-MM-yyyy", parts[2])){
                        if (ToDoListController.tasks.get(parts[0]) == null){
                            return userInput;
                    }else {
                        System.out.println("A task with this ID already exist, try again: ");
                    }
                }else {
                    System.out.println("The date you entered is invalid, pls try again: ");
                }
            } else {
                System.out.println("Please follow the Instructions, try Again: ");
            }
            }  else {
                return userInput;
            }
        }
    }

    @Override
    public void executeMode(String command) {
        String[] parts = command.split(",");
        Task task = Task.buildTask(parts[0], parts[1], DateCart.parseDate("dd-MM-yyyy", parts[2]),
                parts[3], parts[4]);
        ToDoListController.tasks.put(task.getId(), task);
        System.out.println("com.godstime.toDoListApp.Task Successfully added!");

    }
}
