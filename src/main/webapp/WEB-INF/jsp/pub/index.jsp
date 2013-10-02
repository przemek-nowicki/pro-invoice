<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <div>
            <h1>Home page :: Welcome</h1><br/>
            <sec:authorize access="isAnonymous()">
                <p>
                    <a href="<c:url value='/admin/'/>">Sign In</a>
                </p>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <p>Hello, ${userDetails.username}! <a href="<c:url value='/j_spring_security_logout'/>">Sign Out</a></p>
            </sec:authorize>

            <sec:authorize access="hasRole('ADMIN_ROLE')">
                <p>
                    <a href="<c:url value="/admin/users/"/>">Manage users</a>
                </p>
                <p>User authorities: ${userAuthorities}</p>
            </sec:authorize>
        </div>
    </body>
</html>