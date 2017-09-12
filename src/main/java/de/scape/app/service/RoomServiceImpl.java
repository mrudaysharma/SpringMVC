package de.scape.app.service;

import de.scape.app.dao.BaseDao;
import de.scape.app.entities.Room;
import static de.scape.app.service.RoomServiceImpl.logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 *
 */
@Service("roomService")
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService {

    private static final AtomicLong counter = new AtomicLong();

    final static Logger logger = Logger.getLogger(RoomServiceImpl.class);

    @Autowired
    BaseDao roomDao;

    @Override
    public Room getRoom(Long id) {
        logger.debug("Getting room with id " + id);
        System.out.println("Room Object " + roomDao.findById(id, Room.class));
        return roomDao.findById(id, Room.class);
    }

    @Override
    public List<Room> getRooms() {
        logger.debug("Getting list of rooms");
        System.out.println("Rooms Object " + roomDao.findAllEntities(Room.class.getSimpleName()));
        return (List<Room>) roomDao.findAllEntities(Room.class.getSimpleName());
    }

    /**
     * Add new roomList
     *
     * @param room: Room to add
     */
    @Override
    @Transactional(readOnly = false)
    public void addNewRoom(Room room) {
        roomDao.save(room);
        logger.debug("Id of new Room " + room);
    }
    
    @Transactional(readOnly = false)
    @Override
    public List<Room> addAllRooms() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(counter.incrementAndGet(), "1.101"));
        roomList.add(new Room(counter.incrementAndGet(), "2.201"));
        roomList.add(new Room(counter.incrementAndGet(), "3.301"));
        roomList.add(new Room(counter.incrementAndGet(), "4.401"));
        return roomList;
    }

    @Override
    public boolean isRoomExist(Room room) {
        return roomDao.findByName(room.getClass().getSimpleName(), room.getName());
    }

    @Override
    public Room findByName(String name) {
        logger.debug("Getting Room by name " + name);
        
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(counter.incrementAndGet(), "1.101"));
        roomList.add(new Room(counter.incrementAndGet(), "2.201"));
        roomList.add(new Room(counter.incrementAndGet(), "3.301"));
        roomList.add(new Room(counter.incrementAndGet(), "4.401"));
        for (Room room : roomList) {
            if(room.getName().equals(name))
            {
                return room;
            }
        }
        return new Room();
    }

    @Override
    public void update(Room room) {
       roomDao.update(room);
    }


}
