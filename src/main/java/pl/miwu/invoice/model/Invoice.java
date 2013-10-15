package pl.miwu.invoice.model;

import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;
import pl.miwu.invoice.util.invoice.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.10.13
 * Time: 11:14
 */

@Table(name = "invoices")
@Entity
public class Invoice {
    private Integer id;
    private String no;
    private Date date;
    private Date due;
    private Set<Item> items;
    private Client client;
    private Status status;
    private CurrencySymbol currencySymbol;
    private CurrencyCode currencyCode;
    private String payment;
    private String note;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="no")
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Column(name="creation_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="due_date")
    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="invoice_item", joinColumns = {@JoinColumn(name="id_invoice",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name="id_item",referencedColumnName = "id")})
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @ManyToOne
    @JoinColumn(name="id_client")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "currency_symbol")
    @Enumerated(EnumType.STRING)
    public CurrencySymbol getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(CurrencySymbol currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Column(name = "payment")
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
