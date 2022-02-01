package com.example.testing.dao;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.testing.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestIEmployeeDAO {
	@Autowired
	private IEmployeeDAO employeeRepository;

	@Test
	void grivenEmployeeObject_whenSave_thenReturnSavedEmployee() {
		//given -precondition or setup
		Employee employee=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		//when-behavior or action
		Employee savedEmployee=employeeRepository.save(employee);
		
		//then-verify the output
		Assertions.assertThat(savedEmployee).isNotNull();
		Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("Junit test For get All Employee operations")
	void givenEmployeeList_whenFindAll_thenEmployeeList() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		Employee employee2=Employee.builder()
				.firstName("Sunil")
				.lastName("Ghuge")
				.email("Sunilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		//when-behavior or action
		List<Employee> employeeList=employeeRepository.findAll();
		
		//then-verify the output
		Assertions.assertThat(employeeList).isNotNull();
		Assertions.assertThat(employeeList.size()).isEqualTo(2);
	}	
	
	@Test
	@DisplayName("Junit test for get Employee by id operation")
	void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		Employee employeeDB=employeeRepository.findById(employee1.getId()).get();
		//then-verify the output
		Assertions.assertThat(employeeDB).isNotNull();		
	}
	
	@Test
	@DisplayName("Junit test for get Employee by Email")
	void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		Employee employeeDB=employeeRepository.findByEmail(employee1.getEmail()).get();
		//then-verify the output
		Assertions.assertThat(employeeDB).isNotNull();		
	}
	
	@Test
	@DisplayName("Junit Test for update employee operation")
	void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		Employee savedEmployee=employeeRepository.findById(employee1.getId()).get();
		savedEmployee.setEmail("anil.ghuge@vinsys.com");
		Employee updatedEmployee=employeeRepository.save(savedEmployee);
		//then-verify the output
		Assertions.assertThat(updatedEmployee).isNotNull();
		Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("anil.ghuge@vinsys.com");
	}
	
	@Test
	@DisplayName("Junit Test For Delete Employee operation")
	void givenEmployeeObject_whenDeleteEmployeeObject_thenRemoveEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		employeeRepository.delete(employee1);
		Optional<Employee> employeeDB=employeeRepository.findById(employee1.getId());
		//then-verify the output
		Assertions.assertThat(employeeDB).isEmpty();		
	}
	@Test
	@DisplayName("Junit Test for Custom Query using jpql index")
	void givenFirstNameAndLastName_whenFindByJPQL_thenEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		Employee savedEmployee=employeeRepository.findByJPQL(employee1.getFirstName(),employee1.getLastName());
		//then-verify the output
		Assertions.assertThat(savedEmployee).isNotNull();
	}
	
	@Test
	@DisplayName("Junit Test for Custom Query using Native Sql With index param")
	void givenFirstNameAndLastName_whenFindByNativeQuery_thenEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		Employee savedEmployee=employeeRepository.findByNativeSQL(employee1.getFirstName(),employee1.getLastName());
		//then-verify the output
		Assertions.assertThat(savedEmployee).isNotNull();
	}
	
	@Test
	@DisplayName("Junit Test for Custom Query using Named Sql With index param")
	void givenFirstNameAndLastName_whenFindByNamedQuery_thenEmployeeObject() {
		//given -precondition or setup
		Employee employee1=Employee.builder()
				.firstName("Anil")
				.lastName("Ghuge")
				.email("anilghuge@gmail.com")
				.build();
		employeeRepository.save(employee1);
		//when-behavior or action
		Employee savedEmployee=employeeRepository.findByJPQLNamed(employee1.getFirstName(),employee1.getLastName());
		//then-verify the output
		Assertions.assertThat(savedEmployee).isNotNull();
	}
}
