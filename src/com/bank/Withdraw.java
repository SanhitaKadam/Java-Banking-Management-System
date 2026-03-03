package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ano = Integer.parseInt(request.getParameter("ano"));
	    float amount = Float.parseFloat(request.getParameter("amount"));

	    Connection cn = Connectivity.connect();

	    try {
	        PreparedStatement checkPstmt = cn.prepareStatement("SELECT abal FROM accounts WHERE ano = ?");
	        checkPstmt.setInt(1, ano);
	        java.sql.ResultSet rs = checkPstmt.executeQuery();

	        if (rs.next()) {
	            float currentBal = rs.getFloat("abal");

	            if (currentBal < amount) {
	                
	                response.sendRedirect("insufficient.html"); 
	            } 
	            else {
	                PreparedStatement pstmt = cn.prepareStatement("UPDATE accounts SET abal = abal - ? WHERE ano = ?");
	                pstmt.setFloat(1, amount);
	                pstmt.setInt(2, ano);
	                
	                int rows = pstmt.executeUpdate();
	                if (rows > 0) {
	                    response.sendRedirect("withdraw-success.html");
	                } else {
	                    response.sendRedirect("withdraw.html");
	                }
	            }
	        } else {
	            response.sendRedirect("withdraw.html");
	        }
	    }catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
