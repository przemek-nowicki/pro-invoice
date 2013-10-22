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
    #sortable, #adder { list-style-type: none; margin: 0; padding: 0; width: 60%; }
    #sortable li, #adder li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
    #sortable li span, #adder li span { position: absolute; margin-left: -1.3em; }
    #addItem { font-color:#555555;text-align:center;vertical-align:middle;background-color:#e6e6e6;border: 1px solid #555555;width:200px;height:25px;cursor: pointer;}
</style>

<script>
    var counter=0;
    function addRow(nameVal,amountVal,positionVal) {
        var row=$('<span class="ui-icon ui-icon-arrowthick-2-n-s"></span> Name: <input type="text" name="invoiceItems['+counter+'].item.name" value="'+nameVal+'"/> Amount: <input type="text" name="invoiceItems['+counter+'].item.amount" value="'+amountVal+'"/> <input type="hidden" name="invoiceItems['+counter+'].position" value="'+positionVal+'" class="currentposition"/> <input type="hidden" name="invoiceItems['+counter+'].removed" value="0"/> <a href="#" onclick="deleteRow('+counter+')">X</a>');
        var $li = $("<li class='ui-state-default' id='item-"+counter+"'/>").append(row);
        $("#sortable").append($li);
        $("#sortable").sortable('refresh');
        counter++;
    }

    function deleteRow(itemNo) {
        $("input[name='invoiceItems["+itemNo+"].removed']").val(1);
        console.log($("#item-"+itemNo));
        $("#item-"+itemNo).hide();
    }

    $(function() {
        $( "#sortable" ).sortable({
            start: function(event, ui) {
                ui.item.startPos = ui.item.index();
            },
            stop: function(event, ui) {
                var inputs = $('input.currentposition');
                var countOfElems=0;
                $('input.currentposition').each(function(idx) {
                    $(this).val(countOfElems + idx);
                });
            }
        });
        $( "#sortable" ).disableSelection();

        <c:forEach var="invoiceItem" items="${invoice.invoiceItems}" varStatus="index">
            addRow("${invoiceItem.item.name}",${invoiceItem.item.amount},${invoiceItem.position});
        </c:forEach>

        $( "#addItem" ).click(function() {
            var nameVal = $("input[name='name']").val();
            var amountVal = $("input[name='amount']").val();
            var positionVal = $("ul li:last-child").find("input:eq(2)").val();
            positionVal = (positionVal==undefined ? 0 : parseInt(positionVal)+1);
            addRow(nameVal,amountVal,positionVal);
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
    <div id="addItem">Add new item to </div> <br />
    <miwu:selectField name="currencySymbol" label="Currency symbol" names="${symbols}" size="1" itemLabel="symbol" itemValue="name"/>
    <miwu:selectField name="currencyCode" label="Currency code" names="${codes}" size="1" itemLabel="code" itemValue="name"/>
    <br />
    Payment details:
    <form:textarea path="payment"/><br />
    Note:
    <form:textarea path="note"/><br />
    <input type="submit"/>
</form:form>