package margo.model.modelDTO.serviceMargo;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ServiceMargoDTO {
    private MultipartFile objectPhotoCurtain;
    private String photo;
    private Long id;
    private String name;
    private String description;
    private Double quantity;
    private BigDecimal price;
    private Long idForEditCurtain;

    public MultipartFile getObjectPhotoCurtain() {
        return objectPhotoCurtain;
    }

    public void setObjectPhotoCurtain(MultipartFile objectPhotoCurtain) {
        this.objectPhotoCurtain = objectPhotoCurtain;
    }

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

    public Long getIdForEditCurtain() {
        return idForEditCurtain;
    }

    public void setIdForEditCurtain(Long idForEditCurtain) {
        this.idForEditCurtain = idForEditCurtain;
    }
}
