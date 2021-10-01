package com.bl.employeepayroll.service;

import com.bl.employeepayroll.config.DBConfig;
import com.bl.employeepayroll.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeePayrollService {
    Scanner scanner = new Scanner( System.in );
    Connection con = DBConfig.getConnection();
    PreparedStatement preparedStatement;
    public List<Employee> getAllEmployeeDetails() {
        Connection con = DBConfig.getConnection();

        String SELECT_QUERY = "SELECT * FROM employee_payroll";
            Employee employee = null;
            List<Employee> employeePayrollList = new ArrayList <>(  );
        try {
            preparedStatement = con.prepareStatement( SELECT_QUERY );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getInt( "ID" ) );
                employee.setName(resultSet.getString( 2 ) );
                employee.setGender(resultSet.getString( 3 ) );
                employee.setSalary(resultSet.getInt( 4 ) );
                employee.setDate(resultSet.getDate( 5 ).toString() );
                employee.setEmpPhone(resultSet.getString( 6 ) );
                employee.setDepartment(resultSet.getString( 7 ) );
                employee.setAddress(resultSet.getString( 8 ) );
                employee.setBasicPay(resultSet.getInt( 9 ) );
                employee.setDeductions(resultSet.getInt( 10 ) );
                employee.setTaxablePay(resultSet.getInt( 11 ) );
                employee.setIncomeTax(resultSet.getInt( 12 ) );
                employee.setNetPay(resultSet.getInt( 13 ) );

                employeePayrollList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        }
        return employeePayrollList;
    }

    public Employee getEmployeeDetailsByID( int id){
        List <Employee> list = getAllEmployeeDetails();
        Employee employee = list.stream().filter( x-> x.getId() == id ).findAny().orElse( null );
        return employee;
    }

    public void insertEmployee(){
        String INSERT_QUERY = " INSERT into employee_payroll (NAME,GENDER,SALARY,START_DATE,EMP_PHONE,DEPARTMENT,ADDRESS,BASIC_PAY,DEDUCTIONS,TAXABLE_PAY,INCOME_TAX,NET_PAY) VALUES (\"Suresh\",\"MALE\",20000,\"2014-05-23\",\"7645486425\",\"SALES\",\"Bangalore\", 15000,1000,18000,500,14000)";
        try {
            preparedStatement = con.prepareStatement( INSERT_QUERY );
            int i = preparedStatement.executeUpdate();
            System.out.println("Inserted "+i+" row" );
        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }
}
