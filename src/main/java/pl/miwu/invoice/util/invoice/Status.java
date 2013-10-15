package pl.miwu.invoice.util.invoice;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 14.10.13
 * Time: 19:24
 */

public enum Status {
    DRAFT(1,"Draft"),
    DUE(2,"Due"),
    PAID(3,"Paid");

    private Integer id;
    private String label;

    Status(Integer id, String label) {
        this.id=id;
        this.label=label;
    }
}
