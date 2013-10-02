<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 18.09.13
  Time: 14:35
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="../includes/top.jsp" %>
+ <a href="<c:url value="/admin/user/create"/>"><spring:message code="admin.users.list.createAccount"/></a>
<table border="1">
    <tr>
        <th><spring:message code="admin.users.list.id"/></th>
        <th><spring:message code="admin.users.list.username"/></th>
        <th><spring:message code="admin.users.list.email"/></th>
        <th><spring:message code="admin.users.list.userRole"/></th>
        <th><spring:message code="admin.users.list.account"/></th>
        <th><spring:message code="admin.users.list.actions"/></th>
    </tr>
    <c:forEach var="user" items="${users}">
    <tr>
        <td><a href="<c:url value='/admin/user/edit/${user.id}'/>">${user.id}</a></td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.userRole.name}</td>
        <td>
            <c:if test="${user.enabled eq true}">
                <spring:message code="admin.users.list.enabled"/>
            </c:if>
            <c:if test="${user.enabled eq false}">
                <spring:message code="admin.users.list.disabled"/>
            </c:if>
        </td>
        <td><a href="<c:url value='/admin/user/edit/${user.id}'/>"><spring:message code="admin.users.list.edit"/></a> | <a href="<c:url value='/admin/user/delete/${user.id}'/>"><spring:message code="admin.users.list.delete"/></a></td>
    </tr>
    </c:forEach>
</table>

<%@ include file="../includes/bottom.jsp" %>