package model.database.POJO;
// Generated Apr 9, 2018 1:47:50 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Schedule generated by hbm2java
 */
public class Schedule  implements java.io.Serializable {


     private int tripId;
     private Trip trip;
     private Date startTime;
     private Date endTime;
     private String description;
     private Date modifiedDate;

    public Schedule() {
    }

    public Schedule(Trip trip, Date startTime, Date endTime, String description, Date modifiedDate) {
       this.trip = trip;
       this.startTime = startTime;
       this.endTime = endTime;
       this.description = description;
       this.modifiedDate = modifiedDate;
    }
   
    public int getTripId() {
        return this.tripId;
    }
    
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
    public Trip getTrip() {
        return this.trip;
    }
    
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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


