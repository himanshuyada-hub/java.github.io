SLIP.1
(1).Create a servlet for a login page. If the username and password are correct then it says message “Hello <username>” else a message “login failed”
Index.html
<html>
 <head>
 <title>Login</title>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 </head>
 <body>
 <form action="Login" method="get" name="frm">
 Enter username : 
 <input name="uname" type="text"/>
 Enter password : 
 <input name="pass" type="password"/>
 
 <input name="submit" type="Submit"/>
 
 <input name="reset" type="Reset"/>
 </form>
 </body>
</html>  
  Login.html
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
String uname = request.getParameter("uname");
String pass = request.getParameter("pass");
if(uname.equals("Hiloni") && pass.equals("170512")){
out.println("Hello <b>"+(uname)+"</b> Welcome to Java servlet");
}
else{
  out.println("Login Failed");
}
}
}
}

(2).Develop a simple JSP application to display values obtained from the use of implicit objects of various types.
  index.jsp
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Implicit Objects!!</title>
 </head>
 <body>
 <% PrintWriter wrt = response.getWriter();
 wrt.print("<b>Use of Response Object Writer Example</b>");
 %>
 <br><br> 
 <strong>Use of Request Object - locale example: </strong>
 <%=request.getLocale().toString()%><br>
 <strong>Use of Out Object prints: </strong>
 <%out.print("This is an example");%><br> 
 <strong>Use of Config Object - servlet name: </strong>
 <%=config.getServletName()%><br> 
 <strong>Use of Application Object - server info: </strong>
 <%=application.getServerInfo()%><br> 
 <strong>Use of Page Object - page name: </strong>
 <%=page.getClass().getName()%><br> 
 <strong>Use of Application Object - server info: </strong>
 <%=application.getServerInfo()%><br>
 <strong>Use of Session Object - creation time: </strong>
 <%=session.getCreationTime()%><br>
 <%pageContext.setAttribute("Test","Test Value");%>
 <strong>Use of PageContext Object - class name:</strong>
 <%=application.getServerInfo()%><br><br>
 PRANAY T019
 </body>
</html>
   web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.1" xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/webapp_3_1.xsd">
 <welcome-file-list>
  <welcome-file>index.jsp</welcome-file>
 </welcome-file-list>
</web-app>

  Slip (2).
  (1).Create a registration servlet in Java using JDBC. Accept the details such as Username, Password, Email, and Country from the user using HTML Form and store the registration details in the database.
  Index.html
<!DOCTYPE html>
<html>
 <head>
 <title>Practical 1 C</title>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 </head>
 <body>
 <form action="ServReg" method="post">
 <table cellpadding="1">
 <thead>
 <tr>
 <th><b>REGISTRATION FORM</b></th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td><b>First Name : </b></td>
 <td><input type="text" name="fname" value=""></td>
 </tr>
 <tr>
 <td><b>Last Name : </b></td>
 <td><input type="text" name="sname" value=""></td>
 </tr>
 <tr>
 <td><b>Zip Code : </b></td>
 <td><input type="text" name="zip" value=""></td>
 </tr>
 <tr>
 <td><b>User Name : </b></td>
 <td><input type="text" name="uid" value=""></td>
 </tr>
 <tr>
 <td><b>Password : </b></td>
 <td><input type="password" name="pwd" value=""></td>
 </tr>
 <tr>
 <td><b>Confirm Password : </b></td>
 <td><input type="password" name="pwd1" value=""></td>
 </tr>
 <tr>
 <td><b>Town : </b></td>
 <td><input type="text" name="town" value=""></td>
 </tr>
  <tr>
 <td><b>Country : </b></td>
 <td><input type="text" name="country" value=""></td>
 </tr>
 <tr>
 <td><input type="submit" value="Submit"></td>
 </tr>
 </tbody>
 </table>
 </form>
 </body>
