<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<html>
    <head>
        <title>JSP Directives and Implicit Objects</title>
    </head>
    <body>
        <h2>JSP Directives and Implicit Objects</h2>
        <h3>JSP Directives</h3>
        <p>Language: Java</p>
        <p>Content Type: <%= response.getContentType()%></p>
        <p>Current Date: <%= new Date()%></p>
        <h3>Implicit Objects</h3>
        <p>1. Request Method: <%= request.getMethod()%></p>
        <p>2. Response Content Type: <%= response.getContentType()%></p>
        <p>3. Session ID: <%= session.getId()%></p>
        <p>4. Application Server: <%= application.getServerInfo()%></p>
        <p>5. Page Name: <%= page.getClass().getName()%></p>
        <p>6. PageContext: <%= pageContext.getClass().getName()%></p>
        <p>7. Request Parameter: <%= request.getParameter("name")%></p>
        <p>8. Config Servlet Name: <%= config.getServletName()%></p>
        <p>9. Exception Object: Exception object is available only in error pages.</p>
        <h2>radhika 17</h2>
    </body>
</html>