import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labyrinth implements IRound{
    ArrayList<Room> rooms;
    ArrayList<Student> players;
    ArrayList<Teacher> teachers;
    TranzisztorHandler tranzisztorHandler;

    GameManager GM;

    public void removeRoom(Room r){
        System.out.println(this.toString() + ":: removeRoom() paramétere: " + r.toString() + "-re");
    }
    public void removeStudent(Student s) {
        TestPrinter.printCallingMethod(s);
        players.remove(s);
        if(players.isEmpty()){
            GM.gameEnd();
        }
    }

    public void personInTeacherList() {
        System.out.println(this.toString() + ":: personInTeacherList() függvénye");
    }

    //for checking Person type when Logarlec is picked up
    public void personInStudentList(Student s) {
        System.out.println(this.toString() + ":: personInStudentList() paramétere: " + s + "-re");
    }

    public void tick(){
        System.out.println(this.toString() + ":: tick() függvénye");
    }
    public void newRound(){
        if(players.isEmpty()){
            GM.gameEnd();
        }
        System.out.println(this.toString() + ":: newRound() függvénye");
    }

    public void addStudent(Student s){}
    public void addTeacher(Teacher t){}
    public void addRoom(Room r){}
    public void endGame(){
        GM.gameEnd();
    }

}
