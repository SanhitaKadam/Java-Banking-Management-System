package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Connection cn = Connectivity.connect();
		
		try{
			PreparedStatement pstmt=cn.prepareStatement("Select * from accounts where email=? and password=?");
		    pstmt.setString(1, email);
		    pstmt.setString(2, password);
		    ResultSet rs=pstmt.executeQuery();
		    int i=0;
		    while(rs.next()){
		    	int ano=rs.getInt(1);
		    	i=1;
		    }
		    if(i>0){
		    	response.sendRedirect("home.html");
		    }
		    else{
		    	response.sendRedirect("index.html");
		    }
		    
		}
		catch(SQLException e){
			 e.printStackTrace();
		}
		
	}

}
