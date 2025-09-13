package com.example.model;

import java.util.*;

public class World{

    private static Random random;
    private Player[] players;
    private Country[] countries;
    private int numOfTrades;

    public World(int numPlayers) {
        numOfTrades = 6;
        initializePlayers(numPlayers);
        initializeCountries();
        initializeCountryOwners(countries, players, numPlayers);
        random = new Random();
    }

    //create the players and initialize the unsedTroops
    public void initializePlayers(int numPlayers) {
        int unsedTroops;
        players = new Player[numPlayers];
        switch (numPlayers) {
            case 2:
                unsedTroops = 4;
                break;
            case 3:
                unsedTroops = 35;
                break;
            case 4:
                unsedTroops = 30;
                break;
            case 5:
                unsedTroops = 25;
                break;
            case 6:
                unsedTroops = 20;
                break;
            default : throw new IllegalStateException("Unexpected value: " + numPlayers);
        }
        String playerColor;
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player();
            players[i].setId(i);
            players[i].setUnsedTroops(unsedTroops);
            switch (i){
                case 0:
                    playerColor = "#1057c9";
                    break;
                case 1:
                    playerColor = "#c91010";
                    break;
                case 2:
                    playerColor = "#c9c910";
                    break;
                case 3:
                    playerColor = "#10c913";
                    break;
                case 4:
                    playerColor = "#c97310";
                    break;
                case 5:
                    playerColor = "#5d10c9";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            players[i].setColor(playerColor);
        }
        //Shuffle the players list so every player plays at random in every game
        Collections.shuffle(Arrays.asList(players));
    }

