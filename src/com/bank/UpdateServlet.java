package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
        String name = request.getParameter("aname");
        String mobile = request.getParameter("amobile");
        String city = request.getParameter("acity");

        try {
            Connection cn = Connectivity.connect();
            // Query jo saara data update karegi
            String sql = "UPDATE accounts SET aname=?, amobile=?, acity=? WHERE ano=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setString(3, city);
            ps.setInt(4, ano);
            
            ps.executeUpdate();
            response.sendRedirect("view-account.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
