package com.tw.exercice.api.ppmtool.services;

import com.tw.exercice.api.ppmtool.domain.Project;
import com.tw.exercice.api.ppmtool.exceptions.ProjectIdException;
import com.tw.exercice.api.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exists");
        }

        return project;
    }

    public Iterable<Project> findAll(){

        Iterable<Project> listProjects = projectRepository.findAll();
        if(listProjects == null){
            throw new ProjectIdException("Não existem projetos cadastrados");
        }
        return listProjects;
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException("Canot delete project with ID '"+projectId.toUpperCase()+"'. This project does not exists");
        }
        projectRepository.delete(project);
    }

}