    //Create a list of countries and all the 42 countries
    public void initializeCountries(){
        countries = new Country[42];
        countries[0] = new Country("Alaska");
        countries[1] = new Country("Alberta");
        countries[2] = new Country("CentralAmerica");
        countries[3] = new Country("EastUS");
        countries[4] = new Country("Greenland");
        countries[5] = new Country("NorthTerritory");
        countries[6] = new Country("Ontario");
        countries[7] = new Country("Quebec");
        countries[8] = new Country("WestUS");
        countries[9] = new Country("Venezuela");
        countries[10] = new Country("Brazil");
        countries[11] = new Country("Peru");
        countries[12] = new Country("Argentina");
        countries[13] = new Country("Britain");
        countries[14] = new Country("Iceland");
        countries[15] = new Country("NorthEurope");
        countries[16] = new Country("Scandinavia");
        countries[17] = new Country("Ukraine");
        countries[18] = new Country("SouthEurope");
        countries[19] = new Country("WestEurope");
        countries[20] = new Country("Madagascar");
        countries[21] = new Country("Egypt");
        countries[22] = new Country("NorthAfrica");
        countries[23] = new Country("EastAfrica");
        countries[24] = new Country("Congo");
        countries[25] = new Country("SouthAfrica");
        countries[26] = new Country("MiddleEast");
        countries[27] = new Country("Kazakhstan");
        countries[28] = new Country("Ural");
        countries[29] = new Country("India");
        countries[30] = new Country("China");
        countries[31] = new Country("Siberia");
        countries[32] = new Country("Siam");
        countries[33] = new Country("Mongolia");
        countries[34] = new Country("Irkutsk");
        countries[35] = new Country("Yakutsk");
        countries[36] = new Country("Kamchatka");
        countries[37] = new Country("Japan");
        countries[38] = new Country("Indonesia");
        countries[39] = new Country("WestAustralia");
        countries[40] = new Country("EastAustralia");
        countries[41] = new Country("NewGuinea");

        //Alaska
        countries[0].adjacentCountries = new LinkedList<>();
        countries[0].adjacentCountries.add(countries[1]);
        countries[0].adjacentCountries.add(countries[5]);
        countries[0].adjacentCountries.add(countries[36]);
        //Alberta
        countries[1].adjacentCountries = new LinkedList<>();
        countries[1].adjacentCountries.add(countries[0]);
        countries[1].adjacentCountries.add(countries[5]);
        countries[1].adjacentCountries.add(countries[6]);
        countries[1].adjacentCountries.add(countries[8]);
        //CentralAmerica
        countries[2].adjacentCountries = new LinkedList<>();
        countries[2].adjacentCountries.add(countries[3]);
        countries[2].adjacentCountries.add(countries[8]);
        countries[2].adjacentCountries.add(countries[9]);
        //EastUS
        countries[3].adjacentCountries = new LinkedList<>();
        countries[3].adjacentCountries.add(countries[2]);
        countries[3].adjacentCountries.add(countries[6]);
        countries[3].adjacentCountries.add(countries[7]);
        countries[3].adjacentCountries.add(countries[8]);
        //Greenland
        countries[4].adjacentCountries = new LinkedList<>();
        countries[4].adjacentCountries.add(countries[5]);
        countries[4].adjacentCountries.add(countries[6]);
        countries[4].adjacentCountries.add(countries[7]);
        countries[4].adjacentCountries.add(countries[14]);
        //NorthTerritory
        countries[5].adjacentCountries = new LinkedList<>();
        countries[5].adjacentCountries.add(countries[0]);
        countries[5].adjacentCountries.add(countries[1]);
        countries[5].adjacentCountries.add(countries[4]);
        countries[5].adjacentCountries.add(countries[6]);
        //Ontario
        countries[6].adjacentCountries = new LinkedList<>();
        countries[6].adjacentCountries.add(countries[1]);
        countries[6].adjacentCountries.add(countries[3]);
        countries[6].adjacentCountries.add(countries[4]);
        countries[6].adjacentCountries.add(countries[5]);
        countries[6].adjacentCountries.add(countries[7]);
        countries[6].adjacentCountries.add(countries[8]);
        //Quebec
        countries[7].adjacentCountries = new LinkedList<>();
        countries[7].adjacentCountries.add(countries[3]);
        countries[7].adjacentCountries.add(countries[4]);
        countries[7].adjacentCountries.add(countries[6]);
        //WestUS
        countries[8].adjacentCountries = new LinkedList<>();
        countries[8].adjacentCountries.add(countries[1]);
        countries[8].adjacentCountries.add(countries[2]);
        countries[8].adjacentCountries.add(countries[3]);
        countries[8].adjacentCountries.add(countries[6]);
        //Venezuela
        countries[9].adjacentCountries = new LinkedList<>();
        countries[9].adjacentCountries.add(countries[2]);
        countries[9].adjacentCountries.add(countries[10]);
        countries[9].adjacentCountries.add(countries[11]);
        //Brazil
        countries[10].adjacentCountries = new LinkedList<>();
        countries[10].adjacentCountries.add(countries[9]);
        countries[10].adjacentCountries.add(countries[11]);
        countries[10].adjacentCountries.add(countries[12]);
        countries[10].adjacentCountries.add(countries[22]);
        //Peru
        countries[11].adjacentCountries = new LinkedList<>();
        countries[11].adjacentCountries.add(countries[9]);
        countries[11].adjacentCountries.add(countries[10]);
        countries[11].adjacentCountries.add(countries[12]);
        //Argentina
        countries[12].adjacentCountries = new LinkedList<>();
        countries[12].adjacentCountries.add(countries[10]);
        countries[12].adjacentCountries.add(countries[11]);
        //Britain
        countries[13].adjacentCountries = new LinkedList<>();
        countries[13].adjacentCountries.add(countries[14]);
        countries[13].adjacentCountries.add(countries[15]);
        countries[13].adjacentCountries.add(countries[16]);
        countries[13].adjacentCountries.add(countries[19]);
        //Iceland
        countries[14].adjacentCountries = new LinkedList<>();
        countries[14].adjacentCountries.add(countries[4]);
        countries[14].adjacentCountries.add(countries[13]);
        countries[14].adjacentCountries.add(countries[16]);
        //NorthEurope
        countries[15].adjacentCountries = new LinkedList<>();
        countries[15].adjacentCountries.add(countries[13]);
        countries[15].adjacentCountries.add(countries[16]);
        countries[15].adjacentCountries.add(countries[17]);
        countries[15].adjacentCountries.add(countries[18]);
        countries[15].adjacentCountries.add(countries[19]);
        //Scandinavia
        countries[16].adjacentCountries = new LinkedList<>();
        countries[16].adjacentCountries.add(countries[13]);
        countries[16].adjacentCountries.add(countries[14]);
        countries[16].adjacentCountries.add(countries[15]);
        countries[16].adjacentCountries.add(countries[17]);
        //Ukraine
        countries[17].adjacentCountries = new LinkedList<>();
        countries[17].adjacentCountries.add(countries[15]);
        countries[17].adjacentCountries.add(countries[16]);
        countries[17].adjacentCountries.add(countries[18]);
        countries[17].adjacentCountries.add(countries[26]);
        countries[17].adjacentCountries.add(countries[27]);
        countries[17].adjacentCountries.add(countries[28]);
        //SouthEurope
        countries[18].adjacentCountries = new LinkedList<>();
        countries[18].adjacentCountries.add(countries[15]);
        countries[18].adjacentCountries.add(countries[19]);
        countries[18].adjacentCountries.add(countries[21]);
        countries[18].adjacentCountries.add(countries[22]);
        countries[18].adjacentCountries.add(countries[26]);
        //WestEurope
        countries[19].adjacentCountries = new LinkedList<>();
        countries[19].adjacentCountries.add(countries[13]);
        countries[19].adjacentCountries.add(countries[15]);
        countries[19].adjacentCountries.add(countries[18]);
        countries[19].adjacentCountries.add(countries[22]);
        //Madagascar
        countries[20].adjacentCountries = new LinkedList<>();
        countries[20].adjacentCountries.add(countries[23]);
        countries[20].adjacentCountries.add(countries[25]);
        //Egypt
        countries[21].adjacentCountries = new LinkedList<>();
        countries[21].adjacentCountries.add(countries[18]);
        countries[21].adjacentCountries.add(countries[22]);
        countries[21].adjacentCountries.add(countries[23]);
        countries[21].adjacentCountries.add(countries[26]);
        //NorthAfrica
        countries[22].adjacentCountries = new LinkedList<>();
        countries[22].adjacentCountries.add(countries[10]);
        countries[22].adjacentCountries.add(countries[19]);
        countries[22].adjacentCountries.add(countries[21]);
        countries[22].adjacentCountries.add(countries[23]);
        countries[22].adjacentCountries.add(countries[24]);
        //EastAfrica
        countries[23].adjacentCountries = new LinkedList<>();
        countries[23].adjacentCountries.add(countries[21]);
        countries[23].adjacentCountries.add(countries[22]);
        countries[23].adjacentCountries.add(countries[20]);
        countries[23].adjacentCountries.add(countries[24]);
        countries[23].adjacentCountries.add(countries[25]);
        countries[23].adjacentCountries.add(countries[26]);
        //Congo
        countries[24].adjacentCountries = new LinkedList<>();
        countries[24].adjacentCountries.add(countries[22]);
        countries[24].adjacentCountries.add(countries[23]);
        countries[24].adjacentCountries.add(countries[25]);
        //SouthAfrica
        countries[25].adjacentCountries = new LinkedList<>();
        countries[25].adjacentCountries.add(countries[20]);
        countries[25].adjacentCountries.add(countries[23]);
        countries[25].adjacentCountries.add(countries[24]);
        //MiddleEast
        countries[26].adjacentCountries = new LinkedList<>();
        countries[26].adjacentCountries.add(countries[17]);
        countries[26].adjacentCountries.add(countries[18]);
        countries[26].adjacentCountries.add(countries[21]);
        countries[26].adjacentCountries.add(countries[23]);
        countries[26].adjacentCountries.add(countries[27]);
        countries[26].adjacentCountries.add(countries[29]);
        //Kazakhstan
        countries[27].adjacentCountries = new LinkedList<>();
        countries[27].adjacentCountries.add(countries[17]);
        countries[27].adjacentCountries.add(countries[26]);
        countries[27].adjacentCountries.add(countries[28]);
        countries[27].adjacentCountries.add(countries[29]);
        countries[27].adjacentCountries.add(countries[30]);
        //Ural
        countries[28].adjacentCountries = new LinkedList<>();
        countries[28].adjacentCountries.add(countries[17]);
        countries[28].adjacentCountries.add(countries[27]);
        countries[28].adjacentCountries.add(countries[30]);
        countries[28].adjacentCountries.add(countries[31]);
        //India
        countries[29].adjacentCountries = new LinkedList<>();
        countries[29].adjacentCountries.add(countries[26]);
        countries[29].adjacentCountries.add(countries[27]);
        countries[29].adjacentCountries.add(countries[30]);
        countries[29].adjacentCountries.add(countries[32]);
        //China
        countries[30].adjacentCountries = new LinkedList<>();
        countries[30].adjacentCountries.add(countries[28]);
        countries[30].adjacentCountries.add(countries[27]);
        countries[30].adjacentCountries.add(countries[29]);
        countries[30].adjacentCountries.add(countries[31]);
        countries[30].adjacentCountries.add(countries[32]);
        countries[30].adjacentCountries.add(countries[33]);
        //Siberia
        countries[31].adjacentCountries = new LinkedList<>();
        countries[31].adjacentCountries.add(countries[28]);
        countries[31].adjacentCountries.add(countries[30]);
        countries[31].adjacentCountries.add(countries[33]);
        countries[31].adjacentCountries.add(countries[34]);
        countries[31].adjacentCountries.add(countries[35]);
        //Siam
        countries[32].adjacentCountries = new LinkedList<>();
        countries[32].adjacentCountries.add(countries[29]);
        countries[32].adjacentCountries.add(countries[30]);
        countries[32].adjacentCountries.add(countries[38]);
        //Mongolia
        countries[33].adjacentCountries = new LinkedList<>();
        countries[33].adjacentCountries.add(countries[30]);
        countries[33].adjacentCountries.add(countries[31]);
        countries[33].adjacentCountries.add(countries[34]);
        countries[33].adjacentCountries.add(countries[36]);
        countries[33].adjacentCountries.add(countries[37]);
        //Irkutsk
        countries[34].adjacentCountries = new LinkedList<>();
        countries[34].adjacentCountries.add(countries[31]);
        countries[34].adjacentCountries.add(countries[33]);
        countries[34].adjacentCountries.add(countries[35]);
        countries[34].adjacentCountries.add(countries[36]);
        //Yakutsk
        countries[35].adjacentCountries = new LinkedList<>();
        countries[35].adjacentCountries.add(countries[31]);
        countries[35].adjacentCountries.add(countries[34]);
        countries[35].adjacentCountries.add(countries[36]);
        //Kamchatka
        countries[36].adjacentCountries = new LinkedList<>();
        countries[36].adjacentCountries.add(countries[0]);
        countries[36].adjacentCountries.add(countries[34]);
        countries[36].adjacentCountries.add(countries[35]);
        countries[36].adjacentCountries.add(countries[37]);
        //Japan
        countries[37].adjacentCountries = new LinkedList<>();
        countries[37].adjacentCountries.add(countries[33]);
        countries[37].adjacentCountries.add(countries[36]);
        //Indonesia
        countries[38].adjacentCountries = new LinkedList<>();
        countries[38].adjacentCountries.add(countries[32]);
        countries[38].adjacentCountries.add(countries[39]);
        countries[38].adjacentCountries.add(countries[41]);
        //WestAustralia
        countries[39].adjacentCountries = new LinkedList<>();
        countries[39].adjacentCountries.add(countries[38]);
        countries[39].adjacentCountries.add(countries[40]);
        countries[39].adjacentCountries.add(countries[41]);
        //EastAustralia
        countries[40].adjacentCountries = new LinkedList<>();
        countries[40].adjacentCountries.add(countries[39]);
        countries[40].adjacentCountries.add(countries[41]);
        //NewGuinea
        countries[41].adjacentCountries = new LinkedList<>();
        countries[41].adjacentCountries.add(countries[38]);
        countries[41].adjacentCountries.add(countries[39]);
        countries[41].adjacentCountries.add(countries[40]);
    }

