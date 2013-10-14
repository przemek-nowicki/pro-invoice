package pl.miwu.invoice.model;

import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;
import pl.miwu.invoice.util.invoice.Status;

import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:14
 */

public class Invoice {
    private Integer id;
    private String no;
    private Date date;
    private Date due;
    private Set<InvoiceItem> items;
    private Client client;
    private Status status;
    private boolean reminder;
    private CurrencySymbol currencySymbol;
    private CurrencyCode currencyCode;
    private String note;
    private String payment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Set<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(Set<InvoiceItem> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    public CurrencySymbol getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(CurrencySymbol currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
