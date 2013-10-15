<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 11.08.13
  Time: 16:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="miwu" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../includes/top.jsp"%>


<form:form modelAttribute="invoice" method="post">
    <miwu:inputField name="date" label="Date of invoice"/>
    <miwu:inputField name="due" label="Due"/>
    <miwu:inputField name="no" label="Invoice no"/>
    <miwu:selectField name="client" label="Select client" names="${clients}" itemLabel="name" itemValue="id" size="1"/>

    Items:<br />
    <ul>
        <c:forEach var="item" items="${invoice.items}" varStatus="index">
            <li>Name: <form:input path="items[${index.count - 1}].name"/> Amount: <form:input path="items[${index.count - 1}].amount"/></li>
        </c:forEach>
    </ul><br />
    Symbol:
    <select>
        <option>&nbsp;</option>
        <c:forEach var="symbol" items="${symbols}">
            <option>${symbol.symbol}</option>
        </c:forEach>
    </select><br />
    Code:
    <select>
        <option>&nbsp;</option>
        <c:forEach var="code" items="${codes}">
            <option>${code.code}</option>
        </c:forEach>
    </select><br />
    Payment details:
    <textarea name="payment"></textarea><br />
    Note:
    <textarea name="note"></textarea><br />
    <input type="submit"/>
</form:form>