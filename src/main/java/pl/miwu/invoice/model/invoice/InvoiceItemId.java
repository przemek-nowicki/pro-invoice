package pl.miwu.invoice.model.invoice;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Composite primary key class
 *
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 17.10.13
 * Time: 11:30
 */

@Embeddable
public class InvoiceItemId implements Serializable {

    private Invoice invoice;
    private Item item;
    public InvoiceItemId(){};

    @ManyToOne
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @ManyToOne
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceItemId that = (InvoiceItemId) o;

        if (!invoice.equals(that.invoice)) return false;
        if (!item.equals(that.item)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoice != null ? invoice.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}