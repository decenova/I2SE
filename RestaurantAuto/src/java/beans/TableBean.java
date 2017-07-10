/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import minhnh.dao.MinhRestaurantDAO;
import minhnh.dto.TableDTO;

/**
 *
 * @author kubin
 */
public class TableBean implements Serializable{
    private String id, tableStatus;

    public TableBean() {
    }
    
    public List<TableDTO> getTableList(){
        MinhRestaurantDAO dao = new MinhRestaurantDAO();
        return dao.getTableList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
    
    
}
