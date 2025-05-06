<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/index.css?v=2">
    </head>

    <body>
        <form action="UserServlet" method="post">
            <h2>Login Form</h2>
            <input type="text" name="name" id="name" placeholder="Full Name" required>
            <input type="gmail" name="gmail" id="gmail" placeholder="gmail" required>
            <input type="password" onclick="togglePassword()" name="password" id="password" placeholder="Password"
                required>
                 <input type="consumer_id" name="consumer_id" id="consumer_id" placeholder="consumer_id" required>
            <select id="gender" name="gender" required>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
            <input type="tel" name="contact" id="contact" placeholder="Contact Number" required>
            <button type="submit" value="login">Login</button>
        </form>
        <script>
            function togglePassword() {
                var pw = document.getElementById("password");
                pw.type = pw.type === "password" ? "text" : "password";
            }
        </script>
    </body>

    </html>