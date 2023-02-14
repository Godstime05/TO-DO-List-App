package com.godstime.toDoListApp.prompts;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.utils.DateCart;

public class DisplayTasks extends Mode {
    @Override
    public void showModeInfo() {

        System.out.println("");
        System.out.println("HERE ARE ALL THE TASKS: ");

    }

    @Override
    public String readUserInput() {

        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    @Override
    public void executeMode(String command) {
        ToDoListController.tasks.forEach((key, task) -> {
            System.out.println("ID: " + key + ", Title: " + task.getTitle() + ", Due Date: "
                    + DateCart.convertDateToString(task.getDueDate(), "dd-MM-yyyy") + ", Status: "
                    + task.getStatus() + ", Project: " + task.getProjectName());
        });

    }
}
