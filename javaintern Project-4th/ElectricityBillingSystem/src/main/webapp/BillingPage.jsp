<!DOCTYPE html>
<html>
<head>
    <title>Calculate Electricity Bill</title>
    <link rel="stylesheet" href="css/billing.css">
</head>
<body>
    <h2>Electricity Billing Calculator</h2>
    <form action="BillingServlet" method="post">
        <input type="text" name="consumer_id" placeholder="Consumer ID" required>
        <input type="text" name="name" placeholder="Name" required>
        <input type="number" name="units" placeholder="Units Consumed" required>
        <input type="number" name="rate" placeholder="Rate per Unit" value="5" required>
        <button type="submit">Calculate AND Save Bill</button>
    </form>
</body>
</html>
