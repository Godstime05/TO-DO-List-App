package com.godstime.toDoListApp.prompts;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.utils.DateCart;

import java.util.Scanner;

public class EditTask extends Mode {
    @Override
    public void showModeInfo() {
        System.out.println("===========================================================");
        System.out.println("To update a task, follow the instructions and press ENTER: ");
        System.out.println("com.godstime.toDoListApp.Task ID, com.godstime.toDoListApp.Task Title, Due Date (format: dd-mm-yyyy), com.godstime.toDoListApp.Task Status, Project Name");
        System.out.println("ID here represent the ID of the task where an update will occur");
        System.out.println("insert a (-) when an update is not needed to that specific parameter");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter 0 to RETURN");

    }

    @Override
    public String readUserInput() {
        while (true){
            System.out.println("");
            System.out.println("Enter Information:");

            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();

            if (!userInput.equals(0)){
                String[] parts = userInput.split(",");
                if (parts.length == 5){
                    boolean dateValidationRequired = true;
                    if (parts[2].equals("-")){
                        dateValidationRequired= false;
                    }
                    boolean isDateValid = true;
                    if (dateValidationRequired){
                        isDateValid = DateCart.isDateValid("dd-MM-yyyy", parts[2]);
                    }
                    if (isDateValid){
                        if(ToDoListController.tasks.get(parts[0]) != null){
                            return userInput;
                        }else {
                            System.out.println("ID doesn't exist, try again:");
                        }
                    }else {
                        System.out.println("Please follow instructions or enter 0 to RETURN");
                    }
                }else {
                    return userInput;
                }
            }
        }

    }

    @Override
    public void executeMode(String command) {
        String[] parts = command.split(",");
        boolean isTaskEdited = false;
        if (!parts[1].equals("-")){
            ToDoListController.tasks.get(parts[0]).setTitle(parts[1]);
            isTaskEdited=true;
        }
        if (!parts[2].equals("-")){
            ToDoListController.tasks.get(parts[0]).setDueDate(DateCart.parseDate("dd-MM-yyyy", parts[2]));;
            isTaskEdited=true;
        }

        if (!parts[3].equals("-")){
            ToDoListController.tasks.get(parts[0]).setStatus(parts[3]);
            isTaskEdited=true;
        }
        if (!parts[4].equals("-")){
            ToDoListController.tasks.get(parts[0]).setProjectName(parts[4]);
            isTaskEdited=true;
        }
        if (isTaskEdited){
            System.out.println("Tasks successfully updated!");
        }else {
            System.out.println("No change was applied....");
        }

    }
}
