<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="book.business.Customer"%>
<% 
       // get the info 
       String info = (String) request.getAttribute("info");
       // if info is not null, display the info
       if (info != null) {
           out.println(info);
       }
       // get the customer
       Customer customer = (Customer) session.getAttribute("customer");
%>
<!doctype html>

<html>
<head profile="http://www.w3.org/2005/10/profile">
    <meta charset="utf-8">
    <title>CDOFF!</title>
    <link rel="shortcut icon" href="<c:url value='/images/favorite_icon.ico'/>">
    <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>

<body>

    <header>
        
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="<c:url value='/images/logo.png'/>" 
             alt="CDOFF CatHead Logo" width="205">
        </a>
        
        <h1>CDOFF Book Store</h1>
        <h2>Same books, better prices!</h2>
    </header>
    <nav id="nav_bar">
        <ul>
            <li>Welcome <%=customer.getFirstName() %>!</li>
            <li><a href="<c:url value='/order/showCart'/>">
                    Show Cart</a></li>
            <li><a href="<c:url value='/admin'/>">
                    Admin</a></li>
            <li><a href="<c:url value='/user/deleteCookies'/>">
                   Delete Cookie</a></li>
        </ul>
    </nav>