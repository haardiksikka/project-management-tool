package com.viva.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.viva.ppmtool.domain.Backlog;
import com.viva.ppmtool.domain.Project;
import com.viva.ppmtool.exception.ProjectIdException;
import com.viva.ppmtool.repository.BacklogRepository;
import com.viva.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	
	@CachePut(value = "projectDetailsCache", key = "#result.projectIdentifier")
	public Project saveOrUpdateProject(Project project) {
		
		try {
			
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			 if(project.getId()==null){
	                Backlog backlog = new Backlog();
	                project.setBacklog(backlog);
	                backlog.setProject(project);
	                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
	            }

	            if(project.getId()!=null){
	                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
	            }
		return projectRepository.save(project);
		}
		catch(Exception e) {
			throw new ProjectIdException("Project Id: "+project.getProjectIdentifier().toUpperCase()+" is already defined");
		}
	}
	
	@Cacheable(value = "projectDetailsCache", key= "#projectIdentifier")
	public Project findProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Porject Id: "+projectIdentifier.toUpperCase()+" does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAll(){
		return projectRepository.findAll();
	}
	
	@CacheEvict(value = "projectDetailsCache")
	public void deleteProject(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project with id "+projectIdentifier.toUpperCase()+"does not exist");
		}
		
		projectRepository.delete(project);
	}

}
