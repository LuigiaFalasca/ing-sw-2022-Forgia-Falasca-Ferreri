package it.polimi.ingsw.Model;

import java.io.Serializable;

/** Adds two movements to MotherNature
 * The player doesn't have to choose anything**/
public class CharacterCard4 extends CharacterCard implements Serializable {
    public CharacterCard4(int name, int coins, int countUse, String descriptionEffect) {
        super(name, coins, countUse, descriptionEffect);
    }

    @Override
    public void UseEffect(Player p) {
        p.setNameCharacterCard(4);
        p.setUsedCharacterCard(true);
    }

    @Override
    public void SetCard(Bag b, GeneralBoard gb) {

    }

    public int AddTwoMvntMN(CardAssistant a){     /* player can move nature mother two more position*/

        return a.getMovementMN()+2;
    }
}

