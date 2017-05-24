package margo.model.accessories;

import margo.model.finishedProduct.AllFinishProductModel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FRINGE", schema = "public")
public class FringeModel extends AllFinishProductModel {

    @Column(name = "PHOTO")
    private String photo;
    @Column(name = "PHOTO01")
    private String photo01;
    @Column(name = "PHOTO02")
    private String photo02;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "OFFER")
    private Boolean offer;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "PRICE")
    private BigDecimal price;

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto01() {
        return photo01;
    }

    public void setPhoto01(String photo01) {
        this.photo01 = photo01;
    }

    public String getPhoto02() {
        return photo02;
    }

    public void setPhoto02(String photo02) {
        this.photo02 = photo02;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}