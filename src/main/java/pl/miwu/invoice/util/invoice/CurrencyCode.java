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

    private String code;
    private String description;

    CurrencyCode(String code, String description) {
        this.code=code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName(){
        return name();
    }
}
