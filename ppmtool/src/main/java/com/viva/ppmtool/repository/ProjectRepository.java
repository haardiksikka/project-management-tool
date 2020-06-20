package com.viva.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viva.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Integer> {

	
	Project findByProjectIdentifier(String projectId);
}
