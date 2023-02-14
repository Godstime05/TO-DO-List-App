package com.godstime.toDoListApp;

import com.godstime.toDoListApp.controllers.ToDoListController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListAppApplication.class, args);


		ToDoListController toDoList = new ToDoListController();

		toDoList.startApp();
	}

}
