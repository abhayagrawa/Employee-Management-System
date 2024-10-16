package com.becoder.repository;

import java.util.List;
import com.becoder.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee,Integer> {

}
