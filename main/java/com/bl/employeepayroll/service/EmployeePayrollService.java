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

}
