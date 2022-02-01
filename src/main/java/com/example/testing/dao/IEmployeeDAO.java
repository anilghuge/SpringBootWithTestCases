package com.example.testing.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.testing.model.Employee;

public interface IEmployeeDAO extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);
	
	//define custom query using jpql with index param
	@Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
	Employee findByJPQL(String firstName,String secondName);
	
	//custom query using jpql sql with named param
	@Query(value="select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
	Employee findByJPQLNamed(@Param("firstName") String firstName,@Param("lastName") String lastName);
	
	//custom query using native sql with index param
	@Query(value="select * from tbl_mst_employee e where e.first_name=?1 and e.last_name=?2",nativeQuery=true)
	Employee findByNativeSQL(String firstName,String lastName);
	
	
	//custom query using native sql with named param
	@Query(value="select * from tbl_mst_employee e where e.first_name=:firstName and e.last_name=:lastName",nativeQuery=true)
	Employee findByNativeNamed(@Param("firstName") String firstName,@Param("lastName") String lastName);
	
	
}
