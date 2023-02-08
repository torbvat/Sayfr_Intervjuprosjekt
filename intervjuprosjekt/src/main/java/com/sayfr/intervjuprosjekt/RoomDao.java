package com.sayfr.intervjuprosjekt;
import java.util.ArrayList;
import java.util.Comparator;

public class RoomDao {
    private ArrayList<Room> rooms;
    
    
    public RoomDao(){
        this.rooms = new ArrayList<Room>();
    }

    public ArrayList<Room> getAllRooms() {
        return this.rooms;
    }   

    public ArrayList<Room> getDropInRooms(){
        ArrayList<Room> dropInRooms = new ArrayList<Room>();
        this.getAllRooms().stream()
            .filter(room -> room.isDropIn())
            .forEach(room -> dropInRooms.add(room));
        return dropInRooms;
    }

    public ArrayList<Room> getBookableRooms(){
        ArrayList<Room> bookableRooms = new ArrayList<Room>();
        this.getAllRooms().stream()
            .filter(room -> !room.isDropIn())
            .filter(room -> !room.isBooked())
            .forEach(room -> bookableRooms.add(room));
        return bookableRooms;
    }

    public Room getWarmestRoom(int outDoorTemperature){
        Room warmestRoom = this.getAllRooms().stream()
            .max(Comparator.comparing(room -> room.getTemperature(outDoorTemperature)))
            .get(); 
        return warmestRoom;
    }
    
    public Room getWarmestAvailableRoom(int outDoorTemperature){
        Room warmestAvailableRoom = this.getBookableRooms().stream()
            .max(Comparator.comparing(room -> room.getTemperature(outDoorTemperature)))
            .get(); 
        return warmestAvailableRoom;
    }

    public Booking bookWarmestRoomInDays(int outDoorTemperature, int days){
        Room warmestAvailableRoom = this.getWarmestAvailableRoom(outDoorTemperature);
        Booking booking = new Booking(warmestAvailableRoom, days);
        warmestAvailableRoom.setBooked(true);
        return booking;
    }
}