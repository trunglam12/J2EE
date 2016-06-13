/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.ArrayList;

/**
 *
 * @author belsh
 */
public class HighChartReport {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long> getData() {
        return data;
    }

    public void setData(ArrayList<Long> data) {
        this.data = data;
    }
    ArrayList<Long> data;
    
    public HighChartReport(){
        data = new ArrayList<Long>();
    }
    
    public HighChartReport(String newName){
        setName(newName);
        data = new ArrayList<Long>();
    }
}
