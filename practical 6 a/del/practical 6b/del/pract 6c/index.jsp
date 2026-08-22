<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>E-Commerce Products</title>

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 30px;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: white;
            }

            th {
                background-color: #2874A6;
                color: white;
                padding: 12px;
            }

            td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: center;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #D6EAF8;
            }
        </style>

    </head>

    <body>

        <h2>E-Commerce Product List</h2>

        <table>

            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Brand</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Rating</th>
                <th>Discount</th>
                <th>Description</th>
            </tr>

            <%
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {

                    Class.forName("com.mysql.jdbc.Driver");

                    con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/ecommerce",
                            "root",
                            "root"
                    );

                    stmt = con.createStatement();

                    rs = stmt.executeQuery("SELECT * FROM product");

                    while (rs.next()) {
            %>

            <tr>
                <td><%= rs.getInt("product_id")%></td>
                <td><%= rs.getString("product_name")%></td>
                <td><%= rs.getString("category")%></td>
                <td><%= rs.getString("brand")%></td>
                <td>₹<%= rs.getDouble("price")%></td>
                <td><%= rs.getInt("quantity")%></td>
                <td><%= rs.getDouble("rating")%> ⭐</td>
                <td><%= rs.getDouble("discount")%>%</td>
                <td><%= rs.getString("description")%></td>
            </tr>

            <%
                    }

                } catch (Exception e) {
                    out.println("<p>Error: " + e.getMessage() + "</p>");

                } finally {

                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            %>

        </table>

    </body>
</html>
