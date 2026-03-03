<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.bank.Connectivity" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Account | R3 Bank</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="navbar">
    <div class="logo">R3 Bank</div>
    <div><a href="home.html">Home</a></div>
</div>

<div class="container" style="max-width: 500px; margin-bottom: 20px;">
    <h2>View Account</h2>
    <p>Enter account number to see details</p>
    <form action="View" method="post">
        <input type="number" name="ano" placeholder="Account Number" required>
        <button type="submit">Search</button>
    </form>
</div>

<% 
    if (request.getAttribute("acc_data") != null) { 
        ResultSet rs = (ResultSet) request.getAttribute("acc_data");
%>
    <div class="container_hero" style="width: 90%; max-width: 1000px; margin: 30px auto; padding: 20px; display: block; overflow-x: auto;">
    <h3 style="margin-bottom: 15px; color: #102a43;">Account Information</h3>
    <table class="table">
            <thead>
                <tr>
                    <th>Acc No</th>
                    <th>Name</th>
                    <th>Mobile</th>
                    <th>City</th>
                    <th>Balance</th>
                    <th style="text-align:center;">Actions</th> </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= rs.getInt("ano") %></td>
                    <td><%= rs.getString("aname") %></td>
                    <td><%= rs.getString("amobile") %></td>
                    <td><%= rs.getString("acity") %></td>
                    <td><%= rs.getFloat("abal") %></td>
                    <td style="text-align:center;"> <a href="edit-account.jsp?ano=<%= rs.getInt("ano") %>" class="btn-edit">Edit</a>
                        <a href="DeleteServlet?ano=<%= rs.getInt("ano") %>" class="btn-delete" onclick="return confirm('Do you want to delete...?')">Delete</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
<% 
    } else if (request.getAttribute("error") != null) { 
%>
    <div class="container" style="max-width: 500px; color: red;">
        <b><%= request.getAttribute("error") %></b>
    </div>
<% } %>

</body>
</html>