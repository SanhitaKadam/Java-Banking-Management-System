package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		//doGet(request, response);
		int sender = Integer.parseInt(request.getParameter("saccno"));
	    int receiver = Integer.parseInt(request.getParameter("raccno"));
	    float amount = Float.parseFloat(request.getParameter("tamount"));

	    Connection cn = Connectivity.connect();

	    try {
	        
	        PreparedStatement psCheck = cn.prepareStatement("SELECT abal FROM accounts WHERE ano = ?");
	        psCheck.setInt(1, sender);
	        java.sql.ResultSet rs = psCheck.executeQuery();

	        if (rs.next()) {
	            float bal = rs.getFloat("abal");

	            
	            if (bal < amount) {
	                
	                response.sendRedirect("insufficient.html"); 
	            } 
	            else {
	               
	                PreparedStatement ps1 = cn.prepareStatement("UPDATE accounts SET abal = abal - ? WHERE ano = ?");
	                ps1.setFloat(1, amount);
	                ps1.setInt(2, sender);
	                int r1 = ps1.executeUpdate();



	                PreparedStatement ps2 = cn.prepareStatement("UPDATE accounts SET abal = abal + ? WHERE ano = ?");
	                ps2.setFloat(1, amount);
	                ps2.setInt(2, receiver);
	                int r2 = ps2.executeUpdate();

	                // Success check
	                if (r1 > 0 && r2 > 0) {
	                    response.sendRedirect("tranfered.html");
	                } else {
	                    response.sendRedirect("transfer.html"); 
	                }
	            }
	        } else {
	            
	            response.sendRedirect("transfer.html");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
	}
