<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 10.08.13
  Time: 11:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="miwu" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../includes/top.jsp"%>

<spring:message code='admin.pages.createOrUpdate.title' var="labelTitle"/>
<spring:message code='admin.pages.createOrUpdate.url' var="labelUrl"/>
<spring:message code='admin.pages.createOrUpdate.body' var="labelBody"/>
<spring:message code='admin.pages.createOrUpdate.metaKeywords' var="labelKeywords"/>
<spring:message code='admin.pages.createOrUpdate.metaDescription' var="labelDescription"/>
<spring:message code='admin.pages.createOrUpdate.save' var="saveButton"/>


<form:form modelAttribute="page" method="PUT">
    <miwu:inputField name="metaKeywords" label="${labelKeywords}"/>
    <miwu:inputField name="metaDescription" label="${labelDescription}"/>
    <miwu:inputField name="title" label="${labelTitle}"/>
    <miwu:inputField name="url" label="${labelUrl}"/>
    <miwu:textareaField name="body" label="${labelBody}"/>
    <button type="submit">${saveButton}</button>
</form:form>