    /*Depth search algorithm for searching paths for adjacent countries
    Input: starting country and goal country*/
    public boolean findPath(Country country0, Country country1){
        LinkedList<Country> visited = new LinkedList<Country>();
        return (findPath(country0,country1,visited));
    }

    public boolean findPath(Country country0, Country country1,LinkedList<Country> visited){
        if (country0==country1 && visited.getFirst().getOwner()== country0.getOwner())return true;
        if (visited.contains(country0)) return false;
        visited.add(country0);
        for (Country c: country0.adjacentCountries){
            if (c.getOwner()==visited.getFirst().getOwner()) {
                boolean possible = findPath(c, country1, visited);
                if (possible) {
                    return true;
                }
            }
        }
        return false;
    }

    //initialize country owners
    public  void initializeCountryOwners(Country[] countries, Player[] players, int numPlayers){
        //Each player get a number of countries at random each time
        Collections.shuffle(Arrays.asList(countries));
        int playerID = 0;
        for (Country country : countries) {
            players[playerID].getCountriesOwned().add(country);
            country.setOwner(players[playerID]);
            playerID = (playerID + 1) % numPlayers;
        }
    }

    //calculate the number of troops the player receives at the begging of the turn
    public  void updateUnsedTroops(Player player) {
        int numTroops = (player.getCountriesOwned().size() / 3); // + continent_bonus?
        player.setUnsedTroops(Math.max(3, numTroops));
    }

