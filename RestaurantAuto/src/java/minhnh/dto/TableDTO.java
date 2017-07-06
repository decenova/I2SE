/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnh.dto;

import java.io.Serializable;

/**
 *
 * @author Duc Trung
 */
public class TableDTO implements Serializable{
    private String id, tableStatus;

    public TableDTO() {
    }

    public TableDTO(String id, String tableStatus) {
        this.id = id;
        this.tableStatus = tableStatus;
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
