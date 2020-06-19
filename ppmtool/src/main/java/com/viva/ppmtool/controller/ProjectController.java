package com.viva.ppmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
}
