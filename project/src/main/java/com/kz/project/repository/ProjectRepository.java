package com.kz.project.repository;

import com.kz.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
List<Project> findAll();
Project findByName(String name);
}
