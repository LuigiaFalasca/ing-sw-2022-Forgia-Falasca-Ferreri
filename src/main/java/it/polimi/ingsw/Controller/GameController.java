package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Exception.IllegalArgumentException;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.messages.AnswIfAllowed;

import java.util.List;
import java.util.Random;

public class GameController {
    private AddStudentsOnClouds addStudentsOnClouds = new AddStudentsOnClouds();
    private ChooseCloudTiles chooseCloudTiles = new ChooseCloudTiles();
    private ChoosenPlayer choosenPlayer = new ChoosenPlayer();
    private ChooseSettingGame chooseSettingGame;
    private GameEndState gameEndState = new GameEndState();
    private Move3Students move3Students = new Move3Students();
    private Setup setup = new Setup();
    private MoveMotherNature moveMotherNature = new MoveMotherNature();
    private WinLoseCheckState winLoseCheckState = new WinLoseCheckState();
    private PlayAssCard playAssCard = new PlayAssCard();
    private GameModel gameModel=new GameModel();

    public GameModel getGameModel() {
        return gameModel;
    }

    public AddStudentsOnClouds getAddStudentsOnClouds() {
        return addStudentsOnClouds;
    }

    public void setAddStudentsOnClouds(AddStudentsOnClouds addStudentsOnClouds) {
        this.addStudentsOnClouds = addStudentsOnClouds;
    }
    public ChooseCloudTiles getChooseCloudTiles() {
        return chooseCloudTiles;
    }

    public void setChooseCloudTiles(ChooseCloudTiles chooseCloudTiles) {
        this.chooseCloudTiles = chooseCloudTiles;
    }

    public ChoosenPlayer getChoosenPlayer() {
        return choosenPlayer;
    }

    public void setChoosenPlayer(ChoosenPlayer choosenPlayer) {
        this.choosenPlayer = choosenPlayer;
    }

    public ChooseSettingGame getChooseSettingGame() {
        return chooseSettingGame;
    }

    public void setChooseSettingGame(ChooseSettingGame chooseSettingGame) {
        this.chooseSettingGame = chooseSettingGame;
    }

    public GameEndState getGameEndState() {
        return gameEndState;
    }

    public void setGameEndState(GameEndState gameEndState) {
        this.gameEndState = gameEndState;
    }

    public Move3Students getMove3Students() {
        return move3Students;
    }

