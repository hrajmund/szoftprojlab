package     Modell;

import Grafikus.GuiManager;

import java.io.*;
import java.util.*;

/**
 * Játék vezérlését végző osztály
 */
public class GameManager {
    /**
     * Játékhoz tartozó labirintus
     */
    public Labyrinth labyrinth;
    
    ///* változók amik a grafikus futtatáshoz kellenek*///
    
    private static GuiManager guiManager = null;
    public GuiManager getGamePanel() {
        return guiManager;
    }
    
    
    private int roundCounter = 1;
    
    public int getRoundCounter() {
        return roundCounter;
    }
    
    private int currentPlayerIndex = 0;

    ///* változók amik a grafikus futtatáshoz kellenek VÉGE*///
    
    /**
     * Üres konstruktor
     */
    public GameManager() {}
    
    /**
     * Test futtatáshoz szükséges konstruktor
     */
    public GameManager(String test) throws FileNotFoundException {
        runtest(test);
    }
    
    public void runtest(String test){
        String inputFilepath = ".\\tests\\" + test + "\\" + "map.txt";
        ReadMap(inputFilepath);
        String ActFilepath = ".\\tests\\" + test + "\\" + "act.txt";
        File file = new File(ActFilepath);
        try {
            Scanner fileScanner = new Scanner(file);
            PlayGame(fileScanner);
            fileScanner.close();
            FileWriter fileWriter = new FileWriter(".\\tests\\" + test + "\\" + "out.txt");
            PrintWriter filePrintWriter = new PrintWriter(fileWriter);
            labyrinth.PrintOut(filePrintWriter);
            filePrintWriter.flush();
            filePrintWriter.close();
        } catch (IOException e) {
            System.out.println("A fájl nem található: " + e.getMessage());
        }
    }
    
