package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @return 
	 * @see HttpServlet#HttpServlet()
     */
    public void Login() {
        //super();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int ano = Integer.parseInt(request.getParameter("ano"));
        float amount = Float.parseFloat(request.getParameter("amount"));

        Connection cn = Connectivity.connect();

        try {
            // Balance update query
            PreparedStatement pstmt = cn.prepareStatement("UPDATE accounts SET abal = abal + ? WHERE ano = ?");
            pstmt.setFloat(1, amount);
            pstmt.setInt(2, ano);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                // Success: deposited.html pe bhejo
                response.sendRedirect("deposited.html");
            } else {
                // Fail: wapas deposit.html pe bhejo (Spelling check: deposit)
                response.sendRedirect("deposite.html"); 
            }

        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
