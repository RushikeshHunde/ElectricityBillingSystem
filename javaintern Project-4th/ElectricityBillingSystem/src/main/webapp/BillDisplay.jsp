<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Electricity Bill</title>
    <link rel="stylesheet" href="css/billing.css">
</head>
<body>
    <h2>Electricity Bill Details</h2>
    <form method="post" action="BillSearchServlet" style="text-align:center;">
        <input type="text" name="consumer_id" placeholder="Search by Consumer ID" required>
        <button type="submit">Search</button>
    </form>

    <%
        String consumerId = (String) request.getAttribute("consumer_id");
        if (consumerId != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/billing_db", "root", "#rushi@2183#");
                PreparedStatement pst = con.prepareStatement("SELECT * FROM bills WHERE consumer_id = ?");
                pst.setString(1, consumerId);
                ResultSet rs = pst.executeQuery();
    %>
    <table>
        <tr>
            <th>Consumer ID</th>
            <th>Name</th>
            <th>Units</th>
            <th>Rate</th>
            <th>Total Cost</th>
            <th>Bill Date</th>
        </tr>
        <%
            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("consumer_id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getInt("units") %></td>
            <td><%= rs.getInt("rate") %></td>
            <td><%= rs.getInt("total_cost") %></td>
            <td><%= rs.getTimestamp("bill_date") %></td>
        </tr>
        <%
            }
            con.close();
        } catch (Exception e) {
            out.println("Error fetching bills: " + e.getMessage());
        }
    }
        %>
    </table>
</body>
</html>
