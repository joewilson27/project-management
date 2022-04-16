package com.wilson.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.wilson.pma.entities.Project;

// dao -> data access object
public interface ProjectRepository extends CrudRepository<Project, Long> {
	// CrudRepository<Project, Long> first argument is object entity, second argument is data type of an unique id entity
}
