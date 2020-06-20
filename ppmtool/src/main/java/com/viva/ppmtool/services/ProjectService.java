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

}
