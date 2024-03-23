public enum RoomState {
    cursed, full, open;
    RoomState(){}
    public RoomState[] valuesOfRooms(){
        return new RoomState[2];
    }
    public RoomState valueOfARoom(Room r){
        return this;
    }

}
