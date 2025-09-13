package com.example.model;

import java.util.ArrayList;
import java.util.List;

/* Player object which represents the information pertaining to
 * a player in the game
 */

public class Player {

    private int id;
    private String color;
    private final Cards cards;
    private int unsedTroops;
    private boolean dead;
    private boolean wonCard;
    private final List<Country> countriesOwned;



    public Player(){
        cards = new Cards();
        unsedTroops = 0;
        dead = false;
        countriesOwned = new ArrayList<>();
    }

    //Check if the player has no owned countries left(dead)
    public void statusCheck(){
      if(countriesOwned.isEmpty()){
            this.dead = true;
        }
        else{
            this.dead = false;
        }
    }

    public void removeTroops(int numTroops){ if(unsedTroops > 0){ unsedTroops -= numTroops; } }

    /*Setters & Getters*/

    public int getUnsedTroops() { return this.unsedTroops; }

    public void setUnsedTroops(int unsedTroops) {
        this.unsedTroops += unsedTroops;
    }

    public void setWonCard(boolean wonCard) {
        this.wonCard = wonCard;
    }

    public Cards getCards() { return this.cards; }

    public boolean isWonCard() {
        return wonCard;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public boolean isDead() { return dead; }

    public void setId(int i){
        this.id = i + 1;
    }

    public int getId() { return id; }

    public List<Country> getCountriesOwned() { return countriesOwned; }

}
