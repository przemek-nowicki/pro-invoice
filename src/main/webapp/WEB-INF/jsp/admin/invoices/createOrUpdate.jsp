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

<style>
    #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
    #sortable li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
    #sortable li span { position: absolute; margin-left: -1.3em; }
</style>

<script>
    var counter=0;

    function addRow(nameVal,amountVal) {
        var row=$('<span class="ui-icon ui-icon-arrowthick-2-n-s"></span> Name: <input type="text" name="items['+counter+'].name" value="'+nameVal+'"/> Amount: <input type="text" name="items['+counter+'].amount" value="'+amountVal+'"/>');
        var $li = $("<li class='ui-state-default'/>").append(row);
        $("#sortable").append($li);
        $("#sortable").sortable('refresh');
        counter++;
    }

    function deleteRow()

    $(function() {
        $( "#sortable" ).sortable();
        $( "#sortable" ).disableSelection();

        <c:forEach var="item" items="${invoice.items}" varStatus="index">
            addRow("${item.name}",${item.amount});
        </c:forEach>

        $( "#addItem" ).click(function() {
            var nameVal = $("input[name='name']").val();
            var amountVal = $("input[name='amount']").val();
            addRow(nameVal,amountVal);
        });
    });
</script>

<form:form modelAttribute="invoice" method="post">
    <miwu:inputField name="date" label="Date of invoice"/>
    <miwu:inputField name="due" label="Due"/>
    <miwu:inputField name="no" label="Invoice no"/>
    <miwu:selectField name="client" label="Select client" names="${clients}" itemLabel="name" itemValue="id" size="1"/>
    Items:<br />

    <ul id="sortable"></ul><br />
    <ul id="adder">
        <li class="ui-state-default">Name: <form:input path="" name="name"/> Amount: <form:input path="" name="amount"/></li>
    </ul>
    <div id="addItem">Add new item</div> <br />
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