    public void setMove3Students(Move3Students move3Students) {
        this.move3Students = move3Students;
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public MoveMotherNature getMoveMotherNature() {
        return moveMotherNature;
    }

    public void setMoveMotherNature(MoveMotherNature moveMotherNature) {
        this.moveMotherNature = moveMotherNature;
    }

    public WinLoseCheckState getWinLoseCheckState() {
        return winLoseCheckState;
    }

    public void setWinLoseCheckState(WinLoseCheckState winLoseCheckState) {
        this.winLoseCheckState = winLoseCheckState;
    }

    public PlayAssCard getPlayAssCard() {
        return playAssCard;
    }

    public void setPlayAssCard(PlayAssCard playAssCard) {
        this.playAssCard = playAssCard;
    }


    public void newGame(int numofPlayers,boolean modexpert){
        gameModel.setGeneralBoard(setup.CreateGeneralboard(modexpert,numofPlayers));
        gameModel.setBag(setup.CreateBag());
        gameModel.setNumplayers(numofPlayers);
        gameModel.setModExpert(modexpert);
        setup.ChooseSchoolBoardWithTowers(gameModel.getGeneralBoard(),numofPlayers);
        setup.SetTowers(numofPlayers,setup.getSBWithTowers());
        setup.SetBag(gameModel.getBag(),1);
    }

    public void CheckNumberOfSteps(int n, Player p, CharacterCard c) throws IllegalNumberOfStepException {
        int move=0;
        if(c != null){
            if(c.getName()==4){
                move = ((CharacterCard4)c).AddTwoMvntMN(p.getCA());
            }else{
                move = p.getCA().getMovementMN();
            }
        }else{
            move = p.getCA().getMovementMN();
        }

        if( n > move|| n<=0 ){
            throw  new IllegalNumberOfStepException(n);
        }else{
            moveMotherNature.MoveMN(gameModel.getGeneralBoard(), n);
        }

    }

    public void CheckColor(Color c, Player p) throws ColorNoInEntranceException {
        boolean flag = true;
        if (c == null) {
            throw new ColorNoInEntranceException(c);
        }
        for (int i = 0; i < p.getMySchoolBoard().getEntrance().size() && flag; i++) {
            if (c.equals(p.getMySchoolBoard().getEntrance().get(i))) {
                flag = false;
            }
        }

        if (flag) {
            throw new ColorNoInEntranceException(c);
        }
    }



    public void NickNameAvailable(String name)throws IllegalNickNameException{
        boolean flag= true;
        if(name== null){
            throw new IllegalNickNameException();
        }else {
                for (int i = 0; i < gameModel.getPlayers().size() && flag; i++) {
                    if (name.equals(gameModel.getPlayers().get(i).getNickName())) {
                        flag = false;
                    }
                }
            }
            if (!flag) {
                throw new IllegalNickNameException(name);
            } else {
                gameModel.getPlayers().add(new Player(name));
            }
        }


    private boolean CloudInList( CloudTiles c){
        boolean flag = false;
        for(int i =0; i < gameModel.getGeneralBoard().getClouds().size(); i++){
            if(c.equals(gameModel.getGeneralBoard().getClouds().get(i))){
                flag=true;
            }
        }

        return flag;
    }

    public void CheckCloud(CloudTiles c, Player player)throws CloudEmptyException {
        if( !CloudInList(c) || c.getStud().size()==0 || c.getStud() == null ){
            throw new CloudEmptyException(c);
        }else{
            chooseCloudTiles.ChoosenCloud(player, c);
        }
    }


    public void CardAssistantInDeck(CardAssistant cardAssistant, Player p) throws CardAssistantNotAvailableException{
        boolean flag = true;

        for(int i =0; i < p.getMyDeck().GetDeck().size() && flag; i ++){
            if(p.getMyDeck().GetDeck().get(i).equals(cardAssistant)){
                flag = false;
            }
        }

        for(int i=0; i < playAssCard.GetAssCardPlayed().size() && !flag; i++){
            if(cardAssistant.equals(playAssCard.GetAssCardPlayed().get(i))){
                if(!playAssCard.CheckIfLast(p)){
                    flag = true;
                }
            }
        }

        if (flag) {
            throw new CardAssistantNotAvailableException(cardAssistant);
        }else{
            playAssCard.GetAssCard(p,cardAssistant);

        }

    }

    public void CharacterCardInTable(CharacterCard characterCard) throws CharacterCardNotInTableException{
        boolean flag = true;
        if(characterCard == null){
            throw new CharacterCardNotInTableException(characterCard);
        }
        for(int i=0; i < gameModel.getGeneralBoard().getChoosenCard().size() && flag; i ++) {
            if(gameModel.getGeneralBoard().getChoosenCard().get(i).equals(characterCard)){
                flag= false;
            }
        }

        if(flag){
            throw new CharacterCardNotInTableException(characterCard);
        }
    }

    public void CheckNumOfPlayer(int n) throws IllegalArgumentException {
        if(n<2 || n> 4){
            throw new IllegalArgumentException();
        }
    }

    public void CheckIfEnoughMoney(Player p, CharacterCard c) throws NotEnoughCoinException{
        if(c.getCost()> p.getNumberCoins()){
            throw new NotEnoughCoinException(p.getNumberCoins(),c);
        }else{
            p.setNumberCoins(p.getNumberCoins()-c.getCost());
            c.getCountUse();
        }
    }

    public void CheckColorOfTower(ColorTower ct, Player p) throws ColorTowerNotCorrectException{
        boolean flag = true;
        boolean flag1= true;
        int count =0;
        if(gameModel.getPlayers().size()<4 &&  gameModel.getPlayers().size()>1) {
            for (int i = 0; i < gameModel.getPlayers().size() && flag; i++) {
                if (gameModel.getPlayers().get(i).getMySchoolBoard().ColorTower() != null && !(gameModel.getPlayers().get(i).equals(p))) {
                    if (gameModel.getPlayers().get(i).getMySchoolBoard().ColorTower().equals(ct)) {
                        flag = false;
                    }
                }
            }
        }else {

            for (int i = 0; i < gameModel.getPlayers().size(); i++) {
                if (gameModel.getPlayers().get(i).getMySchoolBoard().ColorTower() != null && !(gameModel.getPlayers().get(i).equals(p))) {
                    if (gameModel.getPlayers().get(i).getMySchoolBoard().ColorTower().equals(ct)) {
                        count = count +1;
                    }
                }
            }

            if(count>1){
                flag= false;
            }
        }
        if(!flag){
            throw new ColorTowerNotCorrectException();
        }else{
            for(int i =0; i < gameModel.getPlayers().size() && flag1 ; i ++ ){
                if((gameModel.getPlayers().get(i).equals(p))){
                    gameModel.getPlayers().get(i).getMySchoolBoard().setColorTower(ct);
                    flag1 = false;
                }
            }
        }

    }

    public boolean CheckIfAllLogin(){
        if(gameModel.getPlayers().size()== gameModel.getNumplayers()){
            for(int i=0; i<gameModel.getNumplayers(); i ++ ){
                gameModel.getPlayers().get(i).setMyDeck(new DeckCardAssistant());
                gameModel.getPlayers().get(i).setMySchoolBoard(gameModel.getGeneralBoard().getSchoolBoard().get(i));
                if(gameModel.getModExpert()){
                        gameModel.getPlayers().get(i).setNumberCoins(1);
                }else{
                        gameModel.getPlayers().get(i).setNumberCoins(0);
                    }
            }
            return true;
        }else{
            return false;
        }
    }


    public boolean CheckIfAllPlayedCardAssistant(){
        for(int i=0; i<gameModel.getPlayers().size(); i ++ ){
            if(gameModel.getPlayers().get(i).getCA()== null){
                return false;
            }
        }
        for(int i=0; i<gameModel.getPlayers().size(); i ++ ){
            choosenPlayer.ChooseTurnPlayer(gameModel.getPlayers());
        }
        return true;
    }


    public Player ReturnPlayerTurn(){
        return choosenPlayer.GetPlayerTurn();
    }

    public void SetFirstPlayerTurn(){
        Random random = new Random();
        int draftedindex = random.nextInt(this.getGameModel().getPlayers().size());
        Player p = this.getGameModel().getPlayers().get(draftedindex);
        this.getChoosenPlayer().setFirstPlayer(p);
        choosenPlayer.ChooseTurnPlayerForCardAssistant(gameModel.getPlayers());
    }

    public void SetMotherNatureFirstTurn(IslandTiles I){
        moveMotherNature.SetIslandWithMotherNature(I);
        setup.SetupStudentsInIslands(gameModel.getBag(),I.getNumberID(),gameModel.getGeneralBoard().GetIslands());
        setup.SetBag(gameModel.getBag(),0);
        addStudentsOnClouds.RestartTurn(gameModel.getGeneralBoard(), gameModel.getBag(),gameModel.getNumplayers());
        for(int i=0; i <gameModel.getNumplayers(); i++ ){
            gameModel.getGeneralBoard().getSchoolBoard().get(i).PutStudent(gameModel.getNumplayers(),gameModel.getBag());
        }
        addStudentsOnClouds.RestartTurn(gameModel.getGeneralBoard(),gameModel.getBag(), gameModel.getNumplayers());
    }

}


