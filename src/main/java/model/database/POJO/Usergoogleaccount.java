package model.database.POJO;
// Generated Apr 9, 2018 1:47:50 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Usergoogleaccount generated by hbm2java
 */
public class Usergoogleaccount  implements java.io.Serializable {


     private int userId;
     private Users users;
     private byte[] googleId;
     private Date modifiedDate;

    public Usergoogleaccount() {
    }

    public Usergoogleaccount(Users users, byte[] googleId, Date modifiedDate) {
       this.users = users;
       this.googleId = googleId;
       this.modifiedDate = modifiedDate;
    }
   
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public byte[] getGoogleId() {
        return this.googleId;
    }
    
    public void setGoogleId(byte[] googleId) {
        this.googleId = googleId;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }




}

