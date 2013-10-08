<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="miwu" tagdir="/WEB-INF/tags/pub" %>

<a href="<c:url value='/'/>">Home</a><br />
<c:forEach var="page" items="${pages}">
    <a href="<miwu:pageUrl page="${page}"/>">${page.title}</a><br />
</c:forEach>