package com.servlet;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@SuppressWarnings("serial")
public class BillingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	
    	Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        String consumerId = request.getParameter("consumer_id");
        String name = request.getParameter("name");
        int units = Integer.parseInt(request.getParameter("units"));
        int rate = Integer.parseInt(request.getParameter("rate"));

        int totalCost = units * rate;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/billing_db", "root", "#rushi@2183#");

            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO bills (consumer_id, name, units, rate, total_cost, bill_date) VALUES (?, ?, ?, ?, ?, ?)"
            );

            pst.setString(1, consumerId);
            pst.setString(2, name);
            pst.setInt(3, units);
            pst.setInt(4, rate);
            pst.setInt(5, totalCost);
            pst.setTimestamp(6, timestamp);

            pst.executeUpdate();
            con.close();

            request.setAttribute("totalCost", totalCost);
            request.setAttribute("consumer_id", consumerId);
            RequestDispatcher rd = request.getRequestDispatcher("BillDisplay.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
