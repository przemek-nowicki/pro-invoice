<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 21.09.13
  Time: 09:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="miwu" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../includes/top.jsp"%>

<spring:message code='admin.users.createOrUpdate.username' var="labelUsername"/>
<spring:message code='admin.users.createOrUpdate.email' var="labelEmail"/>
<spring:message code='admin.users.createOrUpdate.password' var="labelPassword"/>
<spring:message code='admin.users.createOrUpdate.userRole' var="labelRole"/>
<spring:message code='admin.users.list.account' var="labelEnabled"/>
<spring:message code='admin.users.createOrUpdate.save' var="saveButton"/>
<spring:message code='tags.selectField.firstOptionLabel' var="firstOptionLabel"/>


<form:form modelAttribute="user" method="PUT">
    <miwu:selectField name="userRole" label="${labelRole}" names="${roles}" itemLabel="name" itemValue="id" size="2"/>
    <miwu:inputField name="username" label="${labelUsername}"/>
    <miwu:inputField name="email" label="${labelEmail}"/>
    <c:if test="${empty user.id}">
        <miwu:passwordField name="password" label="${labelPassword}"/>
    </c:if>
    <miwu:checboxField name="enabled" label="${labelEnabled}"/>
    <button type="submit">${saveButton}</button>
</form:form>




