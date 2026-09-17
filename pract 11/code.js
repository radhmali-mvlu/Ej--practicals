////Index.jsp:
<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="navbar">
    <h2>Online Book Store</h2>
    <div>
        <a href="index.jsp">Home</a>
        <a href="books.jsp">Books</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
        <a href="cart.jsp">Cart</a>
    </div>
</div>
<div class="hero">
    <h1>Welcome to Online Book Store</h1>
    <p>
        Find your favourite books at affordable prices.
    </p>
    <a href="books.jsp" class="button">
        View Books
    </a>
</div>
<div class="container">
    <h2>About Our Store</h2>
    <p>
        Online Book Store is a simple Java EE web application
        where users can browse books, register, login and add
        books to their shopping cart.
    </p>
</div>
</body>
</html>

///////Books.jsp:
<%@ page import="java.sql.*" %>
<%@ page import="com.bookstore.DBConnection" %>
<!DOCTYPE html>
<html>
<head>

    <title>Books</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="navbar">
    <h2>Online Book Store</h2>
    <div>
        <a href="index.jsp">Home</a>
        <a href="books.jsp">Books</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
        <a href="cart.jsp">Cart</a>
    </div>
</div>
<div class="container">
    <h1>Available Books</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Book Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <%
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = DBConnection.getConnection();
                ps = con.prepareStatement("SELECT * FROM books");
                rs = ps.executeQuery();
                while(rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("title") %></td>
            <td><%= rs.getString("author") %></td>
            <td>₹ <%= rs.getDouble("price") %></td>
            <td>
                <a class="button"
                   href="CartServlet?book_id=<%= rs.getInt("id") %>">
                    Add to Cart
                </a>
            </td>
        </tr>
        <%
                }

            } catch(Exception e) {
                out.println("Error: " + e.getMessage());
            } finally {
                try {
                    if(rs != null) rs.close();
                    if(ps != null) ps.close();
                    if(con != null) con.close();
                } catch(Exception e) {
                }
            }
        %>
    </table>
</div>
</body>
</html>

///////Cart.jsp:
<%@ page import="java.sql.*" %>
<%@ page import="com.bookstore.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="navbar">
    <h2>Online Book Store</h2>
    <div>
        <a href="index.jsp">Home</a>
        <a href="books.jsp">Books</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
        <a href="cart.jsp">Cart</a>
    </div>
</div>
<div class="container">
    <h1>Shopping Cart</h1>
    <%
        Integer userId = (Integer) session.getAttribute("user_id");
        if(userId == null) {
    %>
        <p>
            Please login to view your cart.
        </p>
        <a href="login.jsp" class="button">
            Login
        </a>
    <%
        } else {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = DBConnection.getConnection();
                String sql =
                "SELECT books.title, books.author, books.price, cart.quantity " +
                "FROM cart INNER JOIN books ON cart.book_id = books.id " +
                "WHERE cart.user_id=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
    %>
    <table>
        <tr>
            <th>Book</th>
            <th>Author</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <%
            boolean found = false;
            while(rs.next()) {
                found = true;
        %>
        <tr>
            <td>
                <%= rs.getString("title") %>
            </td>
            <td>
                <%= rs.getString("author") %>
            </td>
            <td>
                ₹ <%= rs.getDouble("price") %>
            </td>
            <td>
                <%= rs.getInt("quantity") %>
            </td>
        </tr>
        <%
            }
            if(!found) {
        %>
        <tr>
            <td colspan="4">
                Your cart is empty.
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
            } catch(Exception e) {
                out.println("Error: " + e.getMessage());
            } finally {
                try {
                    if(rs != null) rs.close();
                    if(ps != null) ps.close();
                    if(con != null) con.close();
                } catch(Exception e) {
                }
            }
        }
    %>
</div>
</body>
</html>

////////Contact.jsp:
<!DOCTYPE html>
<html>
<head>
    <title>Contact - BookHub</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">
    <div class="logo">
        BookHub
    </div>
    <div class="nav">
        <a href="index.jsp">Home</a>
        <a href="books.jsp">Books</a>
        <a href="login.jsp">Login</a>
        <a href="cart.jsp">Cart</a>
        <a href="contact.jsp">Contact</a>
    </div>
