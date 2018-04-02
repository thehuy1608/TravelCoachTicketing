package model.database.POJO;
// Generated Mar 28, 2018 5:38:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ticket generated by hbm2java
 */
public class Ticket  implements java.io.Serializable {


     private Integer ticketId;
     private Trip trip;
     private String ticketName;
     private byte ticketSeatNumber;
     private float ticketPrice;
     private String description;
     private Date modifiedDate;
     private Set<InvoiceLineItem> invoiceLineItems = new HashSet<InvoiceLineItem>(0);

    public Ticket() {
    }

	
    public Ticket(Trip trip, String ticketName, byte ticketSeatNumber, float ticketPrice, Date modifiedDate) {
        this.trip = trip;
        this.ticketName = ticketName;
        this.ticketSeatNumber = ticketSeatNumber;
        this.ticketPrice = ticketPrice;
        this.modifiedDate = modifiedDate;
    }
    public Ticket(Trip trip, String ticketName, byte ticketSeatNumber, float ticketPrice, String description, Date modifiedDate, Set<InvoiceLineItem> invoiceLineItems) {
       this.trip = trip;
       this.ticketName = ticketName;
       this.ticketSeatNumber = ticketSeatNumber;
       this.ticketPrice = ticketPrice;
       this.description = description;
       this.modifiedDate = modifiedDate;
       this.invoiceLineItems = invoiceLineItems;
    }
   
    public Integer getTicketId() {
        return this.ticketId;
    }
    
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public Trip getTrip() {
        return this.trip;
    }
    
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public String getTicketName() {
        return this.ticketName;
    }
    
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
    public byte getTicketSeatNumber() {
        return this.ticketSeatNumber;
    }
    
    public void setTicketSeatNumber(byte ticketSeatNumber) {
        this.ticketSeatNumber = ticketSeatNumber;
    }
    public float getTicketPrice() {
        return this.ticketPrice;
    }
    
    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
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
    public Set<InvoiceLineItem> getInvoiceLineItems() {
        return this.invoiceLineItems;
    }
    
    public void setInvoiceLineItems(Set<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }




}

