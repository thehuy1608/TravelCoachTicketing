package model.database.POJO;
// Generated Apr 9, 2018 1:47:50 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Trip generated by hbm2java
 */
public class Trip  implements java.io.Serializable {


     private Integer tripId;
     private Line line;
     private String tripName;
     private String description;
     private Date modifiedDate;
     private Tripstatus tripstatus;
     private Set<CoachDriverTrip> coachDriverTrips = new HashSet<CoachDriverTrip>(0);
     private Set<Ticket> tickets = new HashSet<Ticket>(0);
     private Schedule schedule;

    public Trip() {
    }

	
    public Trip(Line line, String tripName, Date modifiedDate) {
        this.line = line;
        this.tripName = tripName;
        this.modifiedDate = modifiedDate;
    }
    public Trip(Line line, String tripName, String description, Date modifiedDate, Tripstatus tripstatus, Set<CoachDriverTrip> coachDriverTrips, Set<Ticket> tickets, Schedule schedule) {
       this.line = line;
       this.tripName = tripName;
       this.description = description;
       this.modifiedDate = modifiedDate;
       this.tripstatus = tripstatus;
       this.coachDriverTrips = coachDriverTrips;
       this.tickets = tickets;
       this.schedule = schedule;
    }
   
    public Integer getTripId() {
        return this.tripId;
    }
    
    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }
    public Line getLine() {
        return this.line;
    }
    
    public void setLine(Line line) {
        this.line = line;
    }
    public String getTripName() {
        return this.tripName;
    }
    
    public void setTripName(String tripName) {
        this.tripName = tripName;
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
    public Tripstatus getTripstatus() {
        return this.tripstatus;
    }
    
    public void setTripstatus(Tripstatus tripstatus) {
        this.tripstatus = tripstatus;
    }
    public Set<CoachDriverTrip> getCoachDriverTrips() {
        return this.coachDriverTrips;
    }
    
    public void setCoachDriverTrips(Set<CoachDriverTrip> coachDriverTrips) {
        this.coachDriverTrips = coachDriverTrips;
    }
    public Set<Ticket> getTickets() {
        return this.tickets;
    }
    
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
    public Schedule getSchedule() {
        return this.schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }




}


