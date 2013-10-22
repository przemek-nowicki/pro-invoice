package pl.miwu.invoice.model.invoice;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 17.10.13
 * Time: 09:38
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoice_item")
@AssociationOverrides({
        @AssociationOverride(name = "invoiceItemId.invoice",
                joinColumns = @JoinColumn(name = "id_invoice",referencedColumnName = "id")),
        @AssociationOverride(name = "invoiceItemId.item",
                joinColumns = @JoinColumn(name = "id_item",referencedColumnName = "id"))})
public class InvoiceItem implements Serializable {

    private InvoiceItemId invoiceItemId = new InvoiceItemId();
    private Integer position;
    private boolean removed;


    @EmbeddedId
    public InvoiceItemId getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(InvoiceItemId invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    @Transient
    public Invoice getInvoice() {
        return getInvoiceItemId().getInvoice();
    }

    public void setInvoice(Invoice invoice) {
        getInvoiceItemId().setInvoice(invoice);

    }

    @Transient
    public Item getItem() {
        return getInvoiceItemId().getItem();
    }

    public void setItem(Item item) {
        getInvoiceItemId().setItem(item);
    }

    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Transient
    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceItem that = (InvoiceItem) o;

        if (!invoiceItemId.equals(that.invoiceItemId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return invoiceItemId != null ? invoiceItemId.hashCode() : 0;
    }
}
