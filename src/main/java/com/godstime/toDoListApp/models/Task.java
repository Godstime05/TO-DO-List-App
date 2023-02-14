package com.godstime.toDoListApp.models;

import com.godstime.toDoListApp.utils.DateCart;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Task {
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    private String title;
    private LocalDate dueDate;
    private String status;
    private String projectName;
    private String priority;
    private String description;


    public static Task buildTask(String id, String title, LocalDate dueDate, String status, String projectName) {
        Task task = new Task();

        task.setId(id);
        task.setTitle(title);
        task.setDueDate(dueDate);
        task.setStatus(status);
        task.setProjectName(projectName);

        return task;
    }

    @Override
    public String toString(){
        return id +"," + title + "," + DateCart.convertDateToString(dueDate,"dd-MM-yyyy") + "," + status+ ","+projectName;
    }

}
