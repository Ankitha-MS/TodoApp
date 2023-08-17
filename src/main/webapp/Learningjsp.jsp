<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
<%--This is HTML --%>
<h1>This is HTML code</h1>

<%--this is comment --%>
<!-- System.out.println("Hello"); -->

<%--Scriptlet tag --%>
 <%System.out.println("This is java code"); %>
<% int a=5; %>
<%--declarative tag --%>

<%!int a=6; %>

<%System.out.println(this.a); %>


<%--Expression Tag --%>
<h1><%=new Random().nextInt(100,999) %></h1>


</body>
</html>