</html>
ServReg.java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
@WebServlet(urlPatterns = {"/ServReg"})
public class ServReg extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
 
 String connectionURL = "jdbc:mysql://localhost:3306/reg";
 Connection connection = null;
 ResultSet rs;
 response.setContentType("text/html;charset=UTF-8");
 String uid = request.getParameter("uid");
 String fname = request.getParameter("fname");
 String sname = request.getParameter("sname");
 String pwd = request.getParameter("pwd");
 String pwd1 = request.getParameter("pwd1");
 String town = request.getParameter("town");
 String country = request.getParameter("country");
 String zip = request.getParameter("zip");
   try{
 Class.forName("org.gjt.mm.mysql.Driver");
 connection = DriverManager.getConnection(connectionURL,"root","root123");
 String sql = "insert into Register values(?,?,?,?,?,?,?,?)";
 PreparedStatement pst = connection.prepareStatement(sql);
 pst.setString(1,uid);
     pst.setString(2,fname); 
 pst.setString(3,sname); 
 pst.setString(4,pwd); 
 pst.setString(5,pwd1); 
 pst.setString(6,town); 
 pst.setString(7,country); 
 pst.setString(8,zip);
 int numRowsChanged = pst.executeUpdate();
 out.println("Welcome : ");
 out.println("'"+fname+"'");
 pst.close();
 }
 catch(ClassNotFoundException e){
 out.println("Couldnt Load database driver : "+e.getMessage());
 }
 catch(SQLException e){
 out.println("SQL Exception caught : "+e.getMessage());
 }
 catch(Exception e){
 out.println(e);
 }
 finally{
 try{
 if(connection != null)
 connection.close();
 }
 catch(SQLException ignored){
 out.println(ignored);
 }
 }
 }
 }
}
(2). Develop a JSP application to allow the user to modify the registration details after the valid authentication.
Index.html
<!DOCTYPE html>
<html>
 <head>
 <title>Practical 1 C</title>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 </head>
 <body>
 <form action="ServReg" method="post">
 <table cellpadding="1">
 <thead>
 <tr>
 <th><b>REGISTRATION FORM</b></th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td><b>First Name : </b></td>
 <td><input type="text" name="fname" value=""></td>
 </tr>
 <tr>
 <td><b>Last Name : </b></td>
 <td><input type="text" name="sname" value=""></td>
 </tr>
 <tr>
 <td><b>Zip Code : </b></td>
 <td><input type="text" name="zip" value=""></td>
 </tr>
  <tr>
 <td><b>Zip Code : </b></td>
 <td><input type="text" name="zip" value=""></td>
 </tr>
 <tr>
 <td><b>User Name : </b></td>
 <td><input type="text" name="uid" value=""></td>
 </tr>
 <tr>
 <td><b>Password : </b></td>
 <td><input type="password" name="pwd" value=""></td>
 </tr>
 <tr>
 <td><b>Confirm Password : </b></td>
 <td><input type="password" name="pwd1" value=""></td>
 </tr>
 <tr>
 <td><b>Town : </b></td>
 <td><input type="text" name="town" value=""></td>
 </tr>
  <tr>
 <td><b>Country : </b></td>
 <td><input type="text" name="country" value=""></td>
 </tr>
 <tr>
 <td><input type="submit" value="Submit"></td>
 </tr>
 </tbody>
 </table>
 </form>
 </body>
</html>
ServReg.java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
@WebServlet(urlPatterns = {"/ServReg"})
public class ServReg extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
 
 String connectionURL = "jdbc:mysql://localhost:3306/reg";
 Connection connection = null;
 ResultSet rs;
 response.setContentType("text/html;charset=UTF-8");
 String uid = request.getParameter("uid");
 String fname = request.getParameter("fname");
 String sname = request.getParameter("sname");
 String pwd = request.getParameter("pwd");
 String pwd1 = request.getParameter("pwd1");
 String town = request.getParameter("town");
 String country = request.getParameter("country");
 String zip = request.getParameter("zip");
 
 try{
 Class.forName("org.gjt.mm.mysql.Driver");
 connection = DriverManager.getConnection(connectionURL,"root","root123");
 String sql = "insert into Register values(?,?,?,?,?,?,?,?)";
 PreparedStatement pst = connection.prepareStatement(sql);
 pst.setString(1,uid);
   pst.setString(2,fname); 
 pst.setString(3,sname); 
 pst.setString(4,pwd); 
 pst.setString(5,pwd1); 
 pst.setString(6,town); 
 pst.setString(7,country); 
 pst.setString(8,zip);
 int numRowsChanged = pst.executeUpdate();
 out.println("Welcome : ");
 out.println("'"+fname+"'");
 pst.close();
 }
 catch(ClassNotFoundException e){
 out.println("Couldnt Load database driver : "+e.getMessage());
 }
 catch(SQLException e){
 out.println("SQL Exception caught : "+e.getMessage());
 }
 catch(Exception e){
 out.println(e);
 }
 finally{
 try{
 if(connection != null)
 connection.close();
 }
 catch(SQLException ignored){
 out.println(ignored);
 }
 }
 }
 }
}
  
  Slip (3).
  1.Create a JSP application to demonstrate the use of JSTL.
  index.jsp
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Core Tag!!</title>
 </head>
 <body>
 <c:forEach var="j" begin="1" end="3">
 Item <c:out value="${j}" /><br>
 </c:forEach>
 PRANAY T019
 </body>
