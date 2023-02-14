package com.godstime.toDoListApp.utils;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.models.Task;
import com.godstime.toDoListApp.prompts.Mode;

import java.util.*;

public class ProjectCart extends Mode {
    @Override
    public void showModeInfo() {

        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    @Override
    public String readUserInput() {
        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    @Override
    public void executeMode(String command) {

        List<Map.Entry<String, Task>> inputs = new ArrayList<>(ToDoListController.tasks.entrySet());
        Collections.sort(inputs, new Comparator<Map.Entry<String, Task>>() {
            @Override
            public int compare(Map.Entry<String, Task> firstTask, Map.Entry<String, Task> secondTask) {
                String firstProject = firstTask.getValue().getProjectName();
                String secondProject = secondTask.getValue().getProjectName();

                return firstProject.compareTo(secondProject);
            }


        });

        ToDoListController.tasks.clear();
        inputs.forEach(entry -> {
            ToDoListController.tasks.put(entry.getKey(), entry.getValue());
        });

        System.out.println("Tasks successfully sorted");
    }
}
