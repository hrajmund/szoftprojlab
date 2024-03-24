import javax.swing.*;
import javax.swing.text.TabableView;
import java.util.*;

public class Szkeleton {

    /* KOMMUNIKÁCIÓS A DIAGRAMOK
    StudentEntersARoomFromAnotherK() {
        //STUDENT ENTERS THE ROOM FROM ANOTHER//
        Student s = new Student();
        Room r1 = new Room();
        Room r2 = new Room();
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
    }

    StudentEntersARoomAndHasAnFFP2MaskK() {
        Student s = new Student();
        Room r1 = new Room();
        Room r2 = new Room();
        FFP2 mask = new FFP2();
        s.addItem(mask);
        mask.setHolder(s);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
    }

    StudentEntersTheRoomWhereATeacherIsInItAndHasNoProtectionAgainstItK() {
        Student s = new Student();
        Teacher t = new Teacher();
        Labyrinth l = new Labyrinth();
        Room r1 = new Room();
        Room r2 = new Room();
        l.addStudent(s);
        l.addTeacher(t);
        l.addRoom(r1);
        l.addRoom(r2);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);
    }

    StudentEntersThRoomWhereATeacherIsInAndHasTVSZDeneverAgainstItK() {
        Student s = new Student();
        Teacher t = new Teacher();
        Room r1 = new Room();
        Room r2 = new Room();
        TVSzDenever tvsz = new TVSzDenever();
        s.addItem(tvsz);
        tvsz.setHolder(s);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);
    }

    StudentEntersTheRoomWhereATeacherIsInItAndHasSzentSorosPoharakAgainstItK() {
        Student s = new Student();
        Teacher t = new Teacher();
        Room r1 = new Room();
        Room r2 = new Room();
        SzentSorosPoharak pohar = new SzentSorosPoharak();
        s.addItem(pohar);
        pohar.setHolder(s);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);
    }

    StudentInARoomAndThereIsEveryItemInItK() {
        Room r = new Room();
        Student s = new Student();
        FFP2 mask = new FFP2();
        Tranzisztor tran = new Tranzisztor();
        Camembert cam = new Camembert();
        Logarlec log = new Logarlec();
        TVSzDenever tvsz = new TVSzDenever();
        NedvesTablatorlo tablatorlo = new NedvesTablatorlo();
        SzentSorosPoharak pohar= new SzentSorosPoharak();
        ArrayList<BaseItem> items= new ArrayList<>();
        items.add(mask);
        items.add(tran);
        items.add(cam);
        items.add(log);
        items.add(tvsz);
        items.add(tablatorlo);
        items.add(pohar);
        r.addItems(items);
        r.addPerson(s);
        s.setCurrentRoom(r);
    }

    StudentDropsAnItemFromItHand(){
        Student s= new Student();
        Room r1= new Room();
        TVSzDenever tvsz= new TVSzDenever();
        s.addItem(tvsz);
        tvsz.setHolder(s);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
    }

    TwoRoomsMerge(){
        Labyrinth l = new Labyrinth();
        Room r1= new Room();
        Room r2= new Room();
        l.addRoom(r1);
        l.addRoom(r2);
    }

    RoomSplit(){
        Labyrinth l= new Labyrinth();
        Room r= new Room();
        l.addRoom(r);
    }

    CursedRoom(){
        Room r1=new Room();
        Room r2=new Room();
        CursedRoom cursed= new CursedRoom();
        r1.addOutgoingDoor(cursed);
        cursed.addIncomingDoor(r1);
        r2.addIncomingDoor(cursed);
        cursed.addOutgoingDoor(r2);
    }

    TeacherEntersARooom(){
        Teacher t= new Teacher();
        Room r1= new Room();
        Room r2= new Room();
        r1.addOutgoingDoor(r2);
        r2.addIncomingDoor(r1);
        r1.addPerson(t);
        t.setCurrentRoom(r1);
    }

    TeacherEntersARoomWithAnActiveNedvesTablatorloInIt(){
        NedvesTablatorlo rongy= new NedvesTablatorlo();
        Room r1= new Room();
        Room r2= new Room();
        Teacher t= new Teacher();
        r1.addIncomingDoor(r2);
        r2.addOutgoingDoor(r1);
        r1.addItem(rongy);
        r2.addPerson(t);
        t.setCurrentRoom(r2);
    }

    StudentHas5ItemsPickedUpAndInARoomWithAnotherItem(){
        TVSzDenever tvsz= new TVSzDenever();
        Tranzisztor tran= new Tranzisztor();
        NedvesTablatorlo nedv= new NedvesTablatorlo();
        SzentSorosPoharak sorosp= new SzentSorosPoharak();
        Camembert cam= new Camembert();
        FFP2 mask= new FFP2();
        Student s= new Student();
        Room r= new Room();
        ArrayList<BaseItem> items= new ArrayList<>();
        items.add(tvsz);
        items.add(tran);
        items.add(nedv);
        items.add(sorosp);
        items.add(cam);
        r.addPerson(s);
        s.setCurrentRoom(r);
        r.addItem(mask);
        s.setItems(items);
        tvsz.setHolder(s);
        tran.setHolder(s);
        nedv.setHolder(s);
        sorosp.setHolder(s);
        cam.setHolder(s);
    }

    StudentHasMask,SzentSorospoharakAndNedvesTablatorlo(){
        SzentSorosPoharak pohar = new SzentSorosPoharak();
        FFP2 mask= new FFP2();
        NedvesTablatorlo tablatorlo= new NedvesTablatorlo();
        Student s= new Student();
        Room r= new Room();
        s.addItem(pohar);
        pohar.setHolder(s);
        s.addItem(mask);
        mask.setHolder(s);
        s.addItem(tablatorlo);
        tablatorlo.setHolder(s);
        r.addPerson(s);
        s.setCurrentRoom(r);
    }

    StudentHasATranzisztorAndInARoomWithAnotherTranzisztor() {
        Tranzisztor tr1 = new Tranzisztor();
        Student s = new Student();
        Tranzisztor tr2 =  new Tranzisztor();
        Room r = new Room();
        tr1.setHolder(s);
        s.setCurrentRoom(r);
        s.addItem(tr1);
        tr2.setRoom(r);
        r.addPerson(s);
        r.addItem(tr2);
    }

    TeacherEntersARoomWithAnActiveCamambertInItK() {
        Camembert cam = new Camembert();
        Room r1 = new Room();
        Room r2 = new Room();
        Teacher t = new Teacher();
        r1.addIncomingDoor(r2);
        r1.addItem(cam);
        r2.addOutgoingDoor(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);
        cam.effect();
    }

    TeacherInARoomAndThereIsEveryItemInItK() {
        Room r = new Room();
        Teacher t = new Teacher();
        FFP2 mask = new FFP2();
        Tranzisztor tran = new Tranzisztor();
        Camembert cam = new Camembert();
        Logarlec log = new Logarlec();
        TVSzDenever tvsz = new TVSzDenever();
        NedvesTablatorlo tablatorlo = new NedvesTablatorlo();
        SzentSorosPoharak pohar= new SzentSorosPoharak();
        ArrayList<BaseItem> items= new ArrayList<>();
        items.add(mask);
        items.add(tran);
        items.add(cam);
        items.add(log);
        items.add(tvsz);
        items.add(tablatorlo);
        items.add(pohar);
        r.addItems(items);
        r.addPerson(t);
        t.setCurrentRoom(r);
    }

    TeacherDropsAnItemFromItsHandK() {
        Teacher t = new Teacher();
        Room r1 = new Room();
        TVSzDenever tvsz = new TVSzDenever();

        r1.addPerson(t);
        t.setCurrentRoom(r1);
        t.addItem(tvsz);
        tvsz.setHolder(t);
    }*/

