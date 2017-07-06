/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import minhnh.dao.MinhRestaurantDAO;
import minhnh.dto.StaffDTO;

/**
 *
 * @author kubin
 */
public class StaffBean implements Serializable{
    private String firstName;
    private String lastName;
    private String role;
    private boolean available;

    public StaffBean() {
    }
    
    public List<StaffDTO> getStaffAvailable(){
        MinhRestaurantDAO dao = new MinhRestaurantDAO();
        return dao.getStaffAvailable();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
