public class NedvesTablatorlo extends BaseItem implements IRound{
    int remainingRounds;

    @Override
    public void effect() {
        holder.putDownItem(this);
        active = true;
    }

    public void tick(){
        TestPrinter.printCallingMethod();
        if(getRemainingRounds()>0){
            if(getRemainingRounds()==1){
                setRemainingRounds(getRemainingRounds()-1);
                holder.removeItem(this);
                holder=null;
            }else{
                setRemainingRounds(getRemainingRounds()-1);
            }
        }
    }

}
