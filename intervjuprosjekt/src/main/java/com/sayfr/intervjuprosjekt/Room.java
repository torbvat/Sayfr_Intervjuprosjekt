package com.sayfr.intervjuprosjekt;

import java.util.concurrent.atomic.AtomicInteger;

public class Room {
    
    private static RoomDao roomDao = new RoomDao();
    private static final AtomicInteger _ID = new AtomicInteger(1);
    private final int roomNumber;
    private boolean dropIn;
    private boolean hasWindow;
    private float ovenTemperature;
    private boolean booked;

    public Room(boolean dropIn, float ovenTemperature, boolean booked, boolean hasWindow) {
        this.roomNumber = _ID.getAndIncrement();
        this.dropIn = dropIn;
        this.ovenTemperature = ovenTemperature;
        this.booked = booked; //TODO: Ta hensyn til dato booket
        this.hasWindow = hasWindow;
        roomDao.getAllRooms().add(this);
    }

    public boolean isBooked(){
        return this.booked;
    }

    public void setBooked(boolean booked){
        this.booked = booked;
    }

    public int getRoomNumber(){
        return this.roomNumber;
    }

    public boolean isDropIn(){
        return this.dropIn;
    }
    
    public float getOvenTemperature(){
        return this.ovenTemperature;
    }

    public double getTemperature(float outDoorTemperature){
        if(this.hasWindow){
            return (this.ovenTemperature+outDoorTemperature)/2;
        } else {
            return (this.ovenTemperature+2)*outDoorTemperature*0.1;
        }
    }
    
    public boolean hasWindow(){
        return this.hasWindow;
    }

    @Override
    public String toString(){
        return "Room " + this.roomNumber;
    }

    public static void main(String[] args){
        //Manuell testing hovedsaklig for min egen del
        Room room1 = new Room(false, 18, false, true);
        Room room2 = new Room(true, 20, false, false);
        Room room3 = new Room(true, 25, false, true);
        Room room4 = new Room(false, 25, false, false);
        System.out.println(room1.getRoomNumber());
        System.out.println(room2.getRoomNumber());
        System.out.println(room3.getRoomNumber());
        System.out.println("Temperature on room 4: " + room4.getTemperature(10));
        System.out.println("Temperature on room 1: " + room1.getTemperature(10));
        System.out.println(room1.isDropIn());
        System.out.println(room1.isBooked());
        System.out.println("All rooms: " + roomDao.getAllRooms());
        System.out.println("Drop-in rooms: " + roomDao.getDropInRooms());
        System.out.println("Bookable rooms: " + roomDao.getBookableRooms());
        Room room5 = new Room(false, 50, false, false);
        System.out.println("Temperature room 5: " + room5.getTemperature(10));
        System.out.println("Warmest room: " + roomDao.getWarmestRoom(10));
        System.out.println(roomDao.bookWarmestRoomInDays(10, 3));
        
        System.out.println(roomDao.bookWarmestRoomInDays(10, 40));
        //System.out.println(roomDao.bookWarmestRoomInDays(10, -3));
    }
}
