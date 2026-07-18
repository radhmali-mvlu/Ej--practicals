///////////////////////////////////////////////pract-4a/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//index.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head><title> JSP Page</title></head>
    <body>
        <h1>Use of Intrinsic Objects in JSP</h1>
        <h1>Request Object </h1>
        Query String <%=request.getQueryString()%><br>
        Context Path <%=request.getContextPath()%><br>
        Remote Host <%=request.getRemoteHost()%><br>
        <h1>Response Object </h1>
        Character Encoding Type <%=response.getCharacterEncoding()%><br>
        Content Type
        <%=response.getContentType()%><br>
        Locale <%=response.getLocale()%><br>
        <h1>Session Object </h1>
        ID <%=session.getId()%><br>
        Creation Time <%=new java.util.Date(session.getCreationTime())%><br>
        Last Access Time<%=new java.util.Date(session.getLastAccessedTime())%><br>
    </body>
</html>

/////////////////////////////////////////////////////////pract4b//////////////////////////////////////////////////////////////////////////////////////////////////////
//index.jsp
<!DOCTYPE html>
<html>
    <head>
        <meta https-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>JSP Registration</title>
    </head>
    <body>
        <h1>Registration Form</h1>
        <form action="val.jsp" method="post">
            <table style="width:50%">
                <tr>
                    <td>Full Name</td>
                    <td><input type="text" name="fullname"/></td>
                </tr><tr>
                    <td>Age</td>
                    <td><input type="text" name="age"/></td>
                </tr><tr>
                    <td>E-Mail</td>
                    <td><input type="text" name="email" size="20"/></td>
                </tr><tr>

                    <td>Gender</td>
                    <td><input type="radio" name="gender" value="Male">Male
                        <input type="radio" name="gender" value="Female"/>Female
                    </td>
                </tr><tr>
                    <td>Hobbies</td>
                    <td><input type="checkbox" name="hb" value="Acting"/>Acting
                        <input type="checkbox" name="hb" value="Dancing"/>Dancing
                        <input type="checkbox" name="hb" value="Singing"/>Singing
                        <input type="checkbox" name="hb" value="Drawing"/>Drawing
                    </td>
                </tr>
            </table>
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>

//web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-
app_3_1.xsd"

         version="3.1">
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>

//val.jsp
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%!
    int ageInNumbers;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
%>
<%
    String name = request.getParameter("fullname");
    String age = request.getParameter("age");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    String hb[] = request.getParameterValues("hb");
    if (name == null || name.isEmpty() || age == null || age.isEmpty() || email == null || email.isEmpty() || gender
            == null || gender.isEmpty()) {
        out.println("<font color=red>Please fill all the fields</font><br>");
    }
    if (!email.matches(EMAIL_REGEX)) {
        out.println("<font color=red>Correct Your Email Address</font><br>");
    }
    try {
        ageInNumbers = Integer.parseInt(age.trim());
    } catch (NumberFormatException e) {
        out.println("<font color=red>Age must be numbers</font><br>");
    }
    if (ageInNumbers < 18 || ageInNumbers > 60) {
        out.println("<font color=red>Age must be between 18 and 60</font><br>");
    }
%>
Your Extended Information is as follows:<br><br>
Full Name<b>:<%=name%></b><br>
Age<b>:<%=age%></b><br>
EMail<b>:<%=email%></b><br>
Gender<b>:<%=gender%></b><br>

Hobbies<b>:
    <%
        if (hb != null && hb.length != 0) {
            for (int i = 0; i < hb.length; i++) {
                out.println(hb[i]);
            }
        }
    %>

/////////////////////////////////////////////////////////////pract 4 c///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//index.jsp       
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
<h2>Registration & Login System</h2>
<a href="register.jsp">Register</a>
<br><br>
<a href="login.jsp">Login</a>
</body>
</html>

// resitor.jsp
        <%@page import="java.sql.*"%>
<%@page import="db.DBConnection"%>
<%
String msg="";
if(request.getParameter("register")!=null)
{
String username=request.getParameter("username");
String password=request.getParameter("password");
Connection con=DBConnection.getConnection();
PreparedStatement ps=con.prepareStatement(
"insert into users(username,password) values(?,?)");
ps.setString(1,username);
ps.setString(2,password);
int i=ps.executeUpdate();
if(i>0)
msg="Registration Successful!";
else
msg="Registration Failed";
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>
<h2>User Registration</h2>
<form method="post">
Username
<input type="text" name="username">
<br><br>
Password
<input type="password" name="password">
<br><br>
<input type="submit" name="register" value="Register">
</form>
<br>
<%=msg%>
<br><br>
<a href="login.jsp">Go to Login</a>
</body>
</html>


//login.jsp
<%@page import="java.sql.*"%>
<%@page import="db.DBConnection"%>
<%
if(request.getParameter("login")!=null)
{
String username=request.getParameter("username");
String password=request.getParameter("password");
Connection con=DBConnection.getConnection();
PreparedStatement ps=con.prepareStatement(
"select * from users where username=? and password=?"
);
ps.setString(1,username);
ps.setString(2,password);
ResultSet rs=ps.executeQuery();
if(rs.next())
{
session.setAttribute("user",username);
response.sendRedirect("success.jsp")
}
else
{
response.sendRedirect("error.jsp");
}
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
<h2>User Login</h2>
<form method="post">
Username
<input type="text" name="username">
<br><br>
Password
<input type="password" name="password">
<br><br>
<input type="submit" name="login" value="Login">
</form>
</body>
</html>

//success.jsp
<%
String user=(String)session.getAttribute("user");
if(user==null)
{
response.sendRedirect("login.jsp");
return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Success</title>
</head>
<body>
<h1>Login Successful</h1>
<h2>Welcome <%=user%></h2>
</body>
</html>
(error.jsp):

//error.jsp
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
<h2>Invalid Username or Password</h2>
<a href="login.jsp">Try Again</a>
</body>
</html>

//DbConnection:
package db;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "mysql_2026");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}

//mysql
CREATE DATABASE studentdb;
USE studentdb;
CREATE TABLE users
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);




