package com.godstime.toDoListApp.utils;

import com.godstime.toDoListApp.controllers.ToDoListController;
import com.godstime.toDoListApp.models.Task;
import com.godstime.toDoListApp.prompts.Mode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateCart extends Mode {
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
            public int compare(Map.Entry<String, Task> task1, Map.Entry<String, Task> task2) {
                LocalDate dueDateFirstTask = task1.getValue().getDueDate();
                LocalDate dueDateSecondTask = task2.getValue().getDueDate();
                int result = dueDateFirstTask.compareTo(dueDateSecondTask);
                return result;
            }
        });

        ToDoListController.tasks.clear();
        inputs.forEach((input) ->{
            ToDoListController.tasks.put(input.getKey(), input.getValue());
        });
        System.out.println("Tasks successfully Sorted!");

    }

    public static boolean isDateValid(String format, String value){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate localDate = LocalDate.parse(value, dateTimeFormatter);
            String result = localDate.format(dateTimeFormatter);
            return  result.equals(value);
        }catch (DateTimeParseException e){

        }
        return false;
    }

    public static String convertDateToString(LocalDate date, String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String result = null;

        try {
            result= date.format(dateTimeFormatter);
        }catch (DateTimeParseException e){

        }
        return result;
    }

    public static LocalDate parseDate(String format, String value){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(value, dateTimeFormatter);
    }

    }
