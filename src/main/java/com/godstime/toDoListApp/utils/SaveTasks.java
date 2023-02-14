package com.godstime.toDoListApp.utils;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.prompts.Mode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SaveTasks extends Mode {
    @Override
    public void showModeInfo() {
        System.out.println("==========================");
        System.out.println("Please enter path to file:");

        System.out.println("--------------------------");
        System.out.println("Enter 0 to RETURN");

    }

    @Override
    public String readUserInput() {
        while (true) {
            System.out.println("");
            System.out.print("path:");

            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();

            return userInput;

        }
    }

    @Override
    public void executeMode(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(path));

            List<String> lines = ToDoListController.tasks.entrySet().stream().map(entry -> entry.getValue().toString()).collect(Collectors.toList());

            lines.forEach(pw::println);
            pw.close();
            System.out.println("task successfully saved into file: " + path);
        } catch (FileNotFoundException e) {
            System.out.println("Path or file do not exist...");
        }

    }
}
