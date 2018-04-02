package model.database.POJO;
// Generated Mar 28, 2018 5:38:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Station generated by hbm2java
 */
public class Station  implements java.io.Serializable {


     private Integer stationId;
     private CityOrDistrict cityOrDistrict;
     private String stationName;
     private String stationAddress;
     private String stationHotline;
     private Date modifiedDate;
     private Set<Line> linesForDestinationStationId = new HashSet<Line>(0);
     private Set<Line> linesForDepartureStationId = new HashSet<Line>(0);

    public Station() {
    }

	
    public Station(CityOrDistrict cityOrDistrict, String stationName, String stationAddress, String stationHotline, Date modifiedDate) {
        this.cityOrDistrict = cityOrDistrict;
        this.stationName = stationName;
        this.stationAddress = stationAddress;
        this.stationHotline = stationHotline;
        this.modifiedDate = modifiedDate;
    }
    public Station(CityOrDistrict cityOrDistrict, String stationName, String stationAddress, String stationHotline, Date modifiedDate, Set<Line> linesForDestinationStationId, Set<Line> linesForDepartureStationId) {
       this.cityOrDistrict = cityOrDistrict;
       this.stationName = stationName;
       this.stationAddress = stationAddress;
       this.stationHotline = stationHotline;
       this.modifiedDate = modifiedDate;
       this.linesForDestinationStationId = linesForDestinationStationId;
       this.linesForDepartureStationId = linesForDepartureStationId;
    }
   
    public Integer getStationId() {
        return this.stationId;
    }
    
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
    public CityOrDistrict getCityOrDistrict() {
        return this.cityOrDistrict;
    }
    
    public void setCityOrDistrict(CityOrDistrict cityOrDistrict) {
        this.cityOrDistrict = cityOrDistrict;
    }
    public String getStationName() {
        return this.stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    public String getStationAddress() {
        return this.stationAddress;
    }
    
    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }
    public String getStationHotline() {
        return this.stationHotline;
    }
    
    public void setStationHotline(String stationHotline) {
        this.stationHotline = stationHotline;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public Set<Line> getLinesForDestinationStationId() {
        return this.linesForDestinationStationId;
    }
    
    public void setLinesForDestinationStationId(Set<Line> linesForDestinationStationId) {
        this.linesForDestinationStationId = linesForDestinationStationId;
    }
    public Set<Line> getLinesForDepartureStationId() {
        return this.linesForDepartureStationId;
    }
    
    public void setLinesForDepartureStationId(Set<Line> linesForDepartureStationId) {
        this.linesForDepartureStationId = linesForDepartureStationId;
    }




}


