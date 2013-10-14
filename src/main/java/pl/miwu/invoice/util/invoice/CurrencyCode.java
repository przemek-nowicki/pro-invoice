package pl.miwu.invoice.util.invoice;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 14.10.13
 * Time: 20:15
 */

public enum CurrencyCode {

    PLN("PLN","Poland Zloty"),
    USD("USD","United States Dollar"),
    GBP("GBP","United Kingdom Pound");

    private String label;
    private String description;

    CurrencyCode(String label, String description) {
        this.label=label;
        this.description=description;
    }
}
