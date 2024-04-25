package Modell;

import Szkeleton.Szkeleton;
import java.io.*;
import java.util.*;

/**
 * Játék vezérlését végző osztály
 */
public class GameManager {
    /**
     * Játékhoz tartozó labirintus
     */
    Labyrinth labyrinth;
    /**
     * Játék vége
     */
    public void gameEnd(){
    }

    public GameManager(String test) throws FileNotFoundException {
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        String inputFilepath = ".\\Logarlec\\tests\\" + test + "\\" + "map.txt";
        ReadMap(inputFilepath);
        labyrinth.PrintOut();
        String ActFilepath = ".\\Logarlec\\tests\\" + test + "\\" + "act.txt";
        File file = new File(ActFilepath);
        try {
            Scanner fileScanner = new Scanner(file);
            PlayGame(fileScanner);
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("A fájl nem található: " + e.getMessage());
        }
    }


    public GameManager(){
        String inputFilepath = ".\\Logarlec\\tests\\game\\game_input.txt";
        ReadMap(inputFilepath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hány játékos legyen?");


        PlayGame(scanner);
        scanner.close();
    }


    //MÁR CSINÁLJA BARACZK berecki
    public void ReadMap(String filename) {
        Labyrinth l = new Labyrinth();
        l.setGameManager(this);
        ArrayList<BaseItem>Items= new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                String[] parts = command.split(" ");
                String commandName = parts[0];
                switch (commandName) {
                    case "room":
                        String roomName = parts[1];
                        Room r = new Room(roomName);
                        l.addRoom(r);
                        r.setLabyrinth(l);
                        break;
                    case "cursedroom":
                        String cursedroomName = parts[1];
                        CursedRoom cursedr= new CursedRoom(cursedroomName);
                        l.addRoom(cursedr);
                        cursedr.setLabyrinth(l);
                        break;
                    case "capacity":
                        String roomNamec = parts[1];
                        int capacity= Integer.parseInt(parts[2]);
                        for(Room v : l.getRooms()){
                            if(v.Name.equals(roomNamec)){
                                v.capacity=capacity;
                                break;
                            }
                        }
                        break;
                    case "door":
                        String roomDoor1 = parts[1];
                        String roomDoor2 = parts[2];
                        Room r1=null;
                        Room r2=null;
                        for(Room v : l.getRooms()){
                            if(v.Name.equals(roomDoor1)){
                                r1=v;
                            }
                            if(v.Name.equals(roomDoor2)){
                                r2=v;
                            }
                        }
                        if(r1==null || r2== null){
                            System.out.println("Érvénytelen parancs: " + command);
                            break;
                        }
                        r1.addOutgoingDoor(r2);
                        r2.addIncomingDoor(r1);
                        break;
                    case "gas":
                        String roomNameg = parts[1];
                        Boolean siker=false;
                        for(Room v :l.getRooms()){
                            if(v.Name.equals((roomNameg))){
                                v.setGas(true);
                                siker=true;
                            }
                        }
                        if(!siker){
                            System.out.println("Nincs ilyen nevű szoba\n");
                        }
                        break;
                    case "teacher":
                        String teacherName= parts[1];
                        Teacher t= new Teacher(teacherName);
                        l.addTeacher(t);
                        t.setLabyrinth(l);
                        break;
                    case "student":
                        String studentName= parts[1];
                        Student s= new Student(studentName);
                        l.addStudent(s);
                        s.setLabyrinth(l);
                        break;
                    case "cleaner":
                        String cleanerName= parts[1];
                        Takarito c= new Takarito(cleanerName);
                        l.addCleaner(c);
                        c.setLabyrinth(l);
                        break;
                    case "addperson":
                        String room= parts[1];
                        String person= parts[2];
                        Person p=null;
                        Room roomAddPerson=null;
                        for(Room v: l.getRooms()){
                            if(v.Name.equals(room)){
                                roomAddPerson=v;
                                break;
                            }
                        }
                        for(Person v: l.getPeople()){
                            if(v.name.equals(person)){
                                p=v;
                                break;
                            }
                        }
                        roomAddPerson.addPerson(p);
                        p.setCurrentRoom(roomAddPerson);
                        break;
                    case "stun":
                        String stunPlayerName= parts[1];
                        Person stunPerson=null;
                        for(Person v: l.getPeople()){
                            if(v.name.equals(stunPlayerName)){
                                stunPerson=v;
                                break;
                            }
                        }
                        if(stunPerson!=null){
                            stunPerson.stun();
                        }else{
                            System.out.println("Érvénytelen parancs: " + command + "\n");
                        }
                        break;
                    case "tvsz":
                        String tvszName= parts[1];
                        if(parts.length>2){
                            String tvszRem= parts[2];
                            TVSzDenever tvsz= new TVSzDenever(tvszName, Integer.parseInt(tvszRem));
                            Items.add(tvsz);
                        }else{
                            TVSzDenever tvsz= new TVSzDenever(tvszName);
                            Items.add(tvsz);
                        }
                        break;
                    case "cam":
                        String camName= parts[1];
                        Camembert cam= new Camembert(camName);
                        Items.add(cam);
                        break;
                    case "mask":
                        String maskName= parts[1];
                        if(parts.length>2){
                            String maskRem= parts[2];
                            FFP2 mask= new FFP2(maskName, Integer.parseInt(maskRem));
                            Items.add(mask);
                        }else{
                            FFP2 mask= new FFP2(maskName);
                            Items.add(mask);
                        }
                        break;
                    case "fresh":
                        String freshName= parts[1];
                        Legfrissito fresh= new Legfrissito(freshName);
                        Items.add(fresh);
                        break;
                    case "log":
                        String logName= parts[1];
                        Logarlec log= new Logarlec(logName);
                        Items.add(log);
                        break;
                    case "tablatorlo":
                        String tablatorloName= parts[1];
                        NedvesTablatorlo tablatorlo= new NedvesTablatorlo(tablatorloName);
                        Items.add(tablatorlo);
                        break;
                    case "pohar":
                        String poharName= parts[1];
                        if(parts.length>2){
                            String poharRem= parts[2];
                            SzentSorosPoharak pohar= new SzentSorosPoharak(poharName, Integer.parseInt(poharRem));
                            Items.add(pohar);
                        }else{
                            SzentSorosPoharak pohar= new SzentSorosPoharak(poharName);
                            Items.add(pohar);
                        }
                        break;
                    case "tranzi":
                        String tranziName= parts[1];
                        Tranzisztor tranzi= new Tranzisztor(tranziName);
                        Items.add(tranzi);
                        break;
                    case "additem_r":
                        String additemRoomName= parts[1];
                        String additemRName= parts[2];
                        Room additemroom= null;
                        BaseItem additemritem=null;
                        for(Room v: l.getRooms()){
                            if(v.Name.equals(additemRoomName)){
                                additemroom=v;
                                break;
                            }
                        }
                        for(BaseItem v: Items){
                            if(v.name.equals(additemRName)){
                                additemritem=v;
                                break;
                            }
                        }
                        if (additemritem != null && additemritem != null) {
                            additemroom.addItem(additemritem);
                            additemritem.setRoom(additemroom);
                            Items.remove(additemritem);
                        }else{
                            System.out.println("Érvénytelen parancs: " + command + "\n");
                        }
                        break;
                    case "additem_p":
                        String additemPersonName= parts[1];
                        String additemPName= parts[2];
                        Person additemp= null;
                        BaseItem additempitem=null;
                        for(Person v: l.getPeople()){
                            if(v.name.equals(additemPersonName)){
                                additemp=v;
                                break;
                            }
                        }
                        for(BaseItem v: Items){
                            if(v.name.equals(additemPName)){
                                additempitem=v;
                                break;
                            }
                        }
                        if(additemp!=null && additempitem!=null){
                            additemp.addItem(additempitem);
                            additempitem.setHolder(additemp);
                            Items.remove(additempitem);
                        }else{
                            System.out.println("Érvénytelen parancs: " + command + "\n");
                        }

                        break;
                    case "fake":
                        String fakeName= parts[1];
                        BaseItem fake=null;
                        for(BaseItem v: Items){
                            if(v.name.equals((fakeName))){
                                fake=v;
                            }
                        }
                        fake.setFake(true);
                        break;
                    case "active":
                        String activeName= parts[1];
                        BaseItem active=null;
                        for(BaseItem v: Items){
                            if(v.name.equals((activeName))){
                                active=v;
                            }
                        }
                        active.setActive(true);
                        break;

                    default:
                        System.out.println("Érvénytelen parancs: " + command + "\n");
                }
            }
            scanner.close();
            System.out.println("The input has been loaded.");
            this.labyrinth = l;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void PlayGame(Scanner scanner){
        String line = scanner.nextLine();
        Boolean random= false;
        String[] parts = line.split(" ");
        String commandName = parts[0];
        while(!line.equals("init")) {
            if(commandName.equals(random)){
                    if (parts[1].equals(true)) {
                        random = true;
                    } else if (parts[1].equals(false)) {
                        random = false;
                    }
            }
            line = scanner.nextLine();
        }
        ArrayList<Person> people;
        if(random==true){
            people=labyrinth.getPeople(); // ITT HA GLOBÁLIS LESZ A RANDOM AKKOR NEM MINDENKIT AD VISSZA HANEM CSAK A PLAYEREKET
        }else{
            people=labyrinth.getPeople();
        }

        while (!labyrinth.Game_End) {
            for(Person p: people){
                //DE HOGY A GECIBE

            }
        }
    }
}