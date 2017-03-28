package margo.model.cartOder.cartDTO;


import javax.persistence.Column;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDTO {

    private Long customer_id;
    private String nameCustomer;
    private String emailCustomer;
    private String phoneCustomer;
    private String addressCustomer;
    private String oderDate;
    private Boolean reciveOrder;
    private Boolean sentOrder;

    public Boolean getReciveOrder() {
        return reciveOrder;
    }

    public void setReciveOrder(Boolean reciveOrder) {
        this.reciveOrder = reciveOrder;
    }

    public Boolean getSentOrder() {
        return sentOrder;
    }

    public void setSentOrder(Boolean sentOrder) {
        this.sentOrder = sentOrder;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getOderDate() {
        return oderDate;
    }

    public void setOderDate(String oderDate) {
        this.oderDate = oderDate;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customer_id=" + customer_id +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", emailCustomer='" + emailCustomer + '\'' +
                ", phoneCustomer='" + phoneCustomer + '\'' +
                ", addressCustomer='" + addressCustomer + '\'' +
                ", oderDate='" + oderDate + '\'' +
                '}';
    }
}
