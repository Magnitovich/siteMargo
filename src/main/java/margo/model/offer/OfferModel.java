package margo.model.offer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="OFFER", schema = "public")
public class OfferModel {
    @Column(name = "CUSTOMER_ID", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "ID_FROM_COMMODITY", nullable = false)
    private Long idFromCommodity;
    @Column(name = "PHOTO")
    private String photo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "PERCENT_OF_DISCOUNT")
    private Double percent;
    @Column(name = "DATE_ODER")
    private Date oderDate;
    @Column(name = "OFFER")
    private Boolean offer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFromCommodity() {
        return idFromCommodity;
    }

    public void setIdFromCommodity(Long idFromCommodity) {
        this.idFromCommodity = idFromCommodity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    public Boolean getReciveOrder() {
        return offer;
    }

    public void setReciveOrder(Boolean offer) {
        this.offer = offer;
    }
}
