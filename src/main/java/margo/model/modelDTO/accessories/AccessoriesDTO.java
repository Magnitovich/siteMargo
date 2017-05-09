package margo.model.modelDTO.accessories;


import margo.model.modelDTO.interior.InteriorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class AccessoriesDTO extends InteriorDTO {
    private MultipartFile objectPhotoCurtain;
    private MultipartFile objectPhotoCurtain01;
    private MultipartFile objectPhotoCurtain02;

    private String photo;
    private String photo01;
    private String photo02;
    private Long id;
    private String name;
    private String description;
    private String color;
    private Double quantity;
    private BigDecimal price;
    private Long idForEditCurtain;

    public MultipartFile getObjectPhotoCurtain() {
        return objectPhotoCurtain;
    }

    public void setObjectPhotoCurtain(MultipartFile objectPhotoCurtain) {
        this.objectPhotoCurtain = objectPhotoCurtain;
    }

    public MultipartFile getObjectPhotoCurtain01() {
        return objectPhotoCurtain01;
    }

    public void setObjectPhotoCurtain01(MultipartFile objectPhotoCurtain01) {
        this.objectPhotoCurtain01 = objectPhotoCurtain01;
    }

    public MultipartFile getObjectPhotoCurtain02() {
        return objectPhotoCurtain02;
    }

    public void setObjectPhotoCurtain02(MultipartFile objectPhotoCurtain02) {
        this.objectPhotoCurtain02 = objectPhotoCurtain02;
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

    public Long getIdForEditCurtain() {
        return idForEditCurtain;
    }

    public void setIdForEditCurtain(Long idForEditCurtain) {
        this.idForEditCurtain = idForEditCurtain;
    }
}

