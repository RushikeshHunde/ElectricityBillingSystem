<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Page</title>
     <link rel="stylesheet" type="text/css" href="css/DisplayPage.css">
</head>
<body>
    <h2>Registered Users</h2>
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
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/billing_db", "root", "#rushi@2183#");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users");

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("gmail") %></td>
            <td><%= rs.getString("password") %></td>
            <td><%= rs.getString("consumer_id") %></td>
            <td><%= rs.getString("gender") %></td>
            <td><%= rs.getString("contact") %></td>
        </tr>
        <%
                }
                con.close();
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            }
        %>
    </table>
</body>
</html>
