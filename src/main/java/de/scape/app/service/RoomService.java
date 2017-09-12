package de.scape.app.service;

import de.scape.app.entities.Room;
import java.util.List;

public interface RoomService {

     Room getRoom(Long id);

    void addNewRoom(Room room);

    List<Room> getRooms();

    Room findByName(String name);
	
    public boolean isRoomExist(Room room);
    
    public List<Room> addAllRooms();
    
    void update(Room room);

}
