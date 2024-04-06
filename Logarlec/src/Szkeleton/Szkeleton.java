package Szkeleton;
import Modell.*;
import Modell.CursedRoom;
import Modell.GameManager;
import Modell.TVSzDenever;

import java.lang.reflect.Method;
import java.util.*;

public class Szkeleton {
    private void StudentEntersARoom(){
        Student s = new Student();
        Room r1 = new Room();
        Room r2 = new Room();
        r2.addIncomingDoor(r1);
        r1.addOutgoingDoor(r2);
        r1.addPerson(s);
        s.setCurrentRoom(r1);

        s.move(r2);
    }

    private void StudentEntersARoomWhatFilledWithGas(){
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
        s.setCurrentRoom(r);

        s.move(gasRoom);

    }

    private void StudentEntersARoomWithATeacherInItAndDoesntHaveProtection(){
        Student s = new Student();
        Teacher t = new Teacher();
        Labyrinth l = new Labyrinth();
        GameManager GM= new GameManager();
        l.setGameManager(GM);
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

        s.move(r2);
    }
    private void StudentEntersARoomWithATeacherInItAndHasTVSZDenever(){
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

    private void StudentEntersARoomWithATeacherInItAndHaveActiveSzentSorosPoharak() {
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

        s.UseItem(0);
        s.move(r2);
    }
    private void StudentPicksUpLogarlec(){
        GameManager gm = new GameManager();
        Student s = new Student();
        Logarlec log = new Logarlec();
        Labyrinth l = new Labyrinth();
        Room r = new Room();
        s.setLabyrinth(l);
        l.setGameManager(gm);
        l.addRoom(r);
        r.addPerson(s);
        s.setCurrentRoom(r);
        r.addItem(log);
        log.setRoom(r);

        s.pickUpItem(log);


    }

    private void StudentPicksUpCamembert(){
        Student s = new Student();
        Camembert item = new Camembert();
        Room r = new Room();
        r.addItem(item);
        item.setRoom(r);
        r.addPerson(s);
        s.setCurrentRoom(r);

        s.pickUpItem(item);
    }

    private void StudentPicksUpSzentSorospoharak(){
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

        s.pickUpItem(pohar);
    }

    private void StudentPicksUpTVSZDenever(){
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

        s.pickUpItem(tvsz);
    }

    private void StudentPicksUpFFP2(){
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

        s.pickUpItem(mask);
    }

    private void StudentPicksUpNedvesTablatorlo(){
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

        s.pickUpItem(tablatorlo);
    }

    private void StudentPicksUpTranzisztor(){
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

        s.pickUpItem(tran);
    }

    private void StudentDropsAnItemFromItsHand(){
        Student s= new Student();
        Room r1= new Room();
        TVSzDenever tvsz= new TVSzDenever();
        s.addItem(tvsz);
        tvsz.setHolder(s);
        r1.addPerson(s);
        s.setCurrentRoom(r1);

        s.putDownItem(tvsz);
    }

    private void TwoRoomsMerge(){
        Labyrinth l = new Labyrinth();
        Room r1= new Room();
        Room r2= new Room();
        l.addRoom(r1);
        l.addRoom(r2);
        r1.setLabyrinth(l);
        r2.setLabyrinth(l);

        r1.merge(r2);
    }

    private void RoomSplit(){
        Labyrinth l = new Labyrinth();
        Room r = new Room();
        l.addRoom(r);
        r.setLabyrinth(l);
        r.split();
    }

    private void CursedRoomDisappearDoors(){
        Room r1=new Room();
        Room r2=new Room();
        CursedRoom cursed= new CursedRoom();
        r1.addOutgoingDoor(cursed);
        cursed.addIncomingDoor(r1);
        r2.addIncomingDoor(cursed);
        cursed.addOutgoingDoor(r2);

        cursed.cursed();
    }

    private void CursedRoomAddDoors(){
        Room r1=new Room();
        Room r2=new Room();
        CursedRoom cursed= new CursedRoom();
        r1.addOutgoingDoor(cursed);
        cursed.addIncomingDoor(r1);
        r2.addIncomingDoor(cursed);
        cursed.addOutgoingDoor(r2);

        cursed.cursed();
    }

    private void TeacherEntersARoom(){
        Teacher t= new Teacher();
        Room r1= new Room();
        Room r2= new Room();
        r1.addOutgoingDoor(r2);
        r2.addIncomingDoor(r1);
        r1.addPerson(t);
        t.setCurrentRoom(r1);

        t.move(r2);
    }

    private void TeacherEntersARoomWithAStudentInItButStudentHasTVSZDenever(){
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

        t.move(r1);
    }

    private void TeacherEntersARoomWithAStudentInItButStudentHasActiveSzentSorosPoharak(){
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
        t.move(r1);
    }
    private void TeacherEntersARoomWithAStudentInItAndStudentDoesntHaveAnyProtection(){
        Student s = new Student();
        Teacher t = new Teacher();
        Labyrinth l = new Labyrinth();
        GameManager gm = new GameManager();
        Room r1 = new Room();
        Room r2 = new Room();
        l.setGameManager(gm);
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

        t.move(r1);
    }

    private void TeacherEntersARoomAndThereIsActiveNedvesTablatorlo() {
        NedvesTablatorlo rongy = new NedvesTablatorlo();
        GameManager gm= new GameManager();
        Room r1 = new Room();
        Room r2 = new Room();
        Teacher t = new Teacher();
        Student s = new Student();
        Labyrinth l= new Labyrinth();
        l.setGameManager(gm);
        l.addRoom(r1);
        l.addRoom(r2);
        l.addStudent(s);
        l.addTeacher(t);
        s.setLabyrinth(l);
        t.setLabyrinth(l);
        r1.addIncomingDoor(r2);
        r2.addOutgoingDoor(r1);
        r1.addItem(rongy);
        rongy.setRoom(r1);
        r1.addPerson(s);
        r2.addPerson(t);
        s.setCurrentRoom(r1);
        s.pickUpItem(rongy);
        s.UseItem(0);

        t.setCurrentRoom(r2);

        t.move(r1);
    }

    private void StudentActivateCamambert(){
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

        cam.effect();
    }

    private void StudentTriesToPickUpAnItemWithFullHand(){
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

        s.pickUpItem(mask);

    }

    private void StudentActivateFFP2Mask(){
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

        mask.effect();
    }

    private void StudentActivateSzentSorospoharak(){
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

        pohar.effect();
    }

    private void StudentActivateNedvesTablatorlo(){
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

        tablatorlo.effect();
    }

    private void StudentHasTranzisztorAndPicksUpAnother(){
        Tranzisztor tr1 = new Tranzisztor();
        Tranzisztor tr2 =  new Tranzisztor();
        Student s = new Student();
        Room r = new Room();
        tr1.setHolder(s);
        s.addItem(tr1);
        s.setCurrentRoom(r);
        r.addPerson(s);
        tr2.setRoom(r);
        r.addItem(tr2);

        s.pickUpItem(tr2);
    }

    private void StudentPutsDownFirstActiveTranzisztor(){
        Tranzisztor tr1 = new Tranzisztor();
        Tranzisztor tr2 =  new Tranzisztor();
        Student s = new Student();
        Room r = new Room();
        tr1.setHolder(s);
        s.addItem(tr1);
        s.setCurrentRoom(r);
        r.addPerson(s);
        tr2.setRoom(r);
        r.addItem(tr2);
        s.pickUpItem(tr2);

        tr1.effect();
    }

    private void StudentPutsDownSecondActiveTranzisztor(){
        Tranzisztor tr1 = new Tranzisztor();
        Tranzisztor tr2 =  new Tranzisztor();
        Student s = new Student();
        Room r1 = new Room();
        Room r2= new Room();
        r1.addIncomingDoor(r2);
        r2.addOutgoingDoor(r1);
        tr1.setHolder(s);
        s.addItem(tr1);
        s.setCurrentRoom(r1);
        r1.addPerson(s);
        tr2.setRoom(r1);
        r1.addItem(tr2);
        s.pickUpItem(tr2);
        tr2.setHolder(s);
        tr1.effect();

        tr2.effect();
    }

    private void StudentTriesToPickUpActiveTranzisztor(){
        Tranzisztor tr1 = new Tranzisztor();
        Student s= new Student();
        Room r = new Room();
        r.addPerson(s);
        s.setCurrentRoom(r);
        r.addItem(tr1);
        tr1.setRoom(r);
        tr1.setActive(true);

        s.pickUpItem(tr1);
    }

    private void StudentEntersARomWithATeacherInItHasALastOneTVSZDenever(){
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

    private void SzentSorospharTimeIsUp(){
        SzentSorosPoharak pohar = new SzentSorosPoharak();
        Student s= new Student();
        Room r= new Room();
        s.addItem(pohar);
        pohar.setHolder(s);
        r.addPerson(s);
        s.setCurrentRoom(r);

        pohar.effect();
    }

    private void NedvesTablatorloTimeIsUp(){
        NedvesTablatorlo tablatorlo= new NedvesTablatorlo();
        Student s= new Student();
        Room r= new Room();
        s.addItem(tablatorlo);
        tablatorlo.setHolder(s);
        r.addPerson(s);
        s.setCurrentRoom(r);

        tablatorlo.effect();
    }

    private void TeacherPicksUpAnItem(){
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

        t.pickUpItem(tvsz);
    }

    private void TeacherPutsDownAnItemFromItsHand(){
        Teacher t = new Teacher();
        Room r1 = new Room();
        TVSzDenever tvsz = new TVSzDenever();
        r1.addPerson(t);
        t.setCurrentRoom(r1);
        t.addItem(tvsz);
        tvsz.setHolder(t);

        t.putDownItem(tvsz);
    }

    private void StudentUseNewSzentSorospoharak(){
        Room r1 = new Room();
        Room r2 = new Room();
        Student s = new Student();
        Teacher t= new Teacher();
        FFP2 mask = new FFP2();
        Tranzisztor tran = new Tranzisztor();
        Camembert cam = new Camembert();
        Logarlec log = new Logarlec();
        TVSzDenever tvsz = new TVSzDenever();
        NedvesTablatorlo tablatorlo = new NedvesTablatorlo();
        SzentSorosPoharak pohar= new SzentSorosPoharak();
        ArrayList<BaseItem> items= new ArrayList<>();
        r1.addOutgoingDoor(r2);
        r2.addIncomingDoor(r1);
        items.add(mask);
        items.add(tran);
        items.add(cam);
        items.add(log);
        items.add(tvsz);
        items.add(tablatorlo);
        items.add(pohar);
        r1.addItems(items);
        r1.addPerson(s);
        s.setCurrentRoom(r1);
        r2.addPerson(t);
        t.setCurrentRoom(r2);
        s.pickUpItem(mask);
        s.pickUpItem(pohar);
        s.UseItem(1);

        s.move(r2);
    }

    public static void main(String[] args) {
        Szkeleton szkeleton = new Szkeleton();
        List<Runnable> functions = new ArrayList<Runnable>();
        List<String> tesztek= new ArrayList<>();
        functions.add(szkeleton::StudentEntersARoom); tesztek.add("StudentEntersARoom");
        functions.add(szkeleton::StudentEntersARoomWhatFilledWithGas); tesztek.add("StudentEntersARoomWhatFilledWithGas");
        functions.add(szkeleton::StudentEntersARoomWithATeacherInItAndDoesntHaveProtection); tesztek.add("StudentEntersARoomWithATeacherInItAndDoesntHaveProtection");
        functions.add(szkeleton::StudentEntersARoomWithATeacherInItAndHasTVSZDenever); tesztek.add("StudentEntersARoomWithATeacherInItAndHasTVSZDenever");
        functions.add(szkeleton::StudentEntersARoomWithATeacherInItAndHaveActiveSzentSorosPoharak); tesztek.add("StudentEntersARoomWithATeacherInItAndHaveActiveSzentSorosPoharak");
        functions.add(szkeleton::StudentPicksUpLogarlec); tesztek.add("StudentPicksUpLogarlec");
        functions.add(szkeleton::StudentPicksUpCamembert); tesztek.add("StudentPicksUpCamembert");
        functions.add(szkeleton::StudentPicksUpSzentSorospoharak); tesztek.add("StudentPicksUpSzentSorospoharak");
        functions.add(szkeleton::StudentPicksUpTVSZDenever); tesztek.add("StudentPicksUpTVSZDenever");
        functions.add(szkeleton::StudentPicksUpFFP2); tesztek.add("StudentPicksUpFFP2");
        functions.add(szkeleton::StudentPicksUpNedvesTablatorlo); tesztek.add("StudentPicksUpNedvesTablatorlo");
        functions.add(szkeleton::StudentPicksUpTranzisztor); tesztek.add("StudentPicksUpTranzisztor");
        functions.add(szkeleton::StudentDropsAnItemFromItsHand); tesztek.add("StudentDropsAnItemFromItsHand");
        functions.add(szkeleton::TwoRoomsMerge); tesztek.add("TwoRoomsMerge");
        functions.add(szkeleton::RoomSplit); tesztek.add("RoomSplit");
        functions.add(szkeleton::CursedRoomDisappearDoors); tesztek.add("CursedRoomDisappearDoors");
        functions.add(szkeleton::CursedRoomAddDoors); tesztek.add("CursedRoomAddDoors");
        functions.add(szkeleton::TeacherEntersARoom); tesztek.add("TeacherEntersARoom");
        functions.add(szkeleton::TeacherEntersARoomWithAStudentInItButStudentHasTVSZDenever); tesztek.add("TeacherEntersARoomWithAStudentInItButStudentHasTVSZDenever");
        functions.add(szkeleton::TeacherEntersARoomWithAStudentInItButStudentHasActiveSzentSorosPoharak); tesztek.add("TeacherEntersARoomWithAStudentInItButStudentHasActiveSzentSorosPoharak");
        functions.add(szkeleton::TeacherEntersARoomWithAStudentInItAndStudentDoesntHaveAnyProtection); tesztek.add("TeacherEntersARoomWithAStudentInItAndStudentDoesntHaveAnyProtection");
        functions.add(szkeleton::TeacherEntersARoomAndThereIsActiveNedvesTablatorlo); tesztek.add("TeacherEntersARoomAndThereIsActiveNedvesTablatorlo");
        functions.add(szkeleton::StudentActivateCamambert); tesztek.add("StudentActivateCamambert");
        functions.add(szkeleton::StudentTriesToPickUpAnItemWithFullHand); tesztek.add("StudentTriesToPickUpAnItemWithFullHand");
        functions.add(szkeleton::StudentActivateFFP2Mask); tesztek.add("StudentActivateFFP2Mask");
        functions.add(szkeleton::StudentActivateSzentSorospoharak); tesztek.add("StudentActivateSzentSorospoharak");
        functions.add(szkeleton::StudentActivateNedvesTablatorlo); tesztek.add("StudentActivateNedvesTablatorlo");
        functions.add(szkeleton::StudentHasTranzisztorAndPicksUpAnother); tesztek.add("StudentHasTranzisztorAndPicksUpAnother");
        functions.add(szkeleton::StudentPutsDownFirstActiveTranzisztor); tesztek.add("StudentPutsDownFirstActiveTranzisztor");
        functions.add(szkeleton::StudentPutsDownSecondActiveTranzisztor); tesztek.add("StudentPutsDownSecondActiveTranzisztor");
        functions.add(szkeleton::StudentTriesToPickUpActiveTranzisztor); tesztek.add("StudentTriesToPickUpActiveTranzisztor");
        functions.add(szkeleton::StudentEntersARomWithATeacherInItHasALastOneTVSZDenever); tesztek.add("StudentEntersARomWithATeacherInItHasALastOneTVSZDenever");
        functions.add(szkeleton::SzentSorospharTimeIsUp); tesztek.add("SzentSorospharTimeIsUp");
        functions.add(szkeleton::NedvesTablatorloTimeIsUp); tesztek.add("NedvesTablatorloTimeIsUp");
        functions.add(szkeleton::TeacherPicksUpAnItem); tesztek.add("TeacherPicksUpAnItem");
        functions.add(szkeleton::TeacherPutsDownAnItemFromItsHand); tesztek.add("TeacherPutsDownAnItemFromItsHand");
        functions.add(szkeleton::StudentUseNewSzentSorospoharak); tesztek.add("StudentUseNewSzentSorospoharak");

        for (int i = 0; i < tesztek.size(); i++) {
            System.out.println((i+1)+ ". "+ tesztek.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print("\nFuttatni kivant teszt szama: ");
            choice = scanner.nextInt();
            if (choice > 0 && choice <= functions.size()) {
                System.out.println("\n"+(choice)+ ". "+ tesztek.get(choice-1));
                functions.get(choice-1).run();
            } else if (choice != 0) {
                System.out.println("Hibas.");
            }
        } while (choice != 0);
    }
}