package com.acme.roombookingapi.repository;

import com.acme.roombookingapi.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  boolean existsByEmail(String email);

  Optional<Employee> findByEmailIgnoreCase(String email);
}
