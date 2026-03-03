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
 * Servlet implementation class View
 */
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
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
		
		int ano = Integer.parseInt(request.getParameter("ano"));
        
        try {
            Connection cn = Connectivity.connect();
            PreparedStatement ps = cn.prepareStatement("SELECT ano, aname, amobile, acity, abal FROM accounts WHERE ano = ?");
            ps.setInt(1, ano);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("acc_data", rs); // Password nahi bheja
            } else {
                request.setAttribute("error", "Account " + ano + " not found!");
            }
            
            // Wapas usi JSP par data ke saath bhej do
            request.getRequestDispatcher("view-account.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
