package model.entities;
// Generated 29/05/2019 10:03:36 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 * Customer generated by hbm2java
 */
@Entity
@Proxy(lazy = false)
@Table(name="customer"
    ,schema="public"
)
public class Customer  implements java.io.Serializable {


     private int customerid;
     private String firstname;
     private String lastname;
     private String company;
     private String address;
     private String city;
     private String state;
     private String country;
     private String postalcode;
     private String phone;
     private String fax;
     private Set<Invoice> invoices = new HashSet<Invoice>(0);

    public Customer() {
    }

	
    public Customer(int customerid) {
        this.customerid = customerid;
    }
    public Customer(int customerid, String firstname, String lastname, String company, String address, String city, String state, String country, String postalcode, String phone, String fax, Set<Invoice> invoices) {
       this.customerid = customerid;
       this.firstname = firstname;
       this.lastname = lastname;
       this.company = company;
       this.address = address;
       this.city = city;
       this.state = state;
       this.country = country;
       this.postalcode = postalcode;
       this.phone = phone;
       this.fax = fax;
       this.invoices = invoices;
    }
   
     @Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="customerid", unique=true, nullable=false)
    public int getCustomerid() {
        return this.customerid;
    }
    
    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    
    @Column(name="firstname")
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    
    @Column(name="lastname")
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
    @Column(name="company")
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }

    
    @Column(name="address")
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    
    @Column(name="city")
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    
    @Column(name="state")
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    
    @Column(name="country")
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    
    @Column(name="postalcode")
    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    
    @Column(name="phone")
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="fax")
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="customer")
    public Set<Invoice> getInvoices() {
        return this.invoices;
    }
    
    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }




}


