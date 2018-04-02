/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author User
 */
public class SharedData {
    private long user_id;

    public SharedData(long user_id) {
        this.user_id = user_id;
    }

    public long get_user_id() {
        return user_id;
    }

    public void set_user_id(long user_id) {
        this.user_id = user_id;
    }
    
    
}
