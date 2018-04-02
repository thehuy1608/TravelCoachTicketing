package model.database.POJO;
// Generated Mar 28, 2018 5:38:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role  implements java.io.Serializable {


     private Integer roleId;
     private String roleName;
     private String roleDescription;
     private Date modifiedDate;
     private Set<Users> userses = new HashSet<Users>(0);

    public Role() {
    }

	
    public Role(String roleName, Date modifiedDate) {
        this.roleName = roleName;
        this.modifiedDate = modifiedDate;
    }
    public Role(String roleName, String roleDescription, Date modifiedDate, Set<Users> userses) {
       this.roleName = roleName;
       this.roleDescription = roleDescription;
       this.modifiedDate = modifiedDate;
       this.userses = userses;
    }
   
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDescription() {
        return this.roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public Set<Users> getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set<Users> userses) {
        this.userses = userses;
    }




}


