/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import trung.dao.RestaurantDAO;
import trung.dto.TableDTO;

/**
 *
 * @author Duc Trung
 */
public class TrungBean implements Serializable{
    private String id, password, role;

    public String getRole(String id, String password) {
        RestaurantDAO dao = new RestaurantDAO();
        return dao.checkLogin(id, password);
    }
    
    public ArrayList<TableDTO> getTablesStatus() {
        RestaurantDAO dao = new RestaurantDAO();
        return dao.getTablesStatus();
    }
    
    public void changeTableStatus (String tableId, int tableStatusId, String staffId) {
        RestaurantDAO dao = new RestaurantDAO();
        dao.changeTableStatus(tableId, tableStatusId, staffId);
    }
    
    public TrungBean() {
    }

    public TrungBean(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
