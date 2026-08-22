<html>
    <head>
        <title>Patient Details</title>
    </head>
    <body>
        <h2>Patient Details</h2>
        <%
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String gender = request.getParameter("gender");
            String department = request.getParameter("department");
            String address = request.getParameter("address");
            String[] symptoms = request.getParameterValues("symptom");
            boolean valid = true;
            if (name == null || name.trim().equals("")) {
                out.println("Patient name is required.<br>");
                valid = false;
            }
            if (age == null || age.trim().equals("")) {
                out.println("Age is required.<br>");
                valid = false;
            }
            if (email == null || email.trim().equals("")) {
                out.println("Email is required.<br>");
                valid = false;
            }
            if (mobile == null || mobile.trim().equals("")) {
                out.println("Mobile number is required.<br>");
                valid = false;
            }
            if (gender == null) {
                out.println("Please select gender.<br>");
                valid = false;
            }
            if (department == null || department.equals("")) {
                out.println("Please select a department.<br>");
                valid = false;
            }
            if (symptoms == null) {
                out.println("Please select at least one symptom.<br>");
                valid = false;
            }
            if (address == null || address.trim().equals("")) {
                out.println("Address is required.<br>");
                valid = false;
            }
            if (valid) {
        %>
        <h3>Patient Registration Successful</h3>
        Patient Name: <%= name%><br><br>
        Age: <%= age%><br><br>
        Email: <%= email%><br><br>
        Mobile: <%= mobile%><br><br>
        Gender: <%= gender%><br><br>
        Department: <%= department%><br><br>
        Symptoms:
        <%
            for (String symptom : symptoms) {
                out.println(symptom + " ");
            }
        %>
        <br><br>
        Address: <%= address%><br><br>
        <%
            }
        %>
    </body>
</html>