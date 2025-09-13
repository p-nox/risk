package com.example.model;

import java.util.Arrays;
import java.util.Random;

public class Cards {
    private final int[] numberCards;
    private static Random random;

    public Cards(){
        // 0 = troop , 1 = cavalry , 2 = artillery
        numberCards = new int[3];
        Arrays.fill(numberCards,0);
        random = new Random();
    }

    //Add the defeated players cards to the winner(defPlayer = dead)
    public void winnerTakesCards(Cards defCards){
        for(int i = 0; i < this.numberCards.length; i++){
            this.numberCards[i] += defCards.getCards()[i];
        }
    }

    //Check if the player has more cards than limit
    public boolean fullHandCheck(){
        int sum = 0;
        for (int numberCard : numberCards) {
            sum += numberCard;
            if (sum >= 5) return true;
        }
        return false;
    }

    //The player takes only one card if he won until the end of attack phase
    public void winCard(boolean wonCard) {
        if ( wonCard ) {
            numberCards[random.nextInt(3)]++;
        }
    }

    public int[] getCards() {
        return numberCards;
    }

}