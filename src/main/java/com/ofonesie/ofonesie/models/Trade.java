package com.ofonesie.ofonesie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Trade {

    @Id
    @GeneratedValue
    private int id;

    private int previousOwner;

    private int currentOwner;

    private int listingId;


    public Trade(){}

    public Trade(int listingId, int previousOwner, int currentOwner){

        this.previousOwner = previousOwner;
        this.currentOwner = currentOwner;
        this.listingId = listingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(int previousOwner) {
        this.previousOwner = previousOwner;
    }

    public int getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(int currentOwner) {
        this.currentOwner = currentOwner;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }
}
