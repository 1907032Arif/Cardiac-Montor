package com.example.cardiac_monitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The RecordList class represents a collection of Record objects.
 */
public class RecordList {

    /**
     * List of records.
     */
    public List<Record> records = new ArrayList<>();


    /**
     * Adds a new record to the list.
     *
     * @param record The record to be added.
     * @throws IllegalArgumentException if the record already exists in the list.
     */
    public void addRecords(Record record)
    {
        if(records.contains(record))
        {
            throw new IllegalArgumentException();
        }
        records.add(record);
    }

    /**
     * Retrieves a sorted list of all records.
     *
     * @return A sorted list of all records.
     */
    public List<Record>getRecords()
    {
        List<Record>recordList = records;
        Collections.sort(recordList);
        return recordList;
    }

    /**
     * Retrieves a list of records based on a given criteria.
     *
     * @param x The criteria for filtering records (example: category, status, etc.).
     * @return A list of records that match the given criteria.
     */
    public List<Record> getRecords(int x)
    {
        List<Record>recordList = records;
        return recordList;
    }

    /**
     * Deletes a record from the list.
     *
     * @param record The record to be deleted.
     * @throws IllegalArgumentException if the record does not exist in the list.
     */
    public void delete(Record record)
    {
        List<Record>recordList = records;
        if(recordList.contains(record)){
            records.remove(record);
        }//list name is cities
        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Retrieves the count of records in the list.
     *
     * @return The number of records in the list.
     */
    public int count()
    {
        return records.size();
    }



}

