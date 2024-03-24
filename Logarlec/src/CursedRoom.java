import java.util.List;
import java.util.Random;

public class CursedRoom extends Room implements IRound{
    List<Room> cursedNeighbours;
    public void ChangeDoors(){
        System.out.println(this.toString() + ":: ChangeDoors() függvénye");
    }
    public void tick(){
        System.out.println(this.toString() + ":: tick() függvénye");
    }
    public void cursed(){
        Random randomNum = new Random();
        int rand1 = randomNum.nextInt(incomingDoors.size());
        for (int i = 0; i < rand1; i++) {
            int rand2 = randomNum.nextInt(incomingDoors.size());
            removeIncomingDoor(incomingDoors.get(rand2));
        }
        int rand3 = randomNum.nextInt(outgoingDoors.size());
        for (int i = 0; i < rand3; i++) {
            int rand4 = randomNum.nextInt(outgoingDoors.size());
            removeIncomingDoor(incomingDoors.get(rand4));
        }
    }
}
