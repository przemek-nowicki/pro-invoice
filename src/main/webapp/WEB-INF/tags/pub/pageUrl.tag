<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 23.09.13
  Time: 09:49
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" type="pl.miwu.invoice.model.Page" required="true" %>
<c:url value="/page/${page.url}"/>
