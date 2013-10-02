<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 17.09.13
  Time: 14:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="miwu" tagdir="/WEB-INF/tags/admin" %>

<spring:message code='admin.login.submit' var="submitButton"/>

<html>
<head>
    <title><spring:message code="admin.login.title"/></title>
    <%--<link href="<c:url value="/resources/admin/css/style.css"/>" rel="stylesheet" type="text/css" />--%>
</head>
<body onload='document.f.j_username.focus();'>

<c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}">
   <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
    <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>

<form method="post" action="<c:url value='/j_spring_security_check' />">
  <div id="passwordLoginOption" class="form">
      <div class="row">
          <div class="label left">
              <label for="j_username"><spring:message code="admin.login.username"/>:</label>
          </div>
          <div class="right">
              <div class="textWrapper">
                  <input type="text" id="j_username" name="j_username"/>
              </div>
          </div>
          <div class="cl"></div>
      </div>
      <div class="row">
          <div class="label left">
              <label for="j_password"><spring:message code="admin.login.password"/>:</label>
          </div>
          <div class="right">
              <div class="textWrapper">
                  <input type="password" id="j_password" name="j_password"/>
              </div>
          </div>
          <div class="cl"></div>
      </div>
      <div class="row">
          <div class="right">
              <label class="forCheckbox" for='_spring_security_remember_me'>
                  <spring:message code="admin.login.rememberMe"/>:
                  <input type='checkbox'id="_spring_security_remember_me" name='_spring_security_remember_me'/>
              </label>
          </div>
          <div class="cl"></div>
      </div>
      <div class="buttons">
          <input type="submit" value="${submitButton}"/>
      </div>
  </div>
</form>
</body>
</html>