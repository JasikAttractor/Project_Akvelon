package com.kz.project.service;

import com.kz.project.dto.ProjectDTO;
import com.kz.project.dto.TaskDTO;
import com.kz.project.exceptions.ResourceNotFoundException;
import com.kz.project.model.Project;
import com.kz.project.model.ProjectStatus;
import com.kz.project.model.Task;
import com.kz.project.model.TaskStatus;
import com.kz.project.repository.ProjectRepository;
import com.kz.project.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    public List<Task> findAll(){
        return repository.findAll();
    }
    public TaskDTO add(TaskDTO taskDTO,Long id){
        Task task = Task.builder()
                .name(taskDTO.getName())
                .status(TaskStatus.ToDo)
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .projectID(id)
                .build();
        repository.save(task);
        return TaskDTO.from(task);
    }

    public Task getOne(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("task", id));
    }
    public List<Task> findByProjectId(Long id){
       List<Task> projectTasks = repository.findAllByProjectID(id);
        return projectTasks;
    }

    public TaskDTO update(Long id, TaskDTO taskDTO){
        Task task = getOne(id);
        task.setName(taskDTO.getName());
        task.setStatus(taskDTO.getStatus());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        repository.save(task);
        return TaskDTO.from(task);
    }
    public String delete(Long id){
        Task task = getOne(id);
        repository.delete(task);
        return "redirect:/";
    }
}
