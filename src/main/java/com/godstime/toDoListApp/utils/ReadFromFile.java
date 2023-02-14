package com.godstime.toDoListApp.utils;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.models.Task;
import com.godstime.toDoListApp.prompts.Mode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile extends Mode {
    @Override
    public void showModeInfo() {
        System.out.println("========================================");
        System.out.println("Please enter path to read: ");
        System.out.println("----------------------------------------");
        System.out.println("Enter 0 to return");


    }

    @Override
    public String readUserInput() {
        while (true){
            System.out.println("========================================");
            System.out.println("path: ");

            Scanner scanner = new Scanner(System.in);

            return scanner.nextLine();

        }
    }

    @Override
    public void executeMode(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()){
                String file = scanner.nextLine();
                String[] parts = file.split(",");

                Task task = Task.buildTask(parts[0], parts[1], DateCart.parseDate("dd-MM-yyyy",
                        parts[2]),parts[3], parts[4]);
                if (ToDoListController.tasks.get(parts[0]) != null){
                    ToDoListController.tasks.replace(parts[0], task);
                }else {
                    ToDoListController.tasks.put(parts[0], task);
                }
            }
            scanner.close();
            System.out.println("Tasks are being read!");
        }catch (FileNotFoundException e){
            System.out.println("Path or file do not exist.");
        }

    }
}
