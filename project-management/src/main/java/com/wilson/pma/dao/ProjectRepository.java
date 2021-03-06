package com.wilson.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wilson.pma.dto.ChartData;
import com.wilson.pma.dto.TimeChartData;
import com.wilson.pma.entities.Project;

// dao -> data access object
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	// CrudRepository<Project, Long> first argument is object entity, second argument is data type of an unique id entity
	
	// karena kita ingin membuat type List di HomeController dengan instance dr interface ini
	// maka kita override findAll() dgn tipe List<> sehingga kita
	// bs menggunakan instance dr ProjectRepository di assign ke List<>
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> getProjectStatus();
	
	// pastikan datanya kita samakan dengan define variable pada entity Project -> projectName, startDate, endDate
	@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate"
			+ " FROM project WHERE start_date is not null")
	public List<TimeChartData> getTimeData();
}
