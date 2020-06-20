package com.viva.ppmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viva.ppmtool.domain.Project;
import com.viva.ppmtool.services.ProjectService;
import com.viva.ppmtool.services.ValidationMapService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired 
	private ProjectService projectService;
	
	@Autowired
	private ValidationMapService validationMapService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> errorMap = validationMapService.getErrorMap(result);
		if(errorMap!=null) {
			return errorMap;
		}
		
		return new ResponseEntity<Project>(projectService.saveOrUpdateProject(project),HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectId){
		return new ResponseEntity<Project>(projectService.findProjectByIdentifier(projectId), HttpStatus.OK);
	}
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAll();
	}
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProject(projectId);
		
		return new ResponseEntity<String>("Project deleted successfully", HttpStatus.OK);
	}
	
//	@PostMapping("/updateProject")
//	public  ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result){
//		ResponseEntity<?> errorMap = validationMapService.getErrorMap(result);
//		if(errorMap != null) {
//			return errorMap;
//		}
//		
//		return new ResponseEntity<Project>(projectService.saveOrUpdateProject(project),HttpStatus.OK);
//	}
	
}
