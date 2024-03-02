public interface IPlayer {
    String name = null;
    BaseItem[] items = new BaseItem[5];
    int ICapacity = 0;
    public void Move();
    public void PickUpItem(Object o);
    public String getName();
    public void setName(String s);
    public BaseItem[] getItems();
    public void setItems(Object o);
    public int getICapacity();
}
