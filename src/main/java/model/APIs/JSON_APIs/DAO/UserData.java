/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.APIs.JSON_APIs.DAO;

/**
 *
 * @author User
 */
public class UserData {
    private final boolean is_logged_in;
    private final byte[] user_id;
    private final String user_type;
    private final String current_scene;

    public UserData(boolean is_logged_in, byte[] user_id, String user_type, String current_scene) {
        this.is_logged_in = is_logged_in;
        this.user_id = user_id;
        this.user_type = user_type;
        this.current_scene = current_scene;
    }

    public boolean get_logged_in() {
        return is_logged_in;
    }

    public byte[] get_user_id() {
        return user_id;
    }

    public String get_user_type() {
        return user_type;
    }
    
    public String get_current_scene() {
        return current_scene;
    }
    
}
