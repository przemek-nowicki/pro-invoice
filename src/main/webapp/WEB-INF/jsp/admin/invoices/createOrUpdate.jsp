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

<form>
    Date of invoice: <input type="text" name="date"/><br />
    Invoice no: <input type="text" name="no"/><br />
    Select client:
    <select>
     <c:forEach var="client" items="${clients}">
        <option>${client.name}</option>
     </c:forEach>
    </select><br />
    New client<br />
    <ul></ul>

</form>