    /**
     * Játék inicializálása, csak manuális teszt és cmd futtatáshoz
     */
    public GameManager(int a) throws IOException {
        //String inputFilepath = ".\\Logarlec\\tests\\game\\game_input.txt";
        String inputFilepath = ".\\game\\map.txt";
        ReadMap(inputFilepath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of players:");
        int playerNum = scanner.nextInt();
        System.out.println("Please type in your name!");
        for(int i = 0; i < playerNum; i++){
            System.out.println("Player " + (i+1) + " - ");
            String playerName = scanner.next();
            Student player = new Student(playerName);
            labyrinth.addStudent(player);
            player.setLabyrinth(labyrinth);
            labyrinth.getRooms().get(0).addPerson(player);
            player.setCurrentRoom(labyrinth.getRooms().get(0));
        }
        PlayGame(scanner);
        scanner.close();
    }
    
    
    /**
     * A játék térkép inicializálása
     */
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
                        CursedRoom cursedr = new CursedRoom(cursedroomName);
                        l.addRoom(cursedr);
                        cursedr.setLabyrinth(l);
                        
                        break;
                    case "capacity":
                        String roomNamec = parts[1];
                        int capacity = Integer.parseInt(parts[2]);
                        for (Room v : l.getRooms()) {
                            if (v.Name.equals(roomNamec)) {
                                v.capacity = capacity;
                                break;
                            }
                        }
                        break;
                    case "door":
                        String roomDoor1 = parts[1];
                        String roomDoor2 = parts[2];
                        Room r1 = null;
                        Room r2 = null;
                        for (Room v : l.getRooms()) {
                            if (v.Name.equals(roomDoor1)) {
                                r1 = v;
                            }
                            if (v.Name.equals(roomDoor2)) {
                                r2 = v;
                            }
                        }
                        if (r1 == null || r2 == null) {
                            System.out.println("Unknown Command: " + command);
                            break;
                        }
                        r1.addOutgoingDoor(r2);
                        r2.addIncomingDoor(r1);
                        
                        if (guiManager != null){
                            guiManager.getGraphComponent().addEdge(r1, r2);
                        }
                        break;
                    case "gas":
                        String roomNameg = parts[1];
                        Boolean siker = false;
                        for (Room v : l.getRooms()) {
                            if (v.Name.equals((roomNameg))) {
                                v.setGas(true);
                                siker = true;
                            }
                        }
                        if (!siker) {
                            System.out.println("Nincs ilyen nevű szoba\n");
                        }
                        
                        
                        break;
                    case "teacher":
                        String teacherName = parts[1];
                        Teacher t = new Teacher(teacherName);
                        l.addTeacher(t);
                        t.setLabyrinth(l);
                        break;
                    case "student":
                        String studentName = parts[1];
                        Student s = new Student(studentName);
                        l.addStudent(s);
                        s.setLabyrinth(l);
                        
                        break;
                    case "cleaner":
                        String cleanerName = parts[1];
                        Takarito c = new Takarito(cleanerName);
                        l.addCleaner(c);
                        c.setLabyrinth(l);
                        break;
                    case "addperson":
                        String room = parts[1];
                        String person = parts[2];
                        Person p = null;
                        Room roomAddPerson = null;
                        for (Room v : l.getRooms()) {
                            if (v.Name.equals(room)) {
                                roomAddPerson = v;
                                break;
                            }
                        }
                        for (Person v : l.getPeople()) {
                            if (v.name.equals(person)) {
                                p = v;
                                break;
                            }
                        }
                        p.setCurrentRoom(roomAddPerson);
                        roomAddPerson.addPerson(p);
                        break;
                    case "stun":
                        String stunPlayerName = parts[1];
                        Person stunPerson = null;
                        for (Person v : l.getPeople()) {
                            if (v.name.equals(stunPlayerName)) {
                                stunPerson = v;
                                break;
                            }
                        }
                        if (stunPerson != null) {
                            stunPerson.stun();
                        } else {
                            System.out.println("Unknown Command: " + command + "\n");
                        }
                        break;
                    case "tvsz":
                        String tvszName = parts[1];
                        if (parts.length > 2) {
                            String tvszRem = parts[2];
                            TVSzDenever tvsz = new TVSzDenever(tvszName, Integer.parseInt(tvszRem));
                            Items.add(tvsz);
                        } else {
                            TVSzDenever tvsz = new TVSzDenever(tvszName);
                            Items.add(tvsz);
                        }
                        break;
                    case "cam":
                        String camName = parts[1];
                        Camembert cam = new Camembert(camName);
                        Items.add(cam);
                        break;
                    case "mask":
                        String maskName = parts[1];
                        if (parts.length > 2) {
                            String maskRem = parts[2];
                            FFP2 mask = new FFP2(maskName, Integer.parseInt(maskRem));
                            Items.add(mask);
                        } else {
                            FFP2 mask = new FFP2(maskName);
                            Items.add(mask);
                        }
                        break;
                    case "fresh":
                        String freshName = parts[1];
                        Legfrissito fresh = new Legfrissito(freshName);
                        Items.add(fresh);
                        break;
                    case "log":
                        String logName = parts[1];
                        Logarlec log = new Logarlec(logName);
                        Items.add(log);
                        break;
                    case "tablatorlo":
                        String tablatorloName = parts[1];
                        if (parts.length > 2) {
                            String tablatorRem = parts[2];
                            NedvesTablatorlo tablatorlo = new NedvesTablatorlo(tablatorloName, Integer.parseInt(tablatorRem));
                            Items.add(tablatorlo);
                        } else {
                            NedvesTablatorlo tablatorlo = new NedvesTablatorlo(tablatorloName);
                            Items.add(tablatorlo);
                        }
                        break;
                    case "pohar":
                        String poharName = parts[1];
                        if (parts.length > 2) {
                            String poharRem = parts[2];
                            SzentSorosPoharak pohar = new SzentSorosPoharak(poharName, Integer.parseInt(poharRem));
                            Items.add(pohar);
                        } else {
                            SzentSorosPoharak pohar = new SzentSorosPoharak(poharName);
                            Items.add(pohar);
                        }
                        break;
                    case "tranzi":
                        String tranziName = parts[1];
                        Tranzisztor tranzi = new Tranzisztor(tranziName);
                        Items.add(tranzi);
                        break;
                    case "additem_r":
                        String additemRoomName = parts[1];
                        String additemRName = parts[2];
                        Room additemroom = null;
                        BaseItem additemritem = null;
                        for (Room v : l.getRooms()) {
                            if (v.Name.equals(additemRoomName)) {
                                additemroom = v;
                                break;
                            }
                        }
                        for (BaseItem v : Items) {
                            if (v.name.equals(additemRName)) {
                                additemritem = v;
                                break;
                            }
                        }
                        if (additemritem != null && additemritem != null) {
                            additemroom.addItem(additemritem);
                            additemritem.setRoom(additemroom);
                            Items.remove(additemritem);
                        } else {
                            System.out.println("Unknown Command: " + command + "\n");
                        }
                        break;
                    case "additem_p":
                        String additemPersonName = parts[1];
                        String additemPName = parts[2];
                        Person additemp = null;
                        BaseItem additempitem = null;
                        for (Person v : l.getPeople()) {
                            if (v.name.equals(additemPersonName)) {
                                additemp = v;
                                break;
                            }
                        }
                        for (BaseItem v : Items) {
                            if (v.name.equals(additemPName)) {
                                additempitem = v;
                                break;
                            }
                        }
                        if (additemp != null && additempitem != null) {
                            additemp.addItem(additempitem);
                            additempitem.setHolder(additemp);
                            Items.remove(additempitem);
                        } else {
                            System.out.println("Unknown Command: " + command + "\n");
                        }

                        break;
                    case "fake":
                        String fakeName = parts[1];
                        BaseItem fake = null;
                        for (BaseItem v : Items) {
                            if (v.name.equals((fakeName))) {
                                fake = v;
                            }
                        }
                        fake.setFake(true);
                        break;
                    case "active":
                        String activeName = parts[1];
                        BaseItem active = null;
                        for (BaseItem v : Items) {
                            if (v.name.equals((activeName))) {
                                active = v;
                            }
                        }
                        active.setActive(true);
                        break;

                    default:
                        System.out.println("Unknown Command: " + command + "\n");
                }
            }    
            scanner.close();
            System.out.println("The input has been loaded.");
            this.labyrinth = l;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    
    /**
     * Játék futtatása, csak manuális teszt és cmd futtatáshoz
     */ 
    public void PlayGame(Scanner scanner) throws IOException {
        String line = scanner.nextLine();
        Boolean random= false;
        String[] parts = line.split(" ");
        String commandName = parts[0];
        while(!line.equals("init")) {
            if(commandName.equals("random")){
                    if (parts[1].equals("true")) {
                        random = true;
                        labyrinth.random = true;

                    } else if (parts[1].equals("false")) {
                        random = false;
                        labyrinth.random = false;
                    }
            }
            line = scanner.nextLine();
        }
        ArrayList<Person> people;
        if(random){
            //people=labyrinth.getPeople(); // ITT HA GLOBÁLIS LESZ A RANDOM AKKOR NEM MINDENKIT AD VISSZA HANEM CSAK A PLAYEREKET
            people=labyrinth.getStudents();
        }else{
            people=labyrinth.getPeople();
        }

        while (!labyrinth.Game_End) {
            ArrayList<Person> peopleCopy = new ArrayList<>(people);
            for(Person p: peopleCopy){
                if(!labyrinth.getPeople().contains((p)) || p.getStun()){
                    if(p.getStun()){
                        p.setStun(false);
                        //pop_UP_WINDOW
                    }
                    continue;
                }
                String commandName_;
                do{
                    line=scanner.nextLine();
                    parts = line.split(" ");
                     commandName_ = parts[0];
                    switch (commandName_) {
                        case "neighbours":
                            for(Room r : p.getCurrentRoom().getOutgoingDoors()){
                                r.PrintOutRoom(new PrintWriter(System.out));
                            }
                            break;
                        case "move":
                            Room r=null;
                            //Person movep=null;
                            //String movepName= parts[1];
                            String moverName= parts[1];
                            for(Room v : labyrinth.getRooms()){
                                if(v.getName().equals(moverName)){
                                    r=v;
                                    break;
                                }
                            }
                            /*
                            for(Person v :people){
                                if(v.getName().equals(movepName)){
                                    movep=v;
                                    break;
                                }
                            }*/
                            if(r!=null){
                                System.out.println(p.getName() + " moved from " + p.getCurrentRoom().getName() + " to " + r.getName() + ".");
                                p.move(r);
                            }else{
                                System.out.println("Unknown Command: " + line + "\n");
                            }
                            break;
                        case "roomitems":
                            if(p.getCurrentRoom().getItems()!=null){
                                for(BaseItem v: p.getCurrentRoom().getItems()){
                                    v.PrintOutItem(new PrintWriter(System.out));
                                }
                            }else{
                                System.out.println("Theres is no Item\n");
                            }

                            break;
                        case "pickup":
                            String pickupItemName=parts[1];
                            BaseItem pickupItem=null;
                            int parsed = Integer.parseInt(pickupItemName);
                            if(p.getCurrentRoom().getItems().size()>=parsed) {
                                pickupItem = p.getCurrentRoom().getItems().get(parsed);
                            }
                            if(pickupItem!=null){
                                System.out.println(p.getName() + " picked up " + pickupItem.getName() + ".");
                                p.pickUpItem(pickupItem);
                            }else {
                                System.out.println("Unknown Command: " + line + "\n");
                            }

                            break;
                        case "ownitems":
                            if(p.getItems()!=null){
                                for(BaseItem v: p.getItems()){
                                    v.PrintOutItem(new PrintWriter(System.out));
                                }
                            }else{
                                System.out.println("Nincs Item\n");
                            }

                            break;
                        case "useitem":
                            BaseItem useItem= null;
                            String useitemName=parts[1];
                            if(p.getItems().size()>=Integer.parseInt(useitemName)) {
                                useItem = p.getItems().get(Integer.parseInt(useitemName));
                            }
                            if(useItem!=null){
                                System.out.println(p.getName() + " used " + useItem.getName() + ".");
                                p.UseItem(p.getItems().indexOf(useItem));
                            }else{
                                System.out.println("Nincs ilyen Item\n");
                            }
                            break;
                        case "putdown":
                            String putdownItemName=parts[1];
                            BaseItem putdownItem=null;
                            if(p.getItems().size()>=Integer.parseInt(putdownItemName)) {
                                putdownItem = p.getItems().get(Integer.parseInt(putdownItemName));
                            }
                            if(putdownItem!=null){
                                p.putDownItem(putdownItem);
                            }else {
                                System.out.println("Unknown Command: " + line + "\n");
                            }
                            break;
                        case "next":
                            System.out.println(p.getName()+" stays in "+p.getCurrentRoom().getName());
                            break;
                        case "list":
                            PrintWriter consoleWriter = new PrintWriter(System.out);
                            labyrinth.PrintOut(consoleWriter);
                            consoleWriter.flush();
                            consoleWriter.close();
                            break;
                        case "save":
                            System.out.println("The output has been saved.");
                            System.out.println();
                            return;
                        case "merge":
                            String room0Tomerge = parts[1];
                            String room1Tomerge = parts[2];
                            for (Room mRoom0 : labyrinth.getRooms()) {
                                if (mRoom0.getName().equals(room0Tomerge)) {
                                    for (Room mRoom1 : labyrinth.getRooms()) {
                                        if (mRoom1.getName().equals(room1Tomerge)) {
                                            mRoom0.merge(mRoom1);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        case "split":
                            String roomToSplit = parts[1];
                            for (Room croom : labyrinth.getRooms()) {
                                if (croom.getName().equals(roomToSplit)) {
                                    croom.split();
                                    break;
                                }
                            }
                            break;
                        case "curse":
                            if(!random) {
                                String roomToCurse = parts[1];
                                for (Room croom : labyrinth.getRooms()) {
                                    if (croom.getName().equals(roomToCurse)) {
                                        croom.cursed();
                                        break;
                                    }
                                }
                            }
                            break;
                        default:
                            System.out.println("Unknown Command: " + line + "\n");
                    }

                }while(!commandName_.equals("next") && !commandName_.equals("move"));
            }
            if(random){
               // labyrinth.Randomizer();
            }
            labyrinth.tick();
            //ezzel vmit kezdeni kell mert igy nemjo, itt a 9-es tesztnél végtelen ciklusba kerül,
            //(és ez nem a randomgergqrva függvény miatt, azelőtt is fennállt a hiba)
        }
    }
    
    /**
     * Játék inicializálása grafikus futtatáshoz
     */
    public GameManager(GuiManager gp, List<String> playerNames){
        this.guiManager = gp;
        
        ReadMap(".\\game\\map.txt");
        
        for(String playerName : playerNames){
            Student player = new Student(playerName);
            labyrinth.addStudent(player);
            player.setLabyrinth(labyrinth);
            labyrinth.getRooms().get(0).addPerson(player);
            player.setCurrentRoom(labyrinth.getRooms().get(0));
        }
        
        currentPlayerIndex = 0;
        
        guiManager.setCurrentStudent((Student) labyrinth.getStudents().get(currentPlayerIndex));
        guiManager.updateRoundCount(roundCounter);
    }
    
    public static GuiManager getGuiManager(){
        return guiManager;
    }
    
    public void movePlayer(Student player, Room room){
        player.move(room);
        this.next();
    }
    
    public void next(){
        
        if(labyrinth.getStudents().isEmpty() || labyrinth.Game_End){
            guiManager.GameEndPopUp();
            return;
        }
        if(labyrinth.getStudents().contains(guiManager.currentStudent)){ 
            currentPlayerIndex++;
        }
        if(currentPlayerIndex >= labyrinth.getStudents().size()){
            currentPlayerIndex = 0;
            labyrinth.Randomizer();
            labyrinth.tick();
            roundCounter++;
            guiManager.updateRoundCount(roundCounter);
        }
        
        while(labyrinth.getStudents().get(currentPlayerIndex).getStun()){
               
            if (labyrinth.getStudents().get(currentPlayerIndex).getStun()){
                guiManager.StunnedPopUp(labyrinth.getStudents().get(currentPlayerIndex));
                labyrinth.getStudents().get(currentPlayerIndex).setStun(false);
            }
            
            currentPlayerIndex++;
            if(currentPlayerIndex >= labyrinth.getStudents().size()){
                currentPlayerIndex = 0;
                labyrinth.Randomizer();
                labyrinth.tick();
                roundCounter++;
                guiManager.updateRoundCount(roundCounter);
            }
        }
        guiManager.setCurrentStudent((Student) labyrinth.getStudents().get(currentPlayerIndex));
    }
    
    public void pickUpItem(Student player, int itemRoomIndex){
        player.pickUpItem(player.getCurrentRoom().getItems().get(itemRoomIndex));
    }
    
    public void putDownItem(Student player, int itemIndex){
        player.putDownItem(player.getItems().get(itemIndex));
    }
    
    public void useItem(Student player, int itemIndex){
        player.UseItem(itemIndex);
    }
    



}