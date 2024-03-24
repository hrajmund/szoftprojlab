public class Logarlec extends BaseItem{
    @Override
    public void effect() {
    }

    @Override
    public void setHolder(Person holder) {
        if(holder.canPickUpLogarlec()){
            holder.labyrinth.endGame();
        }
        System.out.println(this.toString() + ":: setHolder() paramétere: " + holder.toString() + "-re");
    }
}
