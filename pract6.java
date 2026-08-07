////////////////////////////////practical 6a///////////////////////////////////////
//////index.html:
<!DOCTYPE html> 
<html> 
    <head> 
        <title>Currency Converter</title> 
    </head> 
    <body> 
        <form action="CCServlet"> 
            Enter Amount<input type="text" name="amt"><br> 
            Select Conversion Type <br>
            <input type="radio" name="type" value="r2d" checked>Rupees to Dollar<br>
            <input type="radio" name="type" value="d2r">Dollar to Rupees<br>  
            <input type="reset"><br>
            <input type="submit" value="CONVERT"><br>  
        </form> 
        <h2> Radhika 17</h2>
    </body> 
</html>

///CCBean.java:
package EJPracts;
import javax.ejb.Stateless;
@Stateless
public class CCBean {
    public CCBean() {
    }
    public double r2Dollar(double r) {
        return r / 83.85;
    }
    public double d2Rupees(double d) {
        return d * 83.85;
    }

//////CCServlet.java:
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EJPracts.CCBean;

@WebServlet(urlPatterns = {"/CCServlet"})
public class CCServlet extends HttpServlet {
    @EJB
    CCBean obj;

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        double amt = Double.parseDouble(
                request.getParameter("amt")
        );
        if (request.getParameter("type").equals("r2d")) {
            out.println(
                    "<h1>" + amt + " Rupees = "
                    + obj.r2Dollar(amt)
                    + " Dollars</h1>"
            );
        }
        if (request.getParameter("type").equals("d2r")) {
            out.println(
                    "<h1>" + amt + " Dollars = "
                    + obj.d2Rupees(amt)
                    + " Rupees</h1>"
            );
        }
    }
}

////////////////////////////////practical 6b////////////////////////////////////////
/////index.jsp:
<html> 
    <head> 
        <title>Room Reservation</title> 
    </head> 
    <body> 
        <p><b>Enter Details For Room Reservation.</b></p> <form method="post" action="ReservationServlet"> 
            Customers Name: 
            <input type="text" name="cname" value="" size="20">  <br><br> 
            Address: 
            <input type="text" name="cadd" value="" size="20">  <br><br> 
            Phone Number: 
            <input type="text" name="cph" value="" size="12">  <br><br> 
            Room Type: 
            <select name="roomtype"> 
                <option value="General">General</option>  <option value="Deluxe">Deluxe</option>  <option value="Suite">Suite</option> 
            </select> 
            <br><br> 
            Check In Date (DD/MM/YYYY): 
            <input type="text" name="checkindate"> 
            <br><br> 
            Check Out Date (DD/MM/YYYY): 
            <input type="text" name="checkoutdate">  <br><br> 
            Payment Mode: 
            <select name="paymode"> 
                <option value="CASH">CASH</option> 
                <option value="CREDIT CARD">CREDIT CARD</option>  </select>
            <br><br> 
            <input type="submit" value="Submit"> 
        </form> 
    </body> 
</html> 

////ReservationBean.java:
package EJPracts;
import java.util.ArrayList;
import javax.ejb.Stateless;
@Stateless
public class ReservationBean {
    public String welcome(String customer) {
        return "Hello " + customer.toUpperCase() + " Welcome to Our Hotel";
    }
    public String roomType(String roomtype) {
        return "You have Selected " + roomtype.toUpperCase() + " Room";
    }
    public String payment(String roomtype) {
        if (roomtype.equalsIgnoreCase("SUITE")) {
            return "You have to pay 8000 Rs";
        } else if (roomtype.equalsIgnoreCase("DELUXE")) {
            return "You have to pay 4000 Rs";
        } else {
            return "You have to pay 2000 Rs";
        }
    }
    public ArrayList<String> reserve(
            String customer,
            String add,
            String ph,
            String checkin,
            String checkout,
            String roomtype,
            String paymode) {
        ArrayList<String> a = new ArrayList<String>();
        a.add(customer);
        a.add(add);
        a.add(ph);
        a.add(checkin);
        a.add(checkout);
        a.add(roomtype);
        a.add(paymode);
        return a;
    }
}

///ReservationServlet.java:
package servlet;
import EJPracts.ReservationBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ReservationServlet",
        urlPatterns = {"/ReservationServlet"})
public class ReservationServlet extends HttpServlet {
    @EJB
    ReservationBean rb;
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cname = request.getParameter("cname");
        String add = request.getParameter("cadd");
        String ph = request.getParameter("cph");
        String roomtype = request.getParameter("roomtype");
        String chkin = request.getParameter("checkindate");
        String chkout = request.getParameter("checkoutdate");
        String paymode = request.getParameter("paymode");
        PrintWriter out = response.getWriter();
        ArrayList<String> list1
                = rb.reserve(cname, add, ph, chkin, chkout,
                        roomtype, paymode);
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reservation EJB</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>" + rb.welcome(cname) + "</h2>");
            out.println("<br/>");
            out.println(rb.roomType(roomtype));
            out.println("<br/><br/>");
            out.println(rb.payment(roomtype));
            out.println("<br/><br/>");
            out.println("<hr>");
            out.println("Your Details :");
            out.println("<br/><br/>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Address</th>");
            out.println("<th>Phone</th>");
            out.println("<th>Check In Date</th>");
            out.println("<th>Check Out Date</th>");
            out.println("<th>Room</th>");
            out.println("<th>Payment Mode</th>");
            out.println("</tr>");
            out.println("<tr>");
            for (int i = 0; i < list1.size(); i++) {
                out.println("<td>");
                out.println(list1.get(i));
                out.println("</td>");
            }
            out.println("</tr>");
            out.println("</table>");
            out.println("<br/>");
            out.println("Click ");
            out.println("<a href='index.jsp'>here</a>");
            out.println(" to go back");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

