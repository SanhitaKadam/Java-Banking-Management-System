package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Accountc
 */
public class Accountc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accountc() {
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
		String aname = request.getParameter("aname");
	    String amobile = request.getParameter("amobile");
	    String acity = request.getParameter("acity");
	    String abal = request.getParameter("abal");
	    
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    Connection cn = Connectivity.connect();

	    try {
	        
	        PreparedStatement pstmt = cn.prepareStatement(
	                "INSERT INTO accounts (aname, amobile, acity, abal, email, password) VALUES (?, ?, ?, ?, ?, ?)");
	        
	        pstmt.setString(1, aname);
	        pstmt.setString(2, amobile);
	        pstmt.setString(3, acity);
	        pstmt.setString(4, abal);
	        pstmt.setString(5, email);  
	        pstmt.setString(6, password); 

	        int i = pstmt.executeUpdate(); 

	        if (i > 0) {
	            response.sendRedirect("acc-created-success.html");
	        } else {
	            response.sendRedirect("create-account.html");
	        }

	        

        	} catch (Exception e) {
        			e.printStackTrace();
            
        }
	}

}
