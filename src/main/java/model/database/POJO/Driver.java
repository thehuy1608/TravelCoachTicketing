package model.database.POJO;
// Generated Mar 28, 2018 5:38:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Driver generated by hbm2java
 */
public class Driver  implements java.io.Serializable {


     private Integer driverId;
     private String driverName;
     private byte driverAge;
     private String driverAddress;
     private String description;
     private Date modifiedDate;
     private byte isOnTrip;
     private Set<CoachDriverTrip> coachDriverTripsForFkDriverId = new HashSet<CoachDriverTrip>(0);
     private Set<CoachDriverTrip> coachDriverTripsForFkSubDriverId = new HashSet<CoachDriverTrip>(0);

    public Driver() {
    }

	
    public Driver(String driverName, byte driverAge, String driverAddress, Date modifiedDate, byte isOnTrip) {
        this.driverName = driverName;
        this.driverAge = driverAge;
        this.driverAddress = driverAddress;
        this.modifiedDate = modifiedDate;
        this.isOnTrip = isOnTrip;
    }
    public Driver(String driverName, byte driverAge, String driverAddress, String description, Date modifiedDate, byte isOnTrip, Set<CoachDriverTrip> coachDriverTripsForFkDriverId, Set<CoachDriverTrip> coachDriverTripsForFkSubDriverId) {
       this.driverName = driverName;
       this.driverAge = driverAge;
       this.driverAddress = driverAddress;
       this.description = description;
       this.modifiedDate = modifiedDate;
       this.isOnTrip = isOnTrip;
       this.coachDriverTripsForFkDriverId = coachDriverTripsForFkDriverId;
       this.coachDriverTripsForFkSubDriverId = coachDriverTripsForFkSubDriverId;
    }
   
    public Integer getDriverId() {
        return this.driverId;
    }
    
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    public String getDriverName() {
        return this.driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public byte getDriverAge() {
        return this.driverAge;
    }
    
    public void setDriverAge(byte driverAge) {
        this.driverAge = driverAge;
    }
    public String getDriverAddress() {
        return this.driverAddress;
    }
    
    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
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
    public byte getIsOnTrip() {
        return this.isOnTrip;
    }
    
    public void setIsOnTrip(byte isOnTrip) {
        this.isOnTrip = isOnTrip;
    }
    public Set<CoachDriverTrip> getCoachDriverTripsForFkDriverId() {
        return this.coachDriverTripsForFkDriverId;
    }
    
    public void setCoachDriverTripsForFkDriverId(Set<CoachDriverTrip> coachDriverTripsForFkDriverId) {
        this.coachDriverTripsForFkDriverId = coachDriverTripsForFkDriverId;
    }
    public Set<CoachDriverTrip> getCoachDriverTripsForFkSubDriverId() {
        return this.coachDriverTripsForFkSubDriverId;
    }
    
    public void setCoachDriverTripsForFkSubDriverId(Set<CoachDriverTrip> coachDriverTripsForFkSubDriverId) {
        this.coachDriverTripsForFkSubDriverId = coachDriverTripsForFkSubDriverId;
    }




}

