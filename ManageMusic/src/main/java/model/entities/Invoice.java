package model.entities;
// Generated 29/05/2019 10:03:36 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Proxy;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Proxy(lazy = false)
@Table(name="invoice"
    ,schema="public"
)
public class Invoice  implements java.io.Serializable {


     private int invoiceid;
     private Customer customer;
     private Date invoicedate;
     private String billingaddress;
     private String billingcity;
     private String billingstate;
     private String billingcountry;
     private String billingpostalcode;
     private BigDecimal total;
     private Set<Invoiceline> invoicelines = new HashSet<Invoiceline>(0);

    public Invoice() {
    }

	
    public Invoice(int invoiceid, Customer customer) {
        this.invoiceid = invoiceid;
        this.customer = customer;
    }
    public Invoice(int invoiceid, Customer customer, Date invoicedate, String billingaddress, String billingcity, String billingstate, String billingcountry, String billingpostalcode, BigDecimal total, Set<Invoiceline> invoicelines) {
       this.invoiceid = invoiceid;
       this.customer = customer;
       this.invoicedate = invoicedate;
       this.billingaddress = billingaddress;
       this.billingcity = billingcity;
       this.billingstate = billingstate;
       this.billingcountry = billingcountry;
       this.billingpostalcode = billingpostalcode;
       this.total = total;
       this.invoicelines = invoicelines;
    }
   
     @Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="invoiceid", unique=true, nullable=false)
    public int getInvoiceid() {
        return this.invoiceid;
    }
    
    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customerid", nullable=false)
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="invoicedate", length=29)
    public Date getInvoicedate() {
        return this.invoicedate;
    }
    
    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    
    @Column(name="billingaddress")
    public String getBillingaddress() {
        return this.billingaddress;
    }
    
    public void setBillingaddress(String billingaddress) {
        this.billingaddress = billingaddress;
    }

    
    @Column(name="billingcity")
    public String getBillingcity() {
        return this.billingcity;
    }
    
    public void setBillingcity(String billingcity) {
        this.billingcity = billingcity;
    }

    
    @Column(name="billingstate")
    public String getBillingstate() {
        return this.billingstate;
    }
    
    public void setBillingstate(String billingstate) {
        this.billingstate = billingstate;
    }

    
    @Column(name="billingcountry")
    public String getBillingcountry() {
        return this.billingcountry;
    }
    
    public void setBillingcountry(String billingcountry) {
        this.billingcountry = billingcountry;
    }

    
    @Column(name="billingpostalcode")
    public String getBillingpostalcode() {
        return this.billingpostalcode;
    }
    
    public void setBillingpostalcode(String billingpostalcode) {
        this.billingpostalcode = billingpostalcode;
    }

    
    @Column(name="total", precision=131089, scale=0)
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="invoice")
    public Set<Invoiceline> getInvoicelines() {
        return this.invoicelines;
    }
    
    public void setInvoicelines(Set<Invoiceline> invoicelines) {
        this.invoicelines = invoicelines;
    }




}

