import java.util.ArrayList;
import java.util.List;

public class GameManager {
    Labyrinth gameMap;
    ArrayList<IPlayer> players;
    ArrayList<Tranzisztor> fifoTransistorList;
    boolean pickedUpLogarlec;
    public GameManager(){}
    public void Spawn(){}
    public void ReadFile(){}
    public Labyrinth getGameMap() {
        return gameMap;
    }
    public void setGameMap(Labyrinth gameMap) {
        this.gameMap = gameMap;
    }
    public ArrayList<IPlayer> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<IPlayer> players) {
        this.players = players;
    }
    public boolean getLogarlec() {
        return pickedUpLogarlec;
    }
    public void setHasLogarlec(boolean hasLogarlec) {
        this.pickedUpLogarlec = hasLogarlec;
    }
    public void gameEnd(){}
    public ArrayList<Tranzisztor> getFifoTransistorList() {
        return fifoTransistorList;
    }
    public void setFifoTransistorList(ArrayList<Tranzisztor> fifoTransistorList) {
        this.fifoTransistorList = fifoTransistorList;
    }
    public void addTransistor(Tranzisztor t){
        fifoTransistorList.add(t);
    }
    public void removeTransistorPair(){}
    public void newRound(){}
}
