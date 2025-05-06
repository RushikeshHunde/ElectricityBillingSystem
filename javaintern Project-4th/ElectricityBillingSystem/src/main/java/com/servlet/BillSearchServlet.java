package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@SuppressWarnings("serial")
public class BillSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    	 String consumerId = request.getParameter("consumer_id");
         request.setAttribute("consumer_id", consumerId);

        RequestDispatcher rd = request.getRequestDispatcher("BillDisplay.jsp");
        rd.forward(request, response);
    }
}
