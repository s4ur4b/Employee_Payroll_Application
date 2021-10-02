package com.bl.employeepayroll.service;

import com.bl.employeepayroll.config.DBConfig;
import com.bl.employeepayroll.exception.EmployeePayrollException;
import com.bl.employeepayroll.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void updateSalary(){
        String UPDATE_QUERY = "UPDATE employee_payroll set SALARY=? where ID=?";
        try {
            preparedStatement = con.prepareStatement( UPDATE_QUERY );
            System.out.println("Enter Salary of Employee" );
            int salary = scanner.nextInt();
            System.out.println("Enter ID of Employee" );
            int id = scanner.nextInt();
            preparedStatement.setInt( 1,salary );
            preparedStatement.setInt( 2,id );
            int i = preparedStatement.executeUpdate();
            System.out.println("Updated "+i+" Emplyee data" );
        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }

    public void updateEmployeeSalary(String name, int salary) throws EmployeePayrollException {
        String UPDATE_QUERY = "UPDATE employee_payroll set SALARY=? where NAME=?";
        try {
            preparedStatement = con.prepareStatement( UPDATE_QUERY );
            preparedStatement.setInt( 1,salary );
            preparedStatement.setString( 2,name );
            int i = preparedStatement.executeUpdate();
            System.out.println("Updated "+i+" Emplyee data" );
        } catch (SQLException e) {
            throw new EmployeePayrollException("Please check the getEmployeePayrollData(name) for detailed information!");
        }
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

    public int getSalaryFromList(int id){
        AtomicInteger salary = null;

        List<Employee> list = getAllEmployeeDetails();
        for (Employee item: list){
            if (item.getId() == id){
                 salary = new AtomicInteger(item.getSalary());
            }
        }
        int salaryCheck = salary.intValue();
        return salaryCheck;
    }

    public int checkSalaryFromDatabase(String name) {
        List <Employee> employeePayrollData = getAllEmployeeDetails( );
        int salary = 0;
        for (Employee items : employeePayrollData) {
            if (items.getName( ).equals( name )) {
                salary = items.getSalary( );
            }
        }
        return salary;
    }

    public List<Employee> getEmployeePayrollData(String name) throws EmployeePayrollException {
        List<Employee> employeePayrollList = null;

        try {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeePayrollList = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            throw new EmployeePayrollException("Please check the getEmployeePayrollData(name) for detailed information!");
        }
        return employeePayrollList;
    }

    private List<Employee> getEmployeePayrollData(ResultSet resultSet) throws EmployeePayrollException {
        List<Employee> employeePayrollList = new ArrayList<>();
        Employee employee = null;
        try {
            while(resultSet.next()) {
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
            throw new EmployeePayrollException("Please check the getEmployeePayrollData(resultSet) for detailed information!");
        }
        return employeePayrollList;
    }
    public boolean checkEmployeePayrollInSyncWithDB(String name) throws EmployeePayrollException {
        List<Employee> employeePayrollDataList = getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }
}
