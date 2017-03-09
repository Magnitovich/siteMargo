package margo.model.cartOder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER", schema = "public")
public class CustomerModel {

    @Column(name = "CUSTOMER_ID", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long customer_id;
    @Column(name = "NAME_CUSTOMER")
    private String nameCustomer;
    @Column(name = "EMAIL_CUSTOMER")
    private String emailCustomer;
    @Column(name = "PHONE_CUSTOMER")
    private String phoneCustomer;
    @Column(name = "ADDRESS_CUSTOMER")
    private String addressCustomer;
    @Column(name = "DATE_ODER")
    private Date oderDate;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<OrderCustomerModel> customerModels;

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

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    public List<OrderCustomerModel> getCustomerModels() {
        return customerModels;
    }

    public void setCustomerModels(List<OrderCustomerModel> customerModels) {
        this.customerModels = customerModels;
    }
}
