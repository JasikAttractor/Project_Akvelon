package com.kz.project.controllers;

import com.kz.project.dto.ProjectDTO;
import com.kz.project.model.Project;
import com.kz.project.model.ProjectStatus;
import com.kz.project.model.Task;
import com.kz.project.service.ProjectService;
import com.kz.project.service.TaskService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@Api(description = "Контроллер взаимодействия с сущностью проекта")
public class ProjectController {
    private final ProjectService service;
    private final TaskService taskService;
    @GetMapping
    public String index(Model model){
        List<Project> projects = service.findAll();
        model.addAttribute("projects", projects);
        return "/index";
    }
    @GetMapping("/addProject")
    public String addProject(){
        return "/addProject";
    }

    @PostMapping("/addProject")
    public String addProject(Model model, ProjectDTO project){
        model.addAttribute("project",project);
        service.add(project);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id){
        List<ProjectStatus> status = Arrays.asList(ProjectStatus.values());
        model.addAttribute("projectStatus",status);
        model.addAttribute("project",service.getOne(id));

        return "/edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("project") ProjectDTO project, @PathVariable("id") long id){
        service.update(id,project);
        return "redirect:/";
    }
    @GetMapping("/show/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        List<Task> tasks = taskService.findByProjectId(id);
        model.addAttribute("tasks",tasks);
        model.addAttribute("project",service.getOne(id));
        return "/show";
    }
    @GetMapping("/delete/{id}")
    public String delete( @PathVariable("id") Long id){
        var tasks = taskService.findByProjectId(id);
        for (var task:tasks) {
            taskService.delete(task.getId());
        }
        service.delete(id);
       return "redirect:/";
    }
}
