<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@page import="java.util.* , com.example.SpringBootProject.model.* "%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>
    
    Users : 

    <hr>
        
    </table>

    <%
        if (request.getAttribute("users") != null) {

            List<User> users = (List<User>) request.getAttribute("users") ;

            for (User item : users) {

            %>
                <tr>
                    <td><%= item.getId() %></td>
                    <td><%= item.getName() %></td>
                    <td><%= item.getEmail() %></td>
                </tr>
                <br>
            <%

            }

        }
    %>

    <table>

    <br>
    <h1>This is home page</h1>
</body>
</html>