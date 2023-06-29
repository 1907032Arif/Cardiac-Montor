package com.example.cardiac_monitor;

import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.example.cardiac_monitor.RecordList;



public class AddNewElementTest {

    @Test
    public void testAddData()
    {
        Record record1 = new Record("80", "120", "normal", "100", "normal", "29/06/23", "11 am", "Good");
        RecordList recordList = new RecordList();
        recordList.addRecords(record1);
        assertEquals(1, recordList.getRecords().size());

        Record record2 = new Record("85", "130", "normal", "100", "normal", "29/06/23", "12 am", "Good");
        recordList.addRecords(record2);
        assertEquals(2, recordList.getRecords().size());


        assertTrue(recordList.getRecords().contains(record1));
        assertTrue(recordList.getRecords().contains(record2));

    }
    /**
     * testing addData method for exceptions
     */

    @Test
    public void testAddRecordException()
    {

        Record record1 = new Record("80", "120", "normal", "100", "normal", "29/06/23", "11 am", "Good");
        RecordList recordList = new RecordList();
        recordList.addRecords(record1);

        assertThrows(IllegalArgumentException.class, () -> recordList.addRecords(record1));

    }

    @Test
    public  void testDeleteData()
    {
        Record record1 = new Record("80", "120", "normal", "100", "normal", "29/06/23", "11 am", "Good");
        RecordList recordList = new RecordList();
        recordList.addRecords(record1);


        assertEquals(1, recordList.getRecords().size());

        Record record2 = new Record("85", "130", "normal", "100", "normal", "29/06/23", "12 am", "Good");
        recordList.addRecords(record2);
        assertEquals(2, recordList.getRecords().size());
        assertTrue(recordList.getRecords().contains(record1));
        assertTrue(recordList.getRecords().contains(record2));

        recordList.delete(record1);
        assertEquals(1, recordList.getRecords().size());
        assertFalse(recordList.getRecords().contains(record1));

        recordList.delete(record2);
        assertEquals(0, recordList.getRecords().size());
        assertFalse(recordList.getRecords().contains(record2));

    }


    @Test
    public void testDeleteRecordException()
    {
        Record record1 = new Record("80", "120", "normal", "100", "normal", "29/06/23", "11 am", "Good");
        RecordList recordList = new RecordList();
        recordList.addRecords(record1);

        recordList.delete(record1);
        assertThrows(IllegalArgumentException.class, ()->recordList.delete(record1));

    }




}
