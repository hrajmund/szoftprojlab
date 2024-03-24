public class Camembert extends BaseItem{
    @Override
    public void effect() {

    }
    public void tick(){
        System.out.println(this.toString() + ":: tick() függvénye");
    }
}