    //Match the string input with a country
    public Country findCountry(String country){
        for (Country c : countries) {
            if (country.equals(c.toString())) {
                return c;
            }
        }
        return null;
    }

    //fortify one country only
    public Country fortify(String buttonText,Player player){
        Country country = findCountry(buttonText);
        if (player.getCountriesOwned().contains(country) && player.getUnsedTroops() != 0) {
            country.setNumTroops(1);
            country.getOwner().removeTroops(1);
        }
        return  country;
    }

    //move a number of troops from a country to country2
    public void moveArmy(Country country,Country country2,int numOfTroops,Player player){
        if(moveArmyCheck(country,country2,player)){
            country2.setNumTroops(numOfTroops);
            country.removeNumTroops(numOfTroops);
        }
    }

    //Constrains for moveArmy
    public boolean moveArmyCheck(Country country,Country country2,Player player){
        return  player.getCountriesOwned().contains(country)  &&
                player.getCountriesOwned().contains(country2) &&
                country != country2  &&  country.getNumTroops()  > 1;
    }

    //Implementation of attack : Attacker hits for 3 attacks,defender defends for 2,compare the 2 biggest numbers
    public boolean attack(Country own,Country enemy){
        Integer[] attacker = new Integer[3];
        Integer[] defender = new Integer[2];

        for(int i = 0; i < 3; i++){
            attacker[i] = roll();
        }
        for(int i = 0; i < 2; i++){
            defender[i] = roll();
        }
        Arrays.sort(attacker, Collections.reverseOrder());
        Arrays.sort(defender,Collections.reverseOrder());
        for(int i = 0; i < 2; i++) {
            if (defender[i] >= attacker[i]) {
                own.removeNumTroops(1);
            } else {
                enemy.removeNumTroops(1);
            }
        }
        return attackResult(own,enemy);
    }

