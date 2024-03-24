public class NedvesTablatorlo extends BaseItem implements IRound{
    int remainingRounds;

    @Override
    public void effect() {
    }

    public void tick(){
        System.out.println(this.toString() + ":: tick() függvénye");
    }

}
