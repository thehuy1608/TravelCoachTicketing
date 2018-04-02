package model.database.POJO;
// Generated Mar 28, 2018 5:38:22 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * InvoiceLineItem generated by hbm2java
 */
public class InvoiceLineItem  implements java.io.Serializable {


     private Integer itemId;
     private Invoice invoice;
     private Ticket ticket;
     private float price;
     private String description;
     private Date modifiedDate;

    public InvoiceLineItem() {
    }

	
    public InvoiceLineItem(Invoice invoice, Ticket ticket, float price, Date modifiedDate) {
        this.invoice = invoice;
        this.ticket = ticket;
        this.price = price;
        this.modifiedDate = modifiedDate;
    }
    public InvoiceLineItem(Invoice invoice, Ticket ticket, float price, String description, Date modifiedDate) {
       this.invoice = invoice;
       this.ticket = ticket;
       this.price = price;
       this.description = description;
       this.modifiedDate = modifiedDate;
    }
   
    public Integer getItemId() {
        return this.itemId;
    }
    
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public Invoice getInvoice() {
        return this.invoice;
    }
    
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public Ticket getTicket() {
        return this.ticket;
    }
    
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }




}


