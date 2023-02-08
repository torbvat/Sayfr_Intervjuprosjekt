package com.sayfr;
import com.sayfr.intervjuprosjekt.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class RoomTest {
    private Room room1 = new Room(false, 18, false, true);
    private Room room2 = new Room(false, 20, false, false);
    private Room room3 = new Room(true, 18, false, true);

    @BeforeEach
    public void setup(){
    }

    @Test
    public void testGetAllRooms(){
        Assertions.assertEquals(100, room1.getRoomNumber());
        Assertions.assertEquals(18, room1.getOvenTemperature());
        Assertions.assertTrue(room1.hasWindow());
        Assertions.assertFalse(room1.isDropIn());
        Assertions.assertFalse(room1.isBooked());
        Assertions.assertEquals(101, room2.getRoomNumber());
        Assertions.assertEquals(22, room2.getTemperature(10));
        Assertions.assertTrue(room3.isDropIn());
    }
    

}
