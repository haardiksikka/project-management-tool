package com.viva.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.ppmtool.domain.Project;
import com.viva.ppmtool.exception.ProjectIdException;
import com.viva.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		return projectRepository.save(project);
		}
		catch(Exception e) {
			throw new ProjectIdException("Project Id: "+project.getProjectIdentifier().toUpperCase()+" is already defined");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Porject Id: "+projectId.toUpperCase()+" does not exist");
		}
		return project;
	}

}
