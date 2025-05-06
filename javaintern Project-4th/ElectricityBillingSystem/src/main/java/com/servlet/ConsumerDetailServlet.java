package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@SuppressWarnings("serial")
public class ConsumerDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String consumerId = request.getParameter("consumer_id");

        // Create a list to store multiple rows of results
        List<Map<String, String>> history = new ArrayList<>();

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/billing_db", "root", "#rushi@2183#");

            // Prepare and execute SQL statement
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE consumer_id = ?");
            pst.setString(1, consumerId);
            ResultSet rs = pst.executeQuery();

            // Loop through all matching rows
            while (rs.next()) {
                Map<String, String> record = new HashMap<>();
                record.put("id", String.valueOf(rs.getInt("id")));
                record.put("name", rs.getString("name"));
                record.put("gmail", rs.getString("gmail"));
                record.put("password", rs.getString("password"));
                record.put("consumer_id", rs.getString("consumer_id"));
                record.put("gender", rs.getString("gender"));
                record.put("contact", rs.getString("contact"));
                history.add(record);
            }

            // Pass the list to the JSP
            request.setAttribute("history", history);
            RequestDispatcher rd = request.getRequestDispatcher("consumer_details.jsp");
            rd.forward(request, response);

            // Close connection
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
