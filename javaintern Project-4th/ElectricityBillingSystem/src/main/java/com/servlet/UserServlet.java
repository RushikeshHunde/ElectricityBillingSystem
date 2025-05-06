package com.servlet;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String gmail = request.getParameter("gmail");
        String password = request.getParameter("password");
        String consumer_id = request.getParameter("consumer_id");
        String gender = request.getParameter("gender");
        String contact = request.getParameter("contact");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, gmail, password, consumer_id, gender, contact) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, gmail);
            ps.setString(3, password);
            ps.setString(4, consumer_id);
            ps.setString(5, gender);
            ps.setString(6, contact);
            ps.executeUpdate();

            con.close();
            response.sendRedirect("MainPage.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
