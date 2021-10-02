package com.bl.employeepayroll;

import com.bl.employeepayroll.exception.EmployeePayrollException;
import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.service.EmployeePayrollService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PayrollServiceTesting {
    EmployeePayrollService employeePayrollService = new EmployeePayrollService();
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
        employeePayrollService.updateEmployeeSalary( "Terisa" , 300000 );
        int salary = employeePayrollService.checkSalaryFromDatabase( "Terisa" );
        Assert.assertEquals( 300000 , salary );
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws EmployeePayrollException {
        List<Employee> employeePayrollData = employeePayrollService.getAllEmployeeDetails();
        Assert.assertEquals(11, employeePayrollData.size());
    }
}
