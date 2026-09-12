//9. Implement the following JPA applications.
//a. Develop a simple Inventory Application Using JPA.

//CheckInventory.jsp:
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="myAPP.Inventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
EntityManagerFactory eMF =
        Persistence.createEntityManagerFactory("INVJPAPU");
EntityManager eM = eMF.createEntityManager();
EntityTransaction eT = null;
List<Inventory> inventoryList;
String submit = request.getParameter("AddToInventory");
if (submit != null && submit.equals("AddToInventory")) {
    try {
        String itemname =
                request.getParameter("itemname");
        String description =
                request.getParameter("description");
        String quantity1 =
                request.getParameter("quantity");
        int quantity =
                Integer.parseInt(quantity1);
        String addedDate =
                new java.util.Date().toString();
        Inventory inv = new Inventory();
        inv.setItemName(itemname);
        inv.setDescription(description);
        inv.setQuantity(quantity);
        inv.setAddedDate(addedDate);
        eT = eM.getTransaction();
        eT.begin();
        eM.persist(inv);
        eT.commit();
    } catch (RuntimeException e) {
        if (eT != null && eT.isActive()) {
            eT.rollback();
        }
        throw e;
    }
}
inventoryList =
        eM.createQuery("SELECT i FROM Inventory i",
                       Inventory.class)
          .getResultList();
eM.close();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory</title>
</head>
<body>
<table border="1">
<tr>
    <td colspan="5" align="center">
        <b>
            Click
            <a href="index.jsp">here</a>
            to add items in the Inventory.
        </b>
    </td>
</tr>
<tr>
    <th>Item ID</th>
    <th>Item Name</th>
    <th>Description</th>
    <th>Quantity</th>
    <th>Added Date & Time</th>
</tr>
<%
Iterator<Inventory> iterator =
        inventoryList.iterator();
while (iterator.hasNext()) {
    Inventory obj =
            iterator.next();
%>
<tr>
    <td><%=obj.getItemID()%></td>
    <td><%=obj.getItemName()%></td>
    <td><%=obj.getDescription()%></td>
    <td><%=obj.getQuantity()%></td>
    <td><%=obj.getAddedDate()%></td>
</tr>
<%
}
%>
</table>
</body>
</html>


//Index.jsp:
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory</title>
</head>
<body>
<form action="checkInventory.jsp" method="post">
<table>
<tr>
    <td colspan="2" align="center">
        <b>Add the item in Inventory</b>
    </td>
</tr>
<tr>
    <td>Item Name:</td>
    <td>
        <input name="itemname" maxlength="25" size="50">
    </td>
</tr>
<tr>
    <td>Description:</td>
    <td>
        <textarea rows="5" cols="36"
                  name="description"></textarea>
    </td>
</tr>
<tr>
    <td>Quantity:</td>
    <td>
        <input name="quantity" maxlength="4" size="5">
    </td>
</tr>
<tr>
    <td colspan="2">
        <input type="submit"
               name="AddToInventory"
               value="AddToInventory">
    </td>
</tr>
</table>
</form>
</body>
</html>


//Inventory.jsp:
package myAPP;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid", unique = true, updatable = false)
    private Integer itemid;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "addedDate")
    private String addedDate;
    public Inventory() {
    }
    public Integer getItemID() {
        return itemid;
    }
    public void setItemID(Integer itemid) {
        this.itemid = itemid;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getAddedDate() {
        return addedDate;
    }
    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
}




