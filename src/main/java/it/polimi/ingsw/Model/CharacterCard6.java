package it.polimi.ingsw.Model;

/*@author Luigia Falasca*/


import java.io.Serializable;

/** Tower doesn't influence
 * The player does not have to choose anything
 * The real effect is implemented in MoveMotherNature
 * **/
public class CharacterCard6 extends CharacterCard implements Serializable {

    public CharacterCard6(int name, int coins, int countUse, String descriptionEffect) {
        super(name, coins, countUse, descriptionEffect);
    }

    @Override
    public void UseEffect(Player p) {
        p.setNameCharacterCard(6);
        p.setUsedCharacterCard(true);
    }

    @Override
    public void SetCard(Bag b, GeneralBoard gb) {
    }

}