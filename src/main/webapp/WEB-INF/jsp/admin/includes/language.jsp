<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 20.09.13
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="admin.includes.language.select"/>
<a href="<c:url value="/?lang=en"/>"><spring:message code="admin.includes.language.en"/></a>
<a href="<c:url value="/?lang=pl"/>"><spring:message code="admin.includes.language.pl"/></a>