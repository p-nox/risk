package com.example.model;

import java.util.*;

public class Country{

    private final String name;
    private int numTroops;
    private Player owner;
    LinkedList<Country> adjacentCountries;


    public Country (String name) {
        this.name = name;
        numTroops = 1;
    }

    //Remove numTroops from the currently country after 1.fortify or 2.attack result
    public void removeNumTroops(int numTroops1){
        numTroops -= numTroops1;
    }

    /*Setters & Getters*/

    public int getNumTroops() {
        return numTroops;
    }

    public void setNumTroops(int numSoldiers) {
        this.numTroops += numSoldiers;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public Player getOwner(){
        return owner;
    }

    public void setNum(int num){ numTroops = num; }

    public List<Country> getAdjacentCountries() {
        return adjacentCountries;
    }

    public boolean checkIfAdjacent(Country otherCountry){
        return (this.getAdjacentCountries().contains(otherCountry));
    }

    public String toString(){
        return  this.name ;
    }

}
