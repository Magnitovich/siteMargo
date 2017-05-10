package margo.model.serviceMargo;

import margo.model.finishedProduct.AllFinishProductModel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SERVICE_MARGO", schema = "public")
public class ServiceMargoModel extends AllFinishProductModel {

    @Column(name = "PHOTO")
    private String photo;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "PRICE")
    private BigDecimal price;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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