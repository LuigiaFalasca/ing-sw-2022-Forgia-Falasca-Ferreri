package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Luigia Falasca
 * **/
public class MoveMotherNature implements Serializable {
    private IslandTiles I1;
    private ColorTower colorT;
    private List<ColorTower> colorTowerList = new ArrayList<>();


/** Set randomly the Island where to put MotherNature at the beginning of the game
 * **/
    public void SetIslandWithMotherNature(GeneralBoard gb){
        Random random = new Random();
        int draftedindex = random.nextInt(12);
        I1=gb.GetIslands().get(draftedindex);
        for(int i=0; i< gb.GetIslands().size(); i++){
            if(I1.equals(gb.GetIslands().get(i))){
                gb.GetIslands().get(i).putMotherNature();
            }
        }
    }

    /** Saves where is MotherNature
     * **/
    public void SetIslandWithMotherNature(IslandTiles I){
        I1 = I;
    }

    /** Move Mother and saves it in I1
     * **/
    public void MoveMN(GeneralBoard GB, int n){
        I1 = GB.moveMotherNature(I1, n);
    }

    /** Get the island with MotherNature
     * **/
    public IslandTiles getI1() {
        return I1;
    }

    /** Calculate the influence for the Island I
     * **/
    private int influenceTot(List<Color> student, IslandTiles I, ColorTower ct) {
        int influence = 0;
        int influenceT = 0;
        if (!I.isNoEntryTiles()) {                     /*Effect5*/
            if (I.isTower()) {
                if (ct.equals(I.getColTower())) {
                    influenceT = I.getSize();
                }
            }
            for (int i = 0; i < student.size(); i++) {
                influence = influence + I.CountInfluence(student.get(i));
            }
            influence = influence + influenceT;
        }

        return influence;
    }

     /**
      * Overload, is used for Effect8 and Effect6
      * **/
    private int influenceTot(List<Color> student, IslandTiles I, ColorTower ct, int nameCard, SchoolBoard SbPlayer) {
        int influence = 0;
        int influenceT = 0;
        if (!I.isNoEntryTiles()) {                     /*Effect5*/
            if (I.isTower()) {
                if (ct.equals(I.getColTower())) {
                    influenceT = I.getSize();
                }
            }
            for (int i = 0; i < student.size(); i++) {
                influence = influence + I.CountInfluence(student.get(i));
            }
            influence = influence + influenceT;

            if (nameCard == 6) {     /*Effect6*/
                if(ct.equals(I.getColTower())) {
                   influence = influence - I.getSize();

                }

            } else if (nameCard == 8) {/*Effect8*/
                if(SbPlayer.ColorTower().equals(ct)) {
                   influence = influence +2;

                }
            }
        }
        return influence;
    }

    /**
     * Overload, is used for Effect9
     * **/
    private int influenceTot(List<Color> professor, IslandTiles I, ColorTower ct, int nameCard , Color c) {
        int influence = 0;
        int influenceT = 0;
        if (!I.isNoEntryTiles()) {                     /*Effect5*/
            if (I.isTower()) {
                if (ct.equals(I.getColTower())) {
                    influenceT = I.getSize();
                }
            }
            for (int i = 0; i < professor.size(); i++) {
                if (!c.equals(professor.get(i))) {    /*Effect9*/
                    influence = influence + I.CountInfluence(professor.get(i));
                }
            }

            influence = influence + influenceT;


        }
        return influence;
    }

    /** Get 2 lists of professors (for white and black squad) when there are 4 players
     * **/
    private ArrayList<ArrayList<Color>> GetSquadIf4Players(GeneralBoard GB){
        ColorTower ct;
        boolean flag = true;
        List<SchoolBoard> lSB =new ArrayList<>();
        ArrayList<ArrayList<Color>> listProfessor = new ArrayList<>(2);

        for(int i =0; i < GB.getSchoolBoard().size(); i++) {
            lSB.add(i, GB.getSchoolBoard().get(i));
        }

        for(int i=0; i< 2; i ++){
            listProfessor.add(new ArrayList<>());
        }

        for(int i=0; i < 2; i ++){
            ct= lSB.get(i).ColorTower();
            listProfessor.get(i).addAll(lSB.get(i).getProfessorTable());
            colorTowerList.add(i,lSB.get(i).ColorTower());
            flag = true;
            for( int j= i + 1; j <lSB.size() && flag; j++){
                if(ct.equals(lSB.get(j).ColorTower())){
                    listProfessor.get(i).addAll(lSB.get(j).getProfessorTable());
                    lSB.remove(j);
                    flag= false;
                }
            }
        }
        return listProfessor;
    }

    /** Get 2 or 3 lists of professors when there are 2 or 3 players
     * **/
    private ArrayList<ArrayList<Color>> GetArrayForInfluence(GeneralBoard GB, int n){
        List<SchoolBoard> lSB =GB.getSchoolBoard();
        ArrayList<ArrayList<Color>> listProfessor = new ArrayList<>(n);

        for(int i= 0; i < n; i ++){
            listProfessor.add(new ArrayList<>());
        }

        for(int i=0; i < lSB.size(); i ++){
            listProfessor.get(i).addAll(lSB.get(i).getProfessorTable());
            colorTowerList.add(i, lSB.get(i).ColorTower());
        }
        return listProfessor;
    }

