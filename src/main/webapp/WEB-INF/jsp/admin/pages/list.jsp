<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 07.10.13
  Time: 15:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="../includes/top.jsp" %>

+ <a href="<c:url value="/admin/page/create"/>"><spring:message code="admin.pages.list.createPage"/></a>
<table border="1">
    <tr>
        <th><spring:message code="admin.pages.list.id"/></th>
        <th><spring:message code="admin.pages.list.title"/></th>
        <th><spring:message code="admin.pages.list.body"/></th>
        <th><spring:message code="admin.pages.list.actions"/></th>
    </tr>
    <c:forEach var="page" items="${pages}">
    <tr>
        <td>${page.id}</td>
        <td>${page.title}</td>
        <td>${fn:substring(page.body, 0, 100)}</td>
        <td><a href="<c:url value='/admin/page/edit/${page.id}'/>"><spring:message code="admin.pages.list.edit"/></a> | <a href="<c:url value='/admin/page/delete/${page.id}'/>"><spring:message code="admin.pages.list.delete"/></a> | <a href="<c:url value='/page/${page.url}'/>"><spring:message code="admin.pages.list.view"/></a></td>
    </tr>
    </c:forEach>
</table>

<%@ include file="../includes/bottom.jsp" %>