</div>
<div class="container">
    <div class="hero">
        <h1>
            Contact Us
        </h1>
        <p>
            Have a question?
            We would love to hear from you.
        </p>
        <h3>
            Email
        </h3>
        <p>
            bookhub@gmail.com
        </p>
        <h3>
            Phone
        </h3>
        <p>
            9876543210
        </p>
        <h3>
            Address
        </h3>
        <p>
            Mumbai, Maharashtra, India
        </p>
    </div>
</div>
<div class="footer">
    <p>
        © 2026 BookHub Online Book Store
    </p>
</div>
</body>
</html>

/////////Login.jsp:
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="navbar">
    <h2>Online Book Store</h2>
    <div>
        <a href="index.jsp">Home</a>
        <a href="books.jsp">Books</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
        <a href="cart.jsp">Cart</a>
    </div>
</div>
<div class="form-container">
    <h1>Login</h1>
    <form action="LoginServlet" method="post">
        <label>Email</label>
        <input type="email"
               name="email"
               placeholder="Enter email"
               required>
        <label>Password</label>
        <input type="password"
               name="password"
               placeholder="Enter password"
               required>
        <input type="submit"
               value="Login"
               class="button">
    </form>
    <p>
        Don't have an account?
        <a href="register.jsp">Register</a>
    </p>
</div>
</body>
</html>

//////////Register.jsp:
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="navbar">
    <h2>Online Book Store</h2>
    <div>
        <a href="index.jsp">Home</a>
        <a href="books.jsp">Books</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
        <a href="cart.jsp">Cart</a>
    </div>
</div>
<div class="form-container">
    <h1>Create Account</h1>
    <form action="RegisterServlet" method="post">
        <label>Name</label>
        <input type="text"
               name="name"
               placeholder="Enter your name"
               required>
        <label>Email</label>
        <input type="email"
               name="email"
               placeholder="Enter your email"
               required>
        <label>Password</label>
        <input type="password"
               name="password"
               placeholder="Enter password"
               required>
        <input type="submit"
               value="Register"
               class="button">
    </form>
    <p>
        Already have an account?
        <a href="login.jsp">Login</a>
    </p>
</div>
</body>
</html>

/////////////////CartServlet.java:-
package com.bookstore;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId =
                (Integer) session.getAttribute("user_id");
        if(userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int bookId =
                Integer.parseInt(
                        request.getParameter("book_id")
                );
        try {
            Connection con =
                    DBConnection.getConnection();
            String sql =
                    "INSERT INTO cart(user_id,book_id,quantity) " +
                    "VALUES(?,?,1)";
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
            ps.close();
            con.close();
            response.sendRedirect("cart.jsp");
        } catch(Exception e) {
            response.getWriter().println(
                    "Error: " + e.getMessage()
            );
        }
    }
}

////////////////DBconnection.java:-
package com.bookstore;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/book_store",
                "root",
                "root"
        );
        return con;
    }
}

///////////////LoginServlet.java:-
package com.bookstore;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", rs.getInt("id"));
                session.setAttribute("user_name", rs.getString("name"));
                response.sendRedirect("books.jsp");
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<h2>Invalid Email or Password</h2>");
                response.getWriter().println("<a href='login.jsp'>Try Again</a>");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            response.setContentType("text/html");
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}

///////////////////////RegisterServlet.java:
package com.bookstore;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                    "INSERT INTO users(name,email,password) VALUES(?,?,?)";

PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            int result = ps.executeUpdate();
            if(result > 0) {
                out.println("<h2>Registration Successful!</h2>");
                out.println("<a href='login.jsp'>Login Now</a>");
            } else {
                out.println("<h2>Registration Failed</h2>");
            }
            ps.close();
            con.close();
        } catch(Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}

//////////SQL CODE:
CREATE DATABASE book_store;
USE book_store;
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE cart (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    quantity INT DEFAULT 1
);

INSERT INTO books (title, author, price) VALUES
('Java Programming', 'Herbert Schildt', 550),
('Python Basics', 'Mark Lutz', 600),
('Web Development', 'Jon Duckett', 700),
('Database Management', 'Raghu Ramakrishnan', 650),
('Data Structures', 'Seymour Lipschutz', 500);
 


