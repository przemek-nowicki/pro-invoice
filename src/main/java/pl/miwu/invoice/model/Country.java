package pl.miwu.invoice.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 15.10.13
 * Time: 12:14
 */

@Table(name = "countries")
@Entity
public class Country {

    private Integer id;
    private String shortName;
    private String longName;

    @javax.persistence.Column(name = "id")
    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name="long_name")
    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