    //Rajmund ide baszsd a szekvenciadiagramokat}
    public void StudentEntersARoom(){ //ROHADT KÉSZ
        Student s = new Student();
        Room r1 = new Room();
        Room r2 = new Room();
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        //s.setCurrentRoom(r1);

        s.move(r2);
    }

    private void StudentEntersARoomWhatFilledWithFFP2(){ //ÉN IS ROHADT KÉSZ
        Student s = new Student();
        Room gasRoom = new Room();
        gasRoom.setGas(true);
        Room r = new Room();
        FFP2 mask = new FFP2();
        s.addItem(mask);
        mask.setHolder(s);
        gasRoom.addIncomingDoor(r);
        r.addOutgoingDoor(gasRoom);
        r.addPerson(s);
        //s.setCurrentRoom(r);

        s.move(gasRoom);
        gasRoom.addPerson(s);

    }

    public void StudentEntersARoomWithATeacherInItAndDoesntHaveProtection(){ //MEGVAN EZ IS
        Student s = new Student();
        Teacher t = new Teacher();
        Labyrinth l = new Labyrinth();
        Room r1 = new Room();
        Room r2 = new Room();
        l.addStudent(s);
        l.addTeacher(t);
        l.addRoom(r1);
        l.addRoom(r2);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        r2.addPerson(t);

        s.move(r2);
    }
    private void StudentEntersARoomWithATeacherInItAndHasTVSZDenever(){ //EZ IS MEGVAN
        Student s = new Student();
        Teacher t = new Teacher();
        Room r1 = new Room();
        Room r2 = new Room();
        TVSzDenever tvsz = new TVSzDenever();
        s.addItem(tvsz);
        tvsz.setHolder(s);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);

        s.move(r2);

    }

    private void StudentEntersARoomWithATeacherInItAndHaveActiveSzentSorosPoharak() { //EZ IS FASZA
        Student s = new Student();
        Teacher t = new Teacher();
        Room r1 = new Room();
        Room r2 = new Room();
        SzentSorosPoharak pohar = new SzentSorosPoharak();
        s.addItem(pohar);
        pohar.setHolder(s);
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);

        pohar.effect();
        s.move(r2);
    }
    private void StudentPicksUpLogarlec(){
        GameManager gm = new GameManager();
        Student s = new Student();
        Logarlec log = new Logarlec();
        Labyrinth l = new Labyrinth();
        Room r = new Room();
        l.addRoom(r);
        r.addPerson(s);

        s.pickUpItem(log);


    }

    private void StudentPicksUpCamembert(){
        Student s = new Student();
        Camembert item = new Camembert();
        Room r = new Room();

        r.addPerson(s);
        s.pickUpItem(item);
        item.setHolder(s);
        r.removeItem(item);
    }

    //MANAGER
    public void manager(){
        Scanner scanner = new Scanner(System.in);
        int testcase = scanner.nextInt();
        switch(testcase){
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("default");
        }
    }
}


