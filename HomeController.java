package com.becoder.controller;
import com.becoder.entity.Employee;
import com.becoder.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public String index(Model m) {
        List<Employee> list = empService.getAllEmp();
        m.addAttribute("emplist", list);
        return "index";
    }
    
    @GetMapping("/loadEmpSave")
    public String loadEmpSave()
    {
		return "emp_save";
}

    @GetMapping("/EditEmp/{id}")
    public String EditEmp(@PathVariable int id, Model m) {
    	Employee emp= empService.getEmpById(id );
    	m.addAttribute("emp",emp);
        return "edit_emp";
    }
    	

    @PostMapping("/saveEmp")
    public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
        Employee newEmp = empService.saveEmp(emp);
        if (newEmp != null) {
            System.out.println("Register Successfully");
            session.setAttribute("msg", "Register Successfully");
        } else {
            System.out.println("Something went wrong on server");
            session.setAttribute("msg", "Something went wrong on server");
        }
        return "redirect:/";
    }
    
    @PostMapping("/updateEmpDtls")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
        Employee updateEmp = empService.saveEmp(emp);
        if (updateEmp != null) {
            System.out.println("Updated Successfully");
            session.setAttribute("msg", "Updated Successfully");
        } else {
            System.out.println("Something went wrong on server");
            session.setAttribute("msg", "Something went wrong on server");
        }
        return "redirect:/";
    }

    
    @GetMapping("/deleteEmp/{id}")
    public String loadEmpSave(@PathVariable int id,HttpSession session){
    boolean f= empService.deleteEmp(id);
    if(f) {
    session.setAttribute("msg","Delete Sucessfully");

    }
    else
    	 {
    session.setAttribute("msg", "Something went wrong on server");

    }
    return "redirect:/";

}
}
