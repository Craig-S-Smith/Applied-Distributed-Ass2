/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment1;

/**
 *
 * @author cjvil
 */
public class FiretruckDetails {
    
    int id;
    String truckName;
    int fireId;

    public FiretruckDetails() {
    }

    public FiretruckDetails(int id, String truckName, int fireId) {
        this.id = id;
        this.truckName = truckName;
        this.fireId = fireId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public int getFireId() {
        return fireId;
    }

    public void setFireId(int fireId) {
        this.fireId = fireId;
    }

    @Override
    public String toString() {
        return "Firertruck ID=" + id + "\n" + 
                "Truck Name=" + truckName + "\n" + 
                "Designated Fire ID=" + fireId + "\n";
    }

    

    
    
}
