package com.becoder.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.becoder.entity.Employee;
import com.becoder.repository.EmpRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpRepository empRepo;

    @Override
    public Employee saveEmp(Employee emp) {
        Employee newEmp = empRepo.save(emp);
        return newEmp;
	}

	@Override
	public List<Employee> getAllEmp() {
		return empRepo.findAll();
	}
	@Override
	public Employee getEmpById(int id) {
	 return empRepo.findById(id).get();
	}

	@Override
	public boolean deleteEmp(int id) {
	Employee emp= empRepo.findById(id).get();
	if(emp!=null)
	{
		empRepo.delete(emp);
		return true;
	}
		return false;
	}

	public void removeSessionMessage() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    if (attr != null) {
	        HttpSession session = attr.getRequest().getSession(false);
	        if (session != null) {
	            session.removeAttribute("msg");
	        } else {
	            System.out.println("No session exists.");
	        }
	    } else {
	        System.out.println("No current request context.");
	    }
	}	

}
