package com.godstime.toDoListApp.prompts;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.models.Task;

import java.util.Scanner;

public class RemoveTask extends Mode {
    @Override
    public void showModeInfo() {

        System.out.println("==========================================");
        System.out.println("To remove a task, enter ID and press ENTER");
        System.out.println("------------------------------------------");
        System.out.println("Enter 0 to RETURN");
    }

    @Override
    public String readUserInput() {

        while (true) {
            System.out.println("");
            System.out.print("Enter ID: ");
            Scanner scan = new Scanner(System.in);
            try {
                String userInput = scan.nextLine();
                int userInputAsNum = Integer.parseInt(userInput);
                if (userInputAsNum != 0) {
                    Task task = ToDoListController.tasks.get(userInput);
                    if (task != null) {
                        return userInput;
                    } else {
                        System.out.println("ID doesn't exist, try another ID: ");
                    }
                } else {
                    return userInput;
                }

            } catch (Exception err) {
                System.out.println("Please enter a valid ID or 0 to RETURN");
            }

        }    }

    @Override
    public void executeMode(String command) {
        ToDoListController.tasks.remove(command);

        System.out.println("com.godstime.toDoListApp.Task with ID: " + command + ", was successfully removed...");


    }
}