</html>
  2. Develop simple application to demonstrate accessing Session Bean using EJB.
AdderImpl.java
package AdderImpl;
import javax.ejb.Stateless;
@Stateless(mappedName="st1")
public class AdderImpl implements AdderImplRemote {
 @Override
 public String display(String name, String address, String mobile) {
 String details = "Name: " + name + ", Address: " + address + ", Mobile: " + mobile;
 System.out.println(details);
 return details; // Return the details string if you want to use it in main
 }
}
AdderImplRemote.java
package AdderImpl;
import javax.ejb.Remote;
@Remote
public interface AdderImplRemote{
 String display(String name, String address, String mobile);
}
Test.java
import AdderImpl.AdderImplRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
public class Test {
 public static void main(String[] args) throws Exception {
 Context context = new InitialContext();
 AdderImplRemote remote = (AdderImplRemote) context.lookup("st1");
 System.out.println("Visitor's Details:");
 String details = remote.display("PRANAYT019", "Mumbai", "9800017000");
 System.out.println(details); // This will print the details returned by the display method
 }
}
  SLIP(4).
  1.Develop simple EJB application to demonstrate Servlet Hit count using Singleton Session Beans.
  index.html
<html> 
 <head> 
 <meta charset="UTF-8"> 
 <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
 <meta http-equiv="Refresh" content="0; URL=ServletClient"> 
 </head> 
</html>
STHitCountBean.java
package ejb; 
import javax.ejb.Singleton; 
@Singleton 
public class STHitCountBean { 
 private int hitCount; 
 public synchronized int getCount() 
 { 
 return hitCount++; 
 } 
}
CCClient.java
package servlet; 
import ejb.STHitCountBean; 
import java.io.*; 
import javax.ejb.EJB; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.*; 
@WebServlet(name = "ServletClient", urlPatterns = {"/ServletClient"}) 
public class CCClient extends HttpServlet { 
@EJB STHitCountBean obj; 
@Override 
protected void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, 
IOException 
{
 res.setContentType("text/html"); 
 PrintWriter out=res.getWriter();
  out.print("<b>Number of times this Servlet is accessed </b>: "+obj.getCount()); 
 out.println("<br>T019 PRANAY");
} 
}
 2.Write a servlet code to store the biodata details of a user into the database. (Name, age, address, hobbies, gender, Qualification).
   index.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
 <title>JSP Registration</title>
 </head>
 <body>
 <h1>Registration Form</h1>
 <form action="val.jsp" method="post">
 <table style="width:50%">
 <tbody>
 <tr>
 <td>Full Name</td>
 <td><input type="text" name="fullname"/></td>
 </tr>
 <tr>
 <td>Age</td>
 <td><input type="text" name="age"/></td>
 </tr>
 <tr>
 <td>E-mail</td>
 <td><input type="text" name="email" size="20"/></td>
 </tr>
 <tr>
 <td>Gender</td>
   <td>
 <input type="radio" name="gender" value="Male"/> Male
 <input type="radio" name="gender" value="Female"/> Female
 </td>
 </tr>
 <tr>
 <td>Hobbies</td>
 <td>
 <input type="checkbox" name="hb" value="Acting"/> Acting
 <input type="checkbox" name="hb" value="Dancing"/> Dancing
 <input type="checkbox" name="hb" value="Singing"/> Singing
 </td>
 </tr>
 </tbody>
 </table><br>
   <input type="Submit" value="Register"/>
 </form>
 </body>
