package pl.miwu.invoice.util.invoice;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 14.10.13
 * Time: 19:45
 */

public enum CurrencySymbol {

    POUND("£","Pound"),
    DOLLAR("$", "Dollar"),
    EURO("€", "Euro");

    private String symbol;
    private String description;

    CurrencySymbol(String symbol, String description) {
        this.symbol=symbol;
        this.description=description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
