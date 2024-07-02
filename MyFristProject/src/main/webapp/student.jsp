<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<h2>Marks</h2>
    <h3>Tamil:</h3>
    <%= session.getAttribute("u_name") %>
    <h4>Maths:</h4>
    <%= session.getAttribute("u_name1") %>
    <form action="logout.html" method="post">
        <button type="submit">Logout</button>
    </form>
</div>
</body>
<style>
div{
height: 260px;
width: 100px;
padding:30px;
border: 2px solid black;
background-color: yellow;
}
body{
height: 150px;
width: 200px;
padding-bottom:100px;
padding-left:600px;
background-color: orange;
}
button{
background-color: teal;
}
</style>
</html>