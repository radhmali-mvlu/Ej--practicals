<html>
    <head>
        <title>Patient Registration Form</title>
    </head>
    <body>
        <h2>Patient Registration Form</h2>
        <form action="processPatient.jsp" method="post">
            Patient Name:
            <input type="text" name="name">
            <br><br>
            Age:
            <input type="text" name="age">
            <br><br>
            Email:
            <input type="text" name="email">
            <br><br>
            Mobile:
            <input type="text" name="mobile">
            <br><br>

            Gender:
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female"> Female
            <br><br>

            Department:
            <select name="department">
                <option value="">Select Department</option>
                <option value="General Medicine">General Medicine</option>
                <option value="Cardiology">Cardiology</option>
                <option value="Orthopedics">Orthopedics</option>
                <option value="Dermatology">Dermatology</option>
            </select>
            <br><br>

            Symptoms:
            <input type="checkbox" name="symptom" value="Fever"> Fever
            <input type="checkbox" name="symptom" value="Cough"> Cough
            <input type="checkbox" name="symptom" value="Headache"> Headache
            <br><br>

            Address:
            <textarea name="address"></textarea>
            <br><br>

            <input type="submit" value="Register Patient">

        </form>

    </body>
</html>