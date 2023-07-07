package com.example.cardiac_monitor;

import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.example.cardiac_monitor.RecordList;



/**
 * This class contains unit tests for adding and deleting records in the RecordList.
 */
public class AddNewElementTest {

    /**
     * Test case to verify the addition of records to the RecordList.
     * It creates two records and adds them to the RecordList.
     * Then it checks if the RecordList contains the added records.
     */

    private RecordList mockRecordList()
    {
        RecordList recordList = new RecordList();
        recordList.addRecords(mockRecord());
        return recordList;
    }


    private Record mockRecord()
    {
        return new Record("120","80","normal","60","normal","Thursday,7 July 2022","05:01 pm","sitting");
    }

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
     * Test case to verify the exception thrown when adding an existing record to the RecordList.
     * It adds a record to the RecordList and attempts to add the same record again.
     * It expects an IllegalArgumentException to be thrown.
     */
    @Test
    public void testAddRecordException()
    {

        Record record1 = new Record("80", "120", "normal", "100", "normal", "29/06/23", "11 am", "Good");
        RecordList recordList = new RecordList();
        recordList.addRecords(record1);

        assertThrows(IllegalArgumentException.class, () -> recordList.addRecords(record1));

    }

    /**
     * Test case to verify the deletion of records from the RecordList.
     * It adds two records to the RecordList, deletes them one by one, and checks the size and content of the RecordList.
     */
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


    /**
     * Test case to verify the exception thrown when deleting a non-existing record from the RecordList.
     * It adds a record to the RecordList, deletes it, and attempts to delete it again.
     * It expects an IllegalArgumentException to be thrown.
     */
    @Test
    public void testDeleteRecordException()
    {
        Record record1 = new Record("80", "120", "normal", "100", "normal", "29/06/23", "11 am", "Good");
        RecordList recordList = new RecordList();
        recordList.addRecords(record1);

        recordList.delete(record1);
        assertThrows(IllegalArgumentException.class, ()->recordList.delete(record1));

    }


    @Test
    public void testGetRecords()
    {
        RecordList recordList = mockRecordList();

        assertEquals(0,mockRecord().compareTo(recordList.getRecords().get(0)));

        Record record1 = new Record("190","60","high","50","exceptional","Thursday,7 July 2022","05:49 pm","resting");
        recordList.addRecords(record1);
        Record record2 = new Record("90","60","high","50","exceptional","Thursday,7 July 2022","05:49 pm","resting");
        recordList.addRecords(record2);


        assertEquals(0,record1.compareTo(recordList.getRecords().get(1)));
        assertEquals(0,mockRecord().compareTo(recordList.getRecords().get(0)));
        assertEquals(0,record2.compareTo(recordList.getRecords().get(2)));

    }
    @Test
    public void testcount(){
        /*creating and initializing objects for citylist and city class*/
        RecordList recordList = new RecordList();
        Record record1 = new Record("190","60","high","50","exceptional","Thursday,7 July 2022","05:49 pm","resting");
        recordList.addRecords(record1);
        Record record2 = new Record("90","60","high","50","exceptional","Thursday,7 July 2022","05:49 pm","resting");
        recordList.addRecords(record2);
        assertEquals(2,recordList.count());
        recordList.delete(record1);
        assertEquals(1,recordList.count());
        recordList.delete(record2);
    }





}
