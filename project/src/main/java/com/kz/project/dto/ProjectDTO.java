package com.kz.project.dto;

import com.kz.project.model.Project;
import com.kz.project.model.ProjectStatus;
import com.kz.project.model.Task;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProjectDTO {
   @Id
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate startDate;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Column
    private ProjectStatus projectStatus;
    @Column
    private int priority;
    @JoinColumn(name="projectID")
    private List<Task> tasks;

 public static ProjectDTO from(Project project){
  return ProjectDTO.builder()
          .id(project.getId())
          .name(project.getName())
          .startDate(LocalDate.now())
          .endDate(project.getEndDate())
          .projectStatus(project.getProjectStatus())
          .priority(project.getPriority())
          .tasks(project.getTasks())
          .build();
 }
}
