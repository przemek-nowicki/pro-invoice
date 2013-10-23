<%--
  Created by IntelliJ IDEA.
  User: Przemek Nowicki (nowicki.przemek@gmail.com)
  Date: 23.10.13
  Time: 09:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/admin/css/001.css"/>" rel="stylesheet" type="text/css" />

<div id="invoiceContent">
    <div class="right">Data wystawienia: <c:out value="${invoice.date}"/></div><br /><br />
    <h3>Faktura VAT nr <c:out value="${invoice.no}"/></h3>
    <div class="parties">
        <div class="left">
            <strong>Sprzedawca:</strong><br />
            Make It With Us, Przemysław Nowicki<br />
            Ul Zielona 3<br />
            96-500 Sochaczew<br />
            NIP: 837-162-45-61<br />
        </div>
        <div class="right">
            <strong>Nabywca:</strong><br />
            <c:out value="${invoice.client.name}"/><br />
            <c:out value="${invoice.client.address}"/><br />
            <c:out value="${invoice.client.zipCode}"/> <c:out value="${invoice.client.city}"/><br />
         </div>
    </div>
    <div class="purchasedItems">
        <table>
            <tr>
                <th>No.</th>
                <th>Nazwa i rodzaj usługi</th>
                <th>Wartość netto</th>
                <th colspan="2">VAT</th>
                <th>Wartość brutto</th>
            </tr>
            <c:forEach var="invoiceItem" items="${invoice.invoiceItems}" varStatus="index">
            <tr>
                <td></td>
                <td>${invoiceItem.item.name}</td>
                <td>${invoiceItem.item.amount}</td>
                <td>${invoiceItem.item.tax}11</td>
                <td>taxable at..</td>
                <td>${invoiceItem.item.amount}</td>
            </tr>
            </c:forEach>
            <tr>
                <td colspan="2" class="summary">TOTAL:</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>

        <c:if test="${not empty invoice.note}">
            <div class="note">
                <strong>Note:</strong><br />
                <c:out value="${invoice.note}"/><br />
            </div>
        </c:if>

        <div class="payment">
            <c:out value="${invoice.payment}"/>
        </div>
    </div>
</div>