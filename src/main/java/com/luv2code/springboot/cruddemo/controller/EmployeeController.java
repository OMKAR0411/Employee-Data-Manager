package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theemployeeService){
        employeeService = theemployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model themodel){
        List<Employee> theEmployees=employeeService.findAll();

        themodel.addAttribute("employees",theEmployees);
        return "employees/list-employees";
    }
    @GetMapping("showForm")
    public String showForm(Model model){
        Employee theemployee=new Employee();
        model.addAttribute("employee",theemployee);
        return "/employees/employees-form";
    }

    @PostMapping("/save")
    public  String SaveEmployee(@ModelAttribute("employee") Employee theemployee){

        employeeService.save(theemployee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showForUpdate")
    public String showForUpdate(@RequestParam("employeeId") int theId,Model theModel){
        Employee theemployee=employeeService.findById(theId);
        theModel.addAttribute("employee",theemployee);
        return "employees/employees-form";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId")int theid){
        employeeService .deleteById(theid);
        return "redirect:/employees/list";
    }
}
