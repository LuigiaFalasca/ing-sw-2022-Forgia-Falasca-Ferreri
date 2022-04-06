package it.polimi.ingsw.Model;

import java.util.*;

public class IslandTiles implements Location {
    private int NumberID;
    private boolean MotherNature;
    private int size;
    private boolean NoEntryTiles;
    private boolean Tower;
    private ColorTower ColTower;
    ArrayList<Color> StudentsInIsland = new ArrayList<>();

    public IslandTiles(int numberID, boolean motherNature, int size, boolean noEntryTiles, boolean tower) {
        NumberID = numberID;
        MotherNature = motherNature;
        this.size = size;
        NoEntryTiles = noEntryTiles;
        Tower = tower;
    }

    public void putMotherNature() {
        MotherNature = true;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void putNET() throws IllegalMoveException {
        if (!NoEntryTiles) {
            NoEntryTiles = true;
        } else {
            throw new IllegalMoveException();
        }
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

    public void setColTower(ColorTower colTower) {
        ColTower = colTower;
    }

    public void putTower(ColorTower c) {
        if (!Tower) {
            Tower = true;
        } else {
            setColTower(c);
        }
    }


    public ArrayList<Color> getStudentsInIsland() {
        return StudentsInIsland;
    }

    public void setStudentsInIsland(ArrayList<Color> studentsInIsland) {
        StudentsInIsland = studentsInIsland;
    }

    public void removeNM() throws IllegalMoveException{
        if (MotherNature) {
            MotherNature = false;
        }else {
            throw new IllegalMoveException();
        }

    }
    public void removeNET() throws IllegalMoveException{
        if (NoEntryTiles) {
            NoEntryTiles= false;
        }else {
            throw new IllegalMoveException();
        }
    }

    public void PutStudent(Color c){
        StudentsInIsland.add(c);
    }

    public Color GetStudent(int n){
       return StudentsInIsland.get(n);
    }



}