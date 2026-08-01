////////////////////////////////////////practical 5a////////////////////////////////////////////////////////////////////////
//index.jsp
(index.html)
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
<h2>Update Employee Details</h2>
<form action="newjsp.jsp" method="post">
    Employee No:
    <input type="number" name="eno" required><br><br>
    Name:
    <input type="text" name="name" required><br><br>
    Age:
    <input type="number" name="age" required><br><br>
    Designation:
    <input type="text" name="designation" required><br><br>
    Salary:
    <input type="number" name="salary" required><br><br>
    <input type="submit" value="Update Employee">
</form>
</body>
</html>

//newjsp.jsp
<%@ page import="java.sql.*" %>
<%
String eno = request.getParameter("eno");
String name = request.getParameter("name");
String age = request.getParameter("age");
String designation = request.getParameter("designation");
String salary = request.getParameter("salary");
Connection con = null;
PreparedStatement ps = null;
try
{
   Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/employee_db",
        "root",
        "root"
    );
    String sql = "UPDATE employee SET name=?, age=?, designation=?, salary=? WHERE eno=?";
    ps = con.prepareStatement(sql);
    ps.setString(1, name);
    ps.setInt(2, Integer.parseInt(age));
    ps.setString(3, designation);
    ps.setDouble(4, Double.parseDouble(salary));
    ps.setInt(5, Integer.parseInt(eno));
    int rows = ps.executeUpdate();
    if(rows > 0)
    {
        out.println("<h2>Employee Record Updated Successfully.</h2>");
    }
    else
    {
        out.println("<h2>Employee Number Not Found.</h2>");
    }
}
catch(Exception e)
{
    out.println(e);
}
finally
{
    if(ps != null)
        ps.close();
    if(con != null)
        con.close();
}
%>
  
//sql code
SQL code:
CREATE DATABASE employee_db;
USE employee_db;
CREATE TABLE employee (
    eno INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    designation VARCHAR(50),
    salary DOUBLE
);
INSERT INTO employee VALUES
(101,'Rahul',25,'Developer',35000),
(102,'Priya',28,'Tester',30000),
(103,'Amit',30,'Manager',50000);
SELECT * FROM employee;

////////////////////////////////////////practical 5b////////////////////////////////////////////////////////////////////////
//index.jsp
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Expression Demo</title>
    </head>
    <body>
        <h2>JSP Expression Language</h2>
        <p>1. Addition: <%= 10 + 20%></p>
        <p>2. Multiplication: <%= 5 * 6%></p>
        <p>3. Division: <%= 20 / 4%></p>
        <p>4. Current Year: <%= java.time.Year.now()%></p>
        <p>5. String: <%= "Welcome to JSP"%></p>
        <h3>radhika 17</h3>  
    </body>
</html>

////////////////////////////////////////practical 5c////////////////////////////////////////////////////////////////////////
//index.jsp
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Core Tag Example</title>
    </head>
    <body>
        <c:forEach var = "j" begin="5" end="15">
            Item <c:out value = "${j}"/><p>
            </c:forEach>
        <h3>radhika 17</h3>  
    </body>
</html>
  
