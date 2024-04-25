package Modell;
import Modell.GameManager;
import Modell.IRound;
import Modell.Room;
import Modell.TestPrinter;

import java.math.RoundingMode;
import java.util.ArrayList;

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
    private ArrayList<Student> players = new ArrayList<>();

    /**
     * A labirintusban található tanárok listája.
     */
    private ArrayList<Teacher> teachers = new ArrayList<>();

    /**
     * A labirintusban található tanárok listája.
     */
    private ArrayList<Takarito> cleaner= new ArrayList<>();

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
            GM.gameEnd();
        }
    }

    /**
     * A kört lépteti.
     */
    public void tick(){

    }

    /**
     * Új kört kezd.
     */
    public void newRound(){
        if(players.isEmpty()){
            GM.gameEnd();
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
        GM.gameEnd();
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
        people.addAll(this.cleaner);
        people.addAll(this.players);
        people.addAll(this.teachers);
        return people;
    }

    public void PrintOut(){
        if(Game_End){
            System.out.println("GAME_END");
        }else {
            for (Room r : this.getRooms()) {
                r.PrintOutRoom();
                for (Person person : r.getPeople()) {
                    person.PrintOutPerson();
                }
            }
        }
    }

}

