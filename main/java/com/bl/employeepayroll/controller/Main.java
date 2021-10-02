package com.bl.employeepayroll.controller;

import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.service.EmployeePayrollService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Scanner scanner = new Scanner( System.in );
        System.out.println("Welcome to Employee Payroll Service");
       int EXIT = 4;
       int choice = 0;
        while (choice != EXIT){
        System.out.println("\nEnter your choice" );
        System.out.println("1. View Data" );
        System.out.println("2. Insert into Employee Payroll Service" );
        System.out.println("3. Update Salary" );
        choice = scanner.nextInt();
            switch (choice){
                case 1:
                    List <Employee> list = employeePayrollService.getAllEmployeeDetails();
                    for (Employee item: list){
                        System.out.println(item);
                    }
                    break;
                case 2:
                    employeePayrollService.insertEmployee();
                    break;
                case 3:
                    employeePayrollService.updateSalary();
                    break;

            }
        }
    }
}
