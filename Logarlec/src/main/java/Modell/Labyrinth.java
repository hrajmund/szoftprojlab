package Modell;
import Modell.GameManager;
import Modell.IRound;
import Modell.Room;
import Modell.TestPrinter;

import java.io.PrintWriter;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

/**
 * A labririntust reprezentáló játék.
 */
public class Labyrinth implements IRound {

    boolean Game_End = false;

    /**
     * A labirintusban található szobák listája.
     */
    private ArrayList<Room> rooms = new ArrayList<>();

    /**
     * A labirintusban található diákok listája.
     */
    private ArrayList<Person> players = new ArrayList<>();

    /**
     * A labirintusban található tanárok listája.
     */
    private ArrayList<Person> teachers = new ArrayList<>();

    /**
     * A labirintusban található tanárok listája.
     */
    private ArrayList<Person> cleaner= new ArrayList<>();

    /**
     * A játékmenedzser, amely felelős a játék kezeléséért.
     */
    private GameManager GM;

    /**
     * Eltávolít egy szobát a labirintusból.
     *
     * @param r Az eltávolítandó szoba.
     */
    public void removeRoom(Room r){

    }

    /**
     * Eltávolít egy diákot a labirintusból.
     *
     * @param s Az eltávolítandó diák.
     */
    public void removeStudent(Student s) {
        players.remove(s);
        if(players.isEmpty()){
            Game_End = true;
        }
    }

    /**
     * A kört lépteti.
     */
    public void tick(){
        for(Person p: players){
            if(p.getItems().isEmpty()){
                continue;
            }
        }
        for(Room r: rooms){
            if(r.getItems().isEmpty()){
                continue;
            }
            for(BaseItem i:r.getItems()){
                i.tick();
            }
            r.tick();
        }
    }

    /**
     * Új kört kezd.
     */
    public void newRound(){
        if(players.isEmpty()){
            Game_End = true;
        }
    }

    /**
     * Hozzáad egy diákot a labirintushoz.
     *
     * @param s A hozzáadandó diák.
     */
    public void addStudent(Student s){
        players.add(s);
        s.setLabyrinth(this);
    }

    /**
     * Hozzáad egy tanárt a labirintushoz.
     *
     * @param t A hozzáadandó tanár.
     */
    public void addTeacher(Teacher t){
        teachers.add(t);
        t.setLabyrinth(this);
    }

    /*
        * Hozzáad egy takarítót a labirintushoz.
        * @param t A hozzáadandó takarító.
     */
    public void addCleaner(Takarito t) {
        cleaner.add(t);
        t.setLabyrinth(this);
    }


    /**
     * Hozzáad egy szobát a labirintushoz.
     *
     * @param r A hozzáadandó szoba.
     */
    public void addRoom(Room r) {
        rooms.add(r);
    }

    /**
     * Befejezi a játékot.
     */
    public void endGame(){
        Game_End = true;
    }

    /**
     * Beállítja a játékmenedzsert.
     *
     * @param gm A beállítandó játékmenedzser.
     */
    public void setGameManager(GameManager gm) {
        GM=gm;
    }

    public ArrayList<Room>getRooms(){
        return rooms;
    }
    public ArrayList<Person>getPeople(){
        ArrayList<Person> people= new ArrayList<>();
        people.addAll(this.players);
        people.addAll(this.cleaner);
        people.addAll(this.teachers);
        return people;
    }
    public ArrayList<Person>getStudents(){
        return players;
    }

    public void PrintOut(PrintWriter writer){
        if(Game_End){
            writer.println("GAME_END");
        }else {
            for (Room r : this.getRooms()) {
                r.PrintOutRoom(writer);
                if(!r.getPeople().isEmpty()){
                    for (Person person : r.getPeople()) {
                        person.PrintOutPerson(writer);
                    }
                }else{
                    writer.println();
                    for (Person person : r.getPeople()) {
                        person.PrintOutPerson(writer);
                    }
                }
            }
        }
    }

    public void RandomGergQrva(){
        Random rand= new Random();
        if(!cleaner.isEmpty()){
            for(Person c: cleaner){
                int percent=rand.nextInt(2);
                if(percent==0){
                    int roomNumber=rand.nextInt(c.getCurrentRoom().movePossibilities().size());
                    c.move(c.getCurrentRoom().movePossibilities().get(roomNumber));
                }
            }
        }
        //same for teacher
        if(!teachers.isEmpty()){
            for(Person t: teachers){
                int percent=rand.nextInt(10);
                if(percent<3){
                    int doingthing=rand.nextInt(2);
                    if(doingthing==0){
                        percent= rand.nextInt(t.getCurrentRoom().getItems().size());
                        t.pickUpItem(t.getCurrentRoom().getItems().get(percent));
                    }else{
                        percent=rand.nextInt(t.getItems().size());
                        t.putDownItem(t.getItems().get(percent));
                    }
                }
                percent=rand.nextInt(10);
                if(percent<4){
                    int roomNumber=rand.nextInt(t.getCurrentRoom().movePossibilities().size());
                    t.move(t.getCurrentRoom().movePossibilities().get(roomNumber));
                }
            }
        }
    }

}

