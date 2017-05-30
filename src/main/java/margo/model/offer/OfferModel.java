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
    @Column(name = "PRICE_BEFORE_DISCOUNT")
    private BigDecimal priceBefore;
    @Column(name = "DATE_ODER")
    private Date oderDate;
    @Column(name = "DATE_FINISH_OFFER")
    private Date dateFinishOffer;
    @Column(name = "OFFER")
    private Boolean offer;
    @Column(name = "PART")
    private String part;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Date getDateFinishOffer() {
        return dateFinishOffer;
    }

    public void setDateFinishOffer(Date dateFinishOffer) {
        this.dateFinishOffer = dateFinishOffer;
    }

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

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

    public BigDecimal getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(BigDecimal priceBefore) {
        this.priceBefore = priceBefore;
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
