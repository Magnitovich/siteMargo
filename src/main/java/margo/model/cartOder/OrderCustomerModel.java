package margo.model.cartOder;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "ORDER_FROM_CUSTOMER", schema = "public")
public class OrderCustomerModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PHOTO")
    private String photo;
    @Column
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STRUCTURE") //organza, curtain ....
    private String structure;
    @Column(name = "PAINT") //abstraction, geometric  ....
    private String paint;
    @Column(name = "HEIGHT")
    private String height;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerModel customerOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getPaint() {
        return paint;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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

    public CustomerModel getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerModel customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public String toString() {
        return "OrderCustomerModel{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", structure='" + structure + '\'' +
                ", paint='" + paint + '\'' +
                ", height='" + height + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", customerOrder=" + customerOrder +
                '}';
    }
}
