package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    @Test
    void testToString() {
        Time time1 = new Time(1,1);
        String timeString = time1.toString();
        String expectedTimeString = "01:01";
        assertEquals(expectedTimeString,timeString);
        time1.setHours(10);
        time1.setMinutes(10);
        timeString = time1.toString();
        expectedTimeString = "10:10";
        assertEquals(expectedTimeString,timeString);
    }

    @Test
    void compareTo() {
        Time time1 = new Time(10,10);
        Time time2 = new Time(11,11);
        assertEquals(1,time2.compareTo(time1));
        time2.setHours(9);
        time2.setMinutes(9);
        assertEquals(-1,time2.compareTo(time1));
        time2.setMinutes(10);
        time2.setHours(10);
        assertEquals(0,time2.compareTo(time1));
    }

    @Test
    void isGreaterThan() {
        Time time1 = new Time(10,10);
        Time time2 = new Time(11,11);
        assertTrue(time2.isGreaterThan(time1));
        time2.setHours(10);
        assertTrue(time2.isGreaterThan(time1));
        time2.setMinutes(1);
        assertFalse(time2.isGreaterThan(time1));
    }

    @Test
    void toMinutes() {
        Time time1 = new Time(10,10);
        int expectedMins = 610;
        int obtainedMins = time1.toMinutes();
        assertEquals(expectedMins,obtainedMins);
    }

    @Test
    void addMinutes() {
        Time time1 = new Time(10,10);
        time1 = time1.addMinutes(10);
        Time expectedTime1 = new Time(10,20);
        assertEquals(expectedTime1.getMinutes(),time1.getMinutes());
        assertEquals(expectedTime1.getHours(),time1.getHours());

        Time time2 = new Time(10,55);
        time2 = time2.addMinutes(10);
        Time expectedTime2 = new Time(11,5);
        assertEquals(expectedTime2.getMinutes(),time2.getMinutes());
        assertEquals(expectedTime2.getHours(),time2.getHours());

    }

    @Test
    void subtractMinutes() {
        Time time1 = new Time(10,10);
        time1 = time1.subtractMinutes(10);
        Time expectedTime1 = new Time(10,0);
        assertEquals(expectedTime1.getMinutes(),time1.getMinutes());
        assertEquals(expectedTime1.getHours(),time1.getHours());

        Time time2 = new Time(10,5);
        time2 = time2.subtractMinutes(10);
        Time expectedTime2 = new Time(9,55);
        assertEquals(expectedTime2.getMinutes(),time2.getMinutes());
        assertEquals(expectedTime2.getHours(),time2.getHours());
    }
}