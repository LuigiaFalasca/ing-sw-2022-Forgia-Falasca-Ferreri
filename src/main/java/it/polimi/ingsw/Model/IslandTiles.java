package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.*;

/**
 * @author Luigia Falasca
 */
public class IslandTiles implements Location, Serializable {
    private int NumberID;
    private boolean MotherNature;
    private int size;
    private boolean NoEntryTiles;
    private int NumberOfNet;
    private boolean Tower;
    private ColorTower ColTower;
    ArrayList<Color> StudentsInIsland = new ArrayList<>();

    public IslandTiles(int numberID, boolean motherNature, int size, boolean noEntryTiles, int numberOfNet, boolean tower) {
        NumberID = numberID;
        MotherNature = motherNature;
        this.size = size;
        NoEntryTiles = noEntryTiles;
        Tower = tower;
        NumberOfNet = numberOfNet;
    }

    public void putMotherNature() {
        MotherNature = true;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /** Put the NoEntryTiles when used CharacterCard
     * **/
    public void putNET() {
        if(!NoEntryTiles){
            NoEntryTiles = true;
        }
        NumberOfNet = getNumberOfNet() +1;
    }

    /** Put the NoEntryTiles when 2 island unify
     * **/
    public void putNET( int n) {
        if(!NoEntryTiles){
            NoEntryTiles = true;
        }
        NumberOfNet = getNumberOfNet() +n;
    }

    public int getNumberOfNet() {
        return NumberOfNet;
    }

    public void setNumberOfNet(int numberOfNet) {
        NumberOfNet = numberOfNet;
    }

    public boolean isMotherNature() {
        return MotherNature;
    }

    public boolean isNoEntryTiles() {
        return NoEntryTiles;
    }

    public int getSize() {
        return size;
    }

    public int getNumberID() {
        return NumberID;
    }

    public ColorTower getColTower() {
        return ColTower;
    }

    private void setColTower(ColorTower colTower) {
        ColTower = colTower;
    }

    public boolean isTower() {
        return Tower;
    }

    public void putTower(ColorTower c) {
        if (!Tower) {
            Tower = true;
        }
        setColTower(c);
    }


    public ArrayList<Color> getStudentsInIsland() {
        return StudentsInIsland;
    }


    public void removeNM(){
        MotherNature= false;
        }

        /** Remove the NoEntryTiles
         * **/
    public void removeNET(){
        NumberOfNet = NumberOfNet -1;
        if(NumberOfNet == 0){
            NoEntryTiles = false;
        }
    }

    public void PutStudent(Color c){
        StudentsInIsland.add(c);
    }

    public Color GetStudent(int n){
       return StudentsInIsland.get(n);
    }

    /** Count "Color c" students in the Island
     * **/
    public int CountInfluence(Color c){
        int count=0;
        for(int i=0; i < StudentsInIsland.size(); i++){
            if( StudentsInIsland.get(i).equals(c)){
                count= count+1;
            }
        }
        return count;
    }
}
