package margo.model.allCurtains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TULLE", schema = "public")
public class TulleModel {

    @Column(name = "PHOTO")
    private String photo;
    @Column(name = "PHOTO01")
    private String photo01;
    @Column(name = "PHOTO02")
    private String photo02;
    @Column(name = "PHOTO03")
    private String photo03;
    @Column(name = "PHOTO04")
    private String photo04;
    @Column(name = "PHOTO05")
    private String photo05;
    @Column(name = "ID")
    @Id
    private Long id;
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

    public String getPhoto03() {
        return photo03;
    }

    public void setPhoto03(String photo03) {
        this.photo03 = photo03;
    }

    public String getPhoto04() {
        return photo04;
    }

    public void setPhoto04(String photo04) {
        this.photo04 = photo04;
    }

    public String getPhoto05() {
        return photo05;
    }

    public void setPhoto05(String photo05) {
        this.photo05 = photo05;
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
}
