package com.kz.project.dto;

import com.kz.project.model.Project;
import com.kz.project.model.Task;
import com.kz.project.model.TaskStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TaskDTO {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private TaskStatus status;
    @Column
    private String description;
    @Column
    private int priority;
    @Column
    private Long projectID;


    public static TaskDTO from(Task task){
        return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .status(task.getStatus())
                .description(task.getDescription())
                .priority(task.getPriority())
                .projectID(task.getProjectID())
                .build();
    }
}
