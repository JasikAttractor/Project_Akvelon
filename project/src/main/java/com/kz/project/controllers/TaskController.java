package com.kz.project.controllers;

import com.kz.project.dto.ProjectDTO;
import com.kz.project.dto.TaskDTO;
import com.kz.project.model.Project;
import com.kz.project.model.Task;
import com.kz.project.model.TaskStatus;
import com.kz.project.service.ProjectService;
import com.kz.project.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@Api(description = "Контроллер взаимодействия с сущностью задач")
public class TaskController {
    private final TaskService service;
    private final ProjectService projectService;

    @GetMapping("/addTask/{id}")
    @ApiOperation("method to show page where we can add task to the project")
    public String addTask(Model model, Project project){
    model.addAttribute("project",project);
        return "/addTask";
    }
    @GetMapping("/tasks")
    public String alltasks(Model model){
        model.addAttribute("tasks",service.findAll());

        return "/tasks";
    }
    @PostMapping("/addTask/{id}")
    @ApiOperation("Post mapping to add task to the database")
    public String addTask(Model model, TaskDTO task, @ModelAttribute("project") ProjectDTO project,@PathVariable("id") long id){
        model.addAttribute("task",task);
        service.add(task, id);
        return "redirect:/";
    }
    @GetMapping("/editTask/{id}")
    @ApiOperation("Get mapping to show the page to edit task")
    public String edit(Model model, @PathVariable("id") long id){
        List<TaskStatus> status = Arrays.asList(TaskStatus.values());
        model.addAttribute("taskStatus",status);
        model.addAttribute("task",service.getOne(id));
        return "/editTask";
    }
    @PostMapping("/editTask/{id}")
    @ApiOperation("Post mapping to update values of task")
    public String update(@ModelAttribute("task") TaskDTO task, @PathVariable("id") long id){
        service.update(id,task);
        return "redirect:/";
    }
    @GetMapping("/showTask/{id}")
    public String show(Model model,@PathVariable("id") Long id){
        model.addAttribute("task",service.getOne(id));
        return "/showTask";
    }
    @GetMapping("/deleteTask/{id}")
    public String delete( @PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/";
    }
    @GetMapping("/createTask")
    public String createTask(Model model){
        var projects = projectService.findAll();
        for (var p:projects) {
            System.out.println(p);
        }
        model.addAttribute("projects",projects);
        return "/createTask";
    }
    @PostMapping("/createTask")
    public String createTask(TaskDTO task,String project){
        Long s = Long.parseLong(project);
        System.out.println(s);
        service.add(task, s);
        return "redirect:/";
    }
}
