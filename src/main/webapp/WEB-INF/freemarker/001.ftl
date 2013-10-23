<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="/invoice/resources/admin/css/001.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="invoiceContent">
    <div class="right">Datas wystawienia: ${invoice.date!""}</div><br /><br />
    <h3>Faktura VAT nr ${invoice.no!""}</h3>
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
            ${invoice.client.name!""}<br />
            ${invoice.client.address!""}<br />
            ${invoice.client.zipCode!""} ${invoice.client.city!""}<br />
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
            <#list invoice.invoiceItems as invoiceItem>
            <tr>
                <td></td>
                <td>${invoiceItem.getItem().name!""}</td>
                <td>${invoiceItem.getItem().amount!0}</td>
                <td>${invoiceItem.getItem().tax!0} </td>
                <td>taxable at..</td>
                <td>${invoiceItem.getItem().amount!0}</td>
            </tr>
            </#list>
            <tr>
                <td colspan="2" class="summary">TOTAL:</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>

        <#if invoice.note??>
            <div class="note">
                <strong>Note:</strong><br />
                ${invoice.note}<br />
            </div>
        </#if>

        <div class="payment">
            ${invoice.payment!""}
        </div>
    </div>
</div>
</body>
</html>
