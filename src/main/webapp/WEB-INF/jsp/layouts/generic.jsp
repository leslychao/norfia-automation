<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"/>
</head>
<body>
<security:authorize access="isAuthenticated()">
    <tiles:insertAttribute name="header"/>
</security:authorize>
<div class="row align-items-center justify-content-center">
    <tiles:insertAttribute name="body"/>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>
