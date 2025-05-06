<%@ page import="java.util.*, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consumer History</title>
    <link rel="stylesheet" href="css/MainPage.css">
</head>
<body>
    <h2>Consumer History</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gmail</th>
            <th>Password</th>
            <th>Consumer ID</th>
            <th>Gender</th>
            <th>Contact</th>
        </tr>

        <%
            List<Map<String, String>> history = (List<Map<String, String>>) request.getAttribute("history");
            if (history != null && !history.isEmpty()) {
                for (Map<String, String> row : history) {
        %>
        <tr>
            <td><%= row.get("id") %></td>
            <td><%= row.get("name") %></td>
            <td><%= row.get("gmail") %></td>
            <td><%= row.get("password") %></td>
            <td><%= row.get("consumer_id") %></td>
            <td><%= row.get("gender") %></td>
            <td><%= row.get("contact") %></td>
        </tr>
        <% 
                } 
            } else { 
        %>
        <tr><td colspan="7">No history found for this Consumer ID.</td></tr>
        <% } %>
    </table>
</body>
</html>