    /** Check if the Island is controlled, return true if it is and saves
     * the color of the squad who controls the island
     * **/
    public boolean CheckIfIslandGetControlled(int numberPlayer, GeneralBoard GB, IslandTiles I){
        ArrayList<Integer> listInfluence = new ArrayList<>();
        ArrayList<ArrayList<Color>> listProfessor;
        boolean flag = false;
        int max=0;
        ColorTower ct = colorT;

        if(numberPlayer==4) {
            listProfessor= GetSquadIf4Players(GB);
        }else{
            listProfessor = GetArrayForInfluence(GB, numberPlayer);
        }

        for(int i=0; i< listProfessor.size(); i++) {
            listInfluence.add(i, influenceTot(listProfessor.get(i), I, colorTowerList.get(i)));
        }

        for(int j=0; j < listInfluence.size(); j++){
            if(listInfluence.get(j)> max){
                max = listInfluence.get(j);
                ct = colorTowerList.get(j);
                flag = true;
            }else if(listInfluence.get(j)== max){
                ct = colorT;
                flag = false;
            }
        }

        if(I.isNoEntryTiles()){
            I.removeNET();
        }
        colorT = ct;
        return flag;
    }

    /**Overload, is used for effect 6 and 8**/
    public boolean CheckIfIslandGetControlled(int numberPlayer, GeneralBoard GB, IslandTiles I, int nameCard, SchoolBoard SbPlayer){
        ArrayList<Integer> listInfluence = new ArrayList<>();
        ArrayList<ArrayList<Color>> listProfessor;
        boolean flag = false;
        int max=0;
        ColorTower ct = colorT;

        if(numberPlayer==4) {
            listProfessor= GetSquadIf4Players(GB);
        }else{
           listProfessor = GetArrayForInfluence(GB, numberPlayer);
        }

        for(int i=0; i< listProfessor.size(); i++) {
            listInfluence.add(i, influenceTot(listProfessor.get(i), I, colorTowerList.get(i), nameCard, SbPlayer));
        }

        for(int j=0; j < listInfluence.size(); j++){
            if(listInfluence.get(j)> max){
                 max = listInfluence.get(j);
                 ct = colorTowerList.get(j);
                 flag = true;
            }else if(listInfluence.get(j)== max){
                  ct = colorT;
                  flag = false;
            }
        }
        colorT = ct;
        return flag;
    }


    /**Overload, is used for Effect 9**/

    public boolean CheckIfIslandGetControlled(int numberPlayer, GeneralBoard GB, IslandTiles I, int nameCard, Color color){
        ArrayList<Integer> listInfluence = new ArrayList<>();
        ArrayList<ArrayList<Color>> listProfessor;
        boolean flag = false;
        int max=0;
        ColorTower ct = colorT;

        if(numberPlayer==4) {
            listProfessor= GetSquadIf4Players(GB);
        }else{
            listProfessor = GetArrayForInfluence(GB, numberPlayer);
        }

        for(int i=0; i< listProfessor.size(); i++) {
                listInfluence.add(i, influenceTot(listProfessor.get(i), I, colorTowerList.get(i), nameCard , color));
        }

        for(int j=0; j < listInfluence.size(); j++){
            if(listInfluence.get(j)> max){
                max = listInfluence.get(j);
                ct = colorTowerList.get(j);
                flag = true;
            }else if(listInfluence.get(j)== max){
                ct = colorT;
                flag = false;
            }
        }
        colorT = ct;
        return flag;
    }

    /** If the island is controlled, put the tower on the island, removing it from SchoolBoard
     * and, if there was before a tower, putting back on SchoolBoard it
     * **/
    public void GetRightTowerOnIsland(GeneralBoard GB, IslandTiles I, List<SchoolBoard> SBWhitTower){
        ColorTower ct;
        boolean notFound = true;
        if(I.isTower()) {
            ct = I.getColTower();
            if (!colorT.equals(ct)) {
                for(int i =0; i < SBWhitTower.size() && notFound; i++){
                   if(SBWhitTower.get(i).ColorTower().equals(ct)){
                       for(int j= 0;  j< I.getSize(); j ++){
                           SBWhitTower.get(i).PutTower();
                       }
                       notFound = false;
                   }
                }
                notFound = true;
                for(int i =0; i < SBWhitTower.size() && notFound; i++){
                    if(SBWhitTower.get(i).ColorTower().equals(colorT)){
                        for(int j= 0;  j< I.getSize(); j ++){
                            SBWhitTower.get(i).RemoveTower();
                        }
                        notFound = false;

                    }
                }
            I.putTower(colorT);
            GB.CheckNearTower(I);
            }
        }else{
            notFound = true;
            for(int i =0; i < SBWhitTower.size() && notFound; i++) {
                if (SBWhitTower.get(i).ColorTower().equals(colorT)) {
                    SBWhitTower.get(i).RemoveTower();
                    notFound = false;
                }
            }
            I.putTower(colorT);
            GB.CheckNearTower(I);
        }

    }
}
