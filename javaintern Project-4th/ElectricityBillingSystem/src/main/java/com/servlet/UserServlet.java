package com.servlet;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        try (Connection con = DBConnection.getConnection()) {

            // Check if consumer_id already exists
            PreparedStatement checkConsumer = con.prepareStatement("SELECT * FROM users WHERE consumer_id = ?");
            checkConsumer.setString(1, consumer_id);
            ResultSet rs1 = checkConsumer.executeQuery();

            if (rs1.next()) {
                // consumer_id exists — show error
                response.getWriter().println("Error: Consumer ID already exists!");
                return;
            }

            // Check if gmail and password combo already used
            PreparedStatement checkUser = con.prepareStatement(
                "SELECT * FROM users WHERE gmail = ? AND password = ?");
            checkUser.setString(1, gmail);
            checkUser.setString(2, password);
            ResultSet rs2 = checkUser.executeQuery();

            if (rs2.next()) {
                response.getWriter().println("Error: Gmail and password already associated with another user.");
                return;
            }

            // All good → insert user
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (name, gmail, password, consumer_id, gender, contact) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, gmail);
            ps.setString(3, password);
            ps.setString(4, consumer_id);
            ps.setString(5, gender);
            ps.setString(6, contact);
            ps.executeUpdate();

            // Optionally set session
            HttpSession session = request.getSession();
            session.setAttribute("consumer_id", consumer_id);
            session.setAttribute("name", name);

            // Redirect to main page
            response.sendRedirect("MainPage.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Internal Error: " + e.getMessage());
        }
    }
}
