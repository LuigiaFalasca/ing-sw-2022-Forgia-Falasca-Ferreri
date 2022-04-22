package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseCloudTilesTest {
    @Test
    public void testChooseCloudTiles(){
        SchoolBoard s1=new SchoolBoard(1,1);
        SchoolBoard s2=new SchoolBoard(2,1);
        SchoolBoard s3=new SchoolBoard(3,1);
        SchoolBoard s4=new SchoolBoard(4,1);
        DeckCardAssistant d1=new DeckCardAssistant();
        DeckCardAssistant d2=new DeckCardAssistant();
        DeckCardAssistant d3=new DeckCardAssistant();
        DeckCardAssistant d4=new DeckCardAssistant();
        Player p1=new Player("a",1,s1,d1);
        Player p2=new Player("b",2,s2,d2);
        Player p3=new Player("c",3,s3,d3);
        Player p4=new Player("d",4,s4,d4);
        CloudTiles c1=new CloudTiles(1);
        CloudTiles c2=new CloudTiles(2);
        CloudTiles c3=new CloudTiles(3);
        CloudTiles c4=new CloudTiles(4);
        Bag b=new Bag();
        b.setStudents(120);
        c1.PutStudents(4,b);
        c2.PutStudents(4,b);
        c3.PutStudents(4,b);
        c4.PutStudents(4,b);
        assertEquals(108,b.getStudents().size());
        ChooseCloudTiles ch=new ChooseCloudTiles();
        ch.ChoosenCloud(p1,c1);
        ch.ChoosenCloud(p2,c2);
        ch.ChoosenCloud(p3,c3);
        ch.ChoosenCloud(p4,c4);
        assertEquals(0,c1.getStud().size());
        assertEquals(0,c2.getStud().size());
        assertEquals(0,c3.getStud().size());
        assertEquals(0,c4.getStud().size());
        assertEquals(3,s1.getEntrance().size());
        assertEquals(3,s2.getEntrance().size());
        assertEquals(3,s3.getEntrance().size());
        assertEquals(3,s4.getEntrance().size());

    }

}