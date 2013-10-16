<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 20.09.13
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="admin.includes.top.title"/></title>
    <link href="<c:url value="/resources/admin/css/style.css"/>" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
<h1><spring:message code="admin.includes.top.title"/></h1>
<table>
<tr>
    <td><%@include file="language.jsp"%></td>
    <td class="right"><a href="<c:url value='/j_spring_security_logout'/>">
        <spring:message code="admin.includes.top.logout"/>
    </a></td>
</tr>
<tr>
    <td colspan="2"><%@ include file="menu.jsp"%></td>
</tr>
</table>