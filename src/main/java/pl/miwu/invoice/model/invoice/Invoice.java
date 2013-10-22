package pl.miwu.invoice.model.invoice;

import org.springframework.util.AutoPopulatingList;
import pl.miwu.invoice.model.Client;
import pl.miwu.invoice.util.invoice.CurrencyCode;
import pl.miwu.invoice.util.invoice.CurrencySymbol;
import pl.miwu.invoice.util.invoice.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private List<InvoiceItem> invoiceItems = new AutoPopulatingList(Invoice.class);
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoiceItemId.invoice",cascade = CascadeType.ALL)
    @OrderBy("position ASC")
    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != null ? !id.equals(invoice.id) : invoice.id != null) return false;
        if (no != null ? !no.equals(invoice.no) : invoice.no != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (no != null ? no.hashCode() : 0);
        return result;
    }
}
