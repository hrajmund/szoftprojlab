public class SzentSorosPoharak extends BaseItem{
    int timeUsage;
    @Override
    public  void effect() {
        holder.setTeacherProtection(true);
    }
    public void tick(){}
    public Boolean death_protection(){
        return false;
    }
}