    //Constrains for attack
    public boolean attackCheck(Country attacker,Country defender,Player player){
        return  player.getCountriesOwned().contains(attacker)  &&
                !player.getCountriesOwned().contains(defender) &&
                attacker.getNumTroops() >= 3;
    }

    //Calculate the attack result
    public boolean attackResult(Country own, Country enemy){
        if(enemy.getNumTroops() <= 0) {
            //Give the winner a card if not won already
            if(!own.getOwner().isWonCard()){
                own.getOwner().setWonCard(true);
            }
            //If the defender is dead give his cards to attacker
            if(enemy.getOwner().isDead()){
                own.getOwner().getCards().winnerTakesCards(enemy.getOwner().getCards());
            }
            //Update the players
            enemy.setNum(1);
            own.setNum(own.getNumTroops()-1);
            own.getOwner().getCountriesOwned().add(enemy);
            enemy.getOwner().getCountriesOwned().remove(enemy);
            enemy.setOwner(own.getOwner());
            enemy.getOwner().statusCheck();
            return true;
        }
        return false;
    }

    //Remove the selected amount of cards from players hand to redeem them
    public int redeemCards(int t, int c, int a, Player player) {
        //temp cardList with the cards to remove
        int[] rCards = new int[3];
        rCards[0] = t;
        rCards[1] = c;
        rCards[2] = a;
        int total = 0;
        //trade cards one of each type,check if the player has that amount of cards
        if (    (rCards[0] == rCards[1]) && (rCards[0] == rCards[2]) &&
                (rCards[0] > 0) && (player.getCards().getCards()[0] >= rCards[0]) &&
                (player.getCards().getCards()[1] >= rCards[0]) && (player.getCards().getCards()[2] >= rCards[0])) {
            player.getCards().getCards()[0] -= rCards[0];
            player.getCards().getCards()[1] -= rCards[1];
            player.getCards().getCards()[2] -= rCards[2];
            for (int i = 0; i < rCards[0]; i++) {
                total += numOfTrades;
                numOfTrades += 2;
            }
            return total;

        }
        //trade 3 cards of the same type,check if the player has that amount of cards
        for (int i = 0; i < 3; i++) {
            if ( (rCards[i] != 0) && (rCards[i] % 3) == 0 && (player.getCards().getCards()[i] >= 3)  ){
                player.getCards().getCards()[i] -= rCards[i];
                total += (numOfTrades * (rCards[i] / 3) );
                numOfTrades += 2 * (rCards[i] / 3);
            }
            if( i == 2 ){ return total; }
        }
        //Invalid card combination return 0 total number of trades
        if((rCards[0] == rCards[1] ) && (rCards[0] == rCards[2]) && (rCards[0] ==0) ){
            return 0;
        }
        return  total;
    }

    //check win condition,if the players - 1 are dead return true
    public boolean checkWin(Player [] players) {
        int countDead = 0;
        for (Player p : players) {
            if (p.isDead()) {
                countDead++;
            }
        }
        return players.length - 1 == countDead;
    }

    //Simulates the die roll.Returns a number 1-6.
    public static int roll(){
        return  (random.nextInt(6));
    }



    /*Setters & Getters*/

    public int getNumOfTrades() { return numOfTrades; }

    public Player getPlayer(int i) { return players[i]; }

    public Player[] getPlayers() { return players; }

}
