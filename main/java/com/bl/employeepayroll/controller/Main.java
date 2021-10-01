package com.bl.employeepayroll.controller;

import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.service.EmployeePayrollService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();

//      employeePayrollService.insertEmployee();
//        employeePayrollService.updateSalary();
        List <Employee> list = employeePayrollService.getAllEmployeeDetails();
        for (Employee item: list){
            System.out.println(item);
        }
    }
}
