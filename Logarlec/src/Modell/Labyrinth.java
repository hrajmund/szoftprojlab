package Modell;
import Modell.GameManager;
import Modell.IRound;
import Modell.Room;
import Modell.TestPrinter;

import java.util.ArrayList;

/**
 * A labririntust reprezentáló játék.
 */
public class Labyrinth implements IRound {

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
     * A játékmenedzser, amely felelős a játék kezeléséért.
     */
    private GameManager GM;

    /**
     * Eltávolít egy szobát a labirintusból.
     *
     * @param r Az eltávolítandó szoba.
     */
    public void removeRoom(Room r){
        TestPrinter.printCallingMethod(r);
    }

    /**
     * Eltávolít egy diákot a labirintusból.
     *
     * @param s Az eltávolítandó diák.
     */
    public void removeStudent(Student s) {
        TestPrinter.printCallingMethod(s);
        players.remove(s);
        if(players.isEmpty()){
            GM.gameEnd();
        }
    }

    /**
     * A kört lépteti.
     */
    public void tick(){
        TestPrinter.printCallingMethod();
    }

    /**
     * Új kört kezd.
     */
    public void newRound(){
        TestPrinter.printCallingMethod();
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
        TestPrinter.printCallingMethod(s);
        players.add(s);
        s.setLabyrinth(this);
    }

    /**
     * Hozzáad egy tanárt a labirintushoz.
     *
     * @param t A hozzáadandó tanár.
     */
    public void addTeacher(Teacher t){
        TestPrinter.printCallingMethod(t);
        teachers.add(t);
        t.setLabyrinth(this);
    }

    /**
     * Hozzáad egy szobát a labirintushoz.
     *
     * @param r A hozzáadandó szoba.
     */
    public void addRoom(Room r) {
        TestPrinter.printCallingMethod(r);
    }

    /**
     * Befejezi a játékot.
     */
    public void endGame(){
        TestPrinter.printCallingMethod();
        GM.gameEnd();
    }

    /**
     * Beállítja a játékmenedzsert.
     *
     * @param gm A beállítandó játékmenedzser.
     */
    public void setGameManager(GameManager gm) {
        TestPrinter.printCallingMethod(gm);
        GM=gm;
    }

}

