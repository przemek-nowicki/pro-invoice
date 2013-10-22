package pl.miwu.invoice.model.invoice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 14.10.13
 * Time: 16:40
 */

@Table(name = "items")
@Entity
public class Item {
    private Integer id;
    private String name;
    private Integer tax;
    private BigDecimal amount;
    private Set<Invoice> invoices;
    private Set<InvoiceItem> invoiceItems;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="tax")
    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    @Column(name="amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /*@ManyToMany(mappedBy = "items")
    public Set<InvoiceItem> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceItem> invoices) {
        this.invoices = invoices;
    } */

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoiceItemId.item")
    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
