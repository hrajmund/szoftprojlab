package Modell;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * A labririntust reprezentáló játék.
 */
public class Labyrinth implements IRound {

    boolean Game_End = false;
    
    public boolean isGame_End() {
        return Game_End;
    }

    public boolean random = true;
    
    private int incrementRoomNumber = 1;

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
    
    public GameManager getGameManager(){
        return GM;
    }

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
            if(!p.getItems().isEmpty()){
                ArrayList<BaseItem> items= new ArrayList<>(p.getItems());
                for(BaseItem i: items){
                    i.tick();
                }
            }
        }
        for(Room r: rooms){
            if(r.getItems().isEmpty()) {
                continue;
            }
                ArrayList<BaseItem> roomitems = new ArrayList<>(r.getItems());
                for (BaseItem i : roomitems) {
                    i.tick();
                }
        }
        
        if(random){
            Random rand = new Random();
            if(rand.nextInt(10) < 2){
                int roomNumber = rand.nextInt(rooms.size());
                while(!rooms.get(roomNumber).people.isEmpty()){
                    roomNumber = (roomNumber+1) % rooms.size();
                }
                rooms.get(roomNumber).split();
            }
            if(rand.nextInt(10)<2){
                boolean merged = false;
                int randomINT = rand.nextInt(rooms.size());
                for(int i = 0; i < rooms.size(); i++){
                    int actRand = (i+randomINT) % rooms.size();
                    if(rooms.get(actRand).getPeople().isEmpty()){
                        
                        HashSet<Room> neighbours = new HashSet<>();
                        neighbours.addAll(rooms.get(actRand).outgoingDoors);
                        neighbours.addAll(rooms.get(actRand).incomingDoors);
                        
                        for(Room tomergewith : neighbours){
                            if(tomergewith.getPeople().isEmpty()){
                                rooms.get(actRand).merge(tomergewith);
                                
                                merged = true;
                                break;
                            }
                        }
                    }
                    if(merged){
                        break;
                    }
                }   
            }
        }
    }
    
    private boolean hasEmptyNeighbour(Room r){
        //for (Room room : r.
             //) {
            
        //}
        return false;
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
        incrementRoomNumber++;
        if(getGameManager().getGamePanel() != null){
            getGameManager().getGamePanel().getGraphComponent().addNode(r);
        }
    }
    
    public int getNextRoomNumber(){
        return incrementRoomNumber;
    }

    /**
     * Befejezi a játékot.
     */
    public void endGame(){
        getGameManager();
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
        //boolean isFirstRoom = true;
        if(Game_End){
            writer.println("GAME_END");
        }else {
            for (Room r : this.getRooms()) {
//                if(!isFirstRoom)
//                    writer.print("\n"); //ha nem első szoba, tegyen sortötést (azért, hogy a txt ne kezdődjön üres sorral
//                isFirstRoom = false;
                r.PrintOutRoom(writer);
                if(!r.getPeople().isEmpty()){
                    for (Person person : r.getPeople()) {
                        person.PrintOutPerson(writer);
                    }
                }else{
                    //writer.println();
                    for (Person person : r.getPeople()) {
                        person.PrintOutPerson(writer);
                    }
                }
            }
        }
    }

    public void Randomizer(){
        Random rand= new Random();
        //same for teacher
        if(!teachers.isEmpty()){
            for(Person t: teachers){
                if(t.getStun()){
                    t.setStun(false);
                    continue;
                }
                int percent=rand.nextInt(10);
                if(percent<3){
                    int doingthing=rand.nextInt(2);
                    if(doingthing==0 && !t.getCurrentRoom().getItems().isEmpty()){
                        percent= rand.nextInt(t.getCurrentRoom().getItems().size());
                        t.pickUpItem(t.getCurrentRoom().getItems().get(percent));
                    }else if(!t.getItems().isEmpty()){
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
        if(!cleaner.isEmpty()){
            for(Person c: cleaner){
                int percent=rand.nextInt(2);
                if(percent==0){
                    int roomNumber=rand.nextInt(c.getCurrentRoom().movePossibilities().size());
                    c.move(c.getCurrentRoom().movePossibilities().get(roomNumber));
                }
            }
        }
        
    }
    


}

