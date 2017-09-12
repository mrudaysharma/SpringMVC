/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.controller;

import de.scape.app.entities.Room;
import de.scape.app.service.RoomService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author uysharma
 */
@RestController
public class RoomRestController {
    final static Logger logger = Logger.getLogger(RoomRestController.class);

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/room/", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> listAllRooms() {
        List<Room> rooms = roomService.addAllRooms();
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("id") long id) {
        System.out.println("Fetching Room with id " + id);
        Room room = roomService.getRoom(id);
        if (room == null) {
            System.out.println("Room with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
 
    
    @RequestMapping(value = "/room/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createRoom(@RequestBody Room room,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Room " + room.getName());
        roomService.addNewRoom(room);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/room/{id}").buildAndExpand(room.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