</html>
val.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!int ageInNumbers; private static final String EMAIL_REGEX = "^[\\w-
\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"; %>
<% 
String name = request.getParameter("fullname");
String age = request.getParameter("age");
String email = request.getParameter("email");
String gender = request.getParameter("gender");
String hb[] = request.getParameterValues("hb");
if(name.isEmpty() || age.isEmpty() || email.isEmpty() || gender.isEmpty()){
 out.println("<font color=red>Please fill all the fields!!</font><br>");
}
if(email.matches(EMAIL_REGEX)){
 out.println("<font color=red>Correct your Email Address!!</font><br>");
}
try{
 ageInNumbers = Integer.parseInt(age.trim());
}
catch(NumberFormatException e){
 out.println("<font color=red>Age must be in Number!!</font><br>");
}
if(ageInNumbers >= 18 && ageInNumbers >= 60){
 out.println("<font color=red>Age must be between 18 & 60!!</font><br>");
}
%>
Your Entered Information:<br>
Full Name: <b><%=name%></b><br>
Age: <b><%=age%></b><br>
E-Mail: <b><%=email%></b><br>
Gender: <b><%=gender%></b><br>
Hobbies:
<%
if(hb!=null&&hb.length!=0){
 for(int i=0;i<hb.length;i++){
 out.println("<b>" + hb[i]+ "</b>");
 }
}
%>
web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
 <welcome-file-list>
 <welcome-file>index.jsp</welcome-file>
 </welcome-file-list>
</web-app>

  Slip .(5)
   1.Create a servlet that uses Cookies to store the number of times a user has visited servlet.
   index.html
<html>
 <head>
 <title>Cookie Demo</title>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 </head>
 <body>
 <form action="Page1">
 Enter your name: <input type="text" name="txtName"><br>
 <input type="submit" value="~~~~ Click to Enter ~~~~">
 </form>
 </body>
</html>
Page1.java
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(urlPatterns = {"/Page1"})
public class Page1 extends HttpServlet {
 public void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException
 {
 PrintWriter out=response.getWriter();
 out.println("<html><head><title>Page1</title></head>");
 out.println("<body bgcolor=pink>");
 String uname=request.getParameter("txtName");
 out.println("<h1>~~~Welcome"+uname+"</h1>");
 Cookie ck1=new Cookie("Username",uname);
 Cookie ck2=new Cookie("visit","1");
 response.addCookie(ck1);
 response.addCookie(ck2);
 out.println("<h1><a href=Page2>Click to visit Page 2</a></h1>");
 out.println("</body>");
 out.println("</html>");
 }
}
Page2.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(urlPatterns = {"/Page2"})
public class Page2 extends HttpServlet {
 @Override
 public void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException
 {
 PrintWriter out=response.getWriter();
 out.println("<html><head><title>Page2</title></head>");
 out.println("<body bgcolor=yellow>");
 Cookie [] ck= request.getCookies();
 for(int i=0;i<ck.length;i++)
 {
 if(ck[i].getName().equals("visit"))
 {
 int count=Integer.parseInt(ck[i].getValue())+1;
 out.println("<h1>Visit no: "+count+"</h1>");
 ck[i]=new Cookie("visit",count+"");
 response.addCookie(ck[i]);
 }
 else
 {
 out.println(ck[i].getName()+"="+ck[i].getValue());
 }
 out.println("<h1><a href=Page3>Click to visit page 3</a></h1>");
 out.println("<h1><a href=Page4>Click to visit page 4</a></h1>");
 out.println("<h1><a href=Page5>Click to visit page 5</a></h1>");
 out.println("</body>");
 out.println("</html>");
 }
 }
}
(2). Develop a JSP application to allow the user to modify the registration details after the valid authentication.

  Slip (6).
  1. Create a simple calculator application using servlet.
