<%--
  Created by IntelliJ IDEA.
  User: Nowicki Przemek (nowicki.przemek@gmail.com)
  Date: 23.09.13
  Time: 11:04
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<a href="<c:url value='/admin/users'/>"><spring:message code="admin.includes.menu.users"/></a>
<a href="<c:url value="/admin/pages"/>"><spring:message code="admin.includes.menu.pages"/></a>
