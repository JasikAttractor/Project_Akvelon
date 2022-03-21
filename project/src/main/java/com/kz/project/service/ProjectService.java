package com.kz.project.service;

import com.kz.project.dto.ProjectDTO;
import com.kz.project.exceptions.ResourceNotFoundException;
import com.kz.project.model.Project;
import com.kz.project.model.ProjectStatus;
import com.kz.project.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
private final ProjectRepository repository;
    public List<Project> findAll(){
        return repository.findAll();
    }
    public ProjectDTO add(ProjectDTO projectDTO){
        Project project = Project.builder()
                .name(projectDTO.getName())
                .startDate(LocalDate.now())
                .endDate(projectDTO.getEndDate())
                .priority(projectDTO.getPriority())
                .projectStatus(ProjectStatus.NotStarted)
                .build();
        repository.save(project);
        return ProjectDTO.from(project);
    }

    public Project getOne(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", id));
    }

    public ProjectDTO update(Long id, ProjectDTO projectDTO){
        Project project = getOne(id);
        project.setName(projectDTO.getName());
        project.setProjectStatus(projectDTO.getProjectStatus());
        project.setPriority(projectDTO.getPriority());
        project.setEndDate(projectDTO.getEndDate());
        project.setTasks(projectDTO.getTasks());
        repository.save(project);
        return ProjectDTO.from(project);
    }
    public ProjectDTO updateByProject(Long id, Project updatedProject){
        Project currentProject = getOne(id);
        currentProject.setName(updatedProject.getName());
        currentProject.setProjectStatus(updatedProject.getProjectStatus());
        currentProject.setPriority(updatedProject.getPriority());
        currentProject.setEndDate(updatedProject.getEndDate());
        currentProject.setTasks(updatedProject.getTasks());
        repository.save(currentProject);
        return ProjectDTO.from(currentProject);
    }
    public String delete(Long id){
        Project project = getOne(id);
        repository.delete(project);
        return "redirect:/";
    }

}