Index.html
<html>
 <head>
 <title></title>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 </head>
 <body>
 <form action="Calculator" method="get" name="frm">
 Enter Number 1:
 <input name="num1" type="text"/>
 Enter Number 2:
 <input name="num2" type="text"/>
 Operator
 <select name="operator">
 <option value="Addition">+</option>
 <option value="Subtraction">-</option>
 <option value="Multiplication">*</option>
 <option value="Division">/</option>
 </select>
 <input type="submit" value="submit"/>
 </form>
 <p>T019 Pranay</p>
 </body>
</html>
Calculator.java
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/Calculator"})
public class Calculator extends HttpServlet {
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 PrintWriter out = response.getWriter();
 String num1 = request.getParameter("num1");
 String num2 = request.getParameter("num2");
 String op = request.getParameter("operator");
 
 if(op.equals("Addition")){
 out.println("Addition is: "+(Integer.parseInt(num1)+Integer.parseInt(num2)));
 }
 
 else if(op.equals("Subtraction")){
 out.println("Subtraction is: "+(Integer.parseInt(num1)-Integer.parseInt(num2)));
 }
 
 else if(op.equals("Multiplication")){
 out.println("Multiplication is: "+(Integer.parseInt(num1)*Integer.parseInt(num2)));
 }
 
 else {
 out.println("Division is: "+(Integer.parseInt(num1)/Integer.parseInt(num2)));
 }
 }
}
(2).Write a JSP to accept eno, ename and salary. Insert these records in emp. (Create the emp table with these three fields).

  SLIP(7).
  Develop a simple JSP application to pass values from one page to another with validations.
 (Name-txt, age-txt, hobbies-checkbox, email-txt, gender-radio button).
  Ans.
  index.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
 <title>JSP Registration</title>
 </head>
 <body>
 <h1>Registration Form</h1>
 <form action="val.jsp" method="post">
 <table style="width:50%">
 <tbody>
 <tr>
 <td>Full Name</td>
 <td><input type="text" name="fullname"/></td>
 </tr>
 <tr>
 <td>Age</td>
 <td><input type="text" name="age"/></td>
 </tr>
 <tr>
 <td>E-mail</td>
 <td><input type="text" name="email" size="20"/></td>
 </tr>
 <tr>
 <td>Gender</td>
 <td>
  <input type="radio" name="gender" value="Male"/> Male
 <input type="radio" name="gender" value="Female"/> Female
 </td>
 </tr>
 <tr>
 <td>Hobbies</td>
 <td>
 <input type="checkbox" name="hb" value="Acting"/> Acting
 <input type="checkbox" name="hb" value="Dancing"/> Dancing
 <input type="checkbox" name="hb" value="Singing"/> Singing
 </td>
 </tr>
 </tbody>
 </table><br>
  <input type="Submit" value="Register"/>
 </form>
 </body>
</html>
val.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!int ageInNumbers; private static final String EMAIL_REGEX = "^[\\w-
\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"; %>
<% 
String name = request.getParameter("fullname");
String age = request.getParameter("age");
String email = request.getParameter("email");
String gender = request.getParameter("gender");
String hb[] = request.getParameterValues("hb");
if(name.isEmpty() || age.isEmpty() || email.isEmpty() || gender.isEmpty()){
 out.println("<font color=red>Please fill all the fields!!</font><br>");
}
if(email.matches(EMAIL_REGEX)){
 out.println("<font color=red>Correct your Email Address!!</font><br>");
}
try{
 ageInNumbers = Integer.parseInt(age.trim());
}
catch(NumberFormatException e){
 out.println("<font color=red>Age must be in Number!!</font><br>");
}
if(ageInNumbers >= 18 && ageInNumbers >= 60){
 out.println("<font color=red>Age must be between 18 & 60!!</font><br>");
}
%>
Your Entered Information:<br>
Full Name: <b><%=name%></b><br>
Age: <b><%=age%></b><br>
E-Mail: <b><%=email%></b><br>
Gender: <b><%=gender%></b><br>
Hobbies:
<%
if(hb!=null&&hb.length!=0){
 for(int i=0;i<hb.length;i++){
 out.println("<b>" + hb[i]+ "</b>");
 }
  }
%>
web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
 <welcome-file-list>
 <welcome-file>index.jsp</welcome-file>
 </welcome-file-list>
</web-app>
