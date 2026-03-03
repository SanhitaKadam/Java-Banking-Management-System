<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*, com.bank.Connectivity" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container" style="max-width: 500px;">
    <h2>Update Account</h2>
    <%
        int ano = Integer.parseInt(request.getParameter("ano"));
        Connection cn = Connectivity.connect();
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM accounts WHERE ano=?");
        ps.setInt(1, ano);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
    %>
    <form action="UpdateServlet" method="POST">
        <input type="hidden" name="ano" value="<%= rs.getInt("ano") %>">
        
        <label>Name</label>
        <input type="text" name="aname" value="<%= rs.getString("aname") %>">
        
        <label>Mobile</label>
        <input type="text" name="amobile" value="<%= rs.getString("amobile") %>">
        
        <label>City</label>
        <input type="text" name="acity" value="<%= rs.getString("acity") %>">
        
        <button type="submit">Update Record</button>
    </form>
    <% } %>
</div>
</body>
</html>