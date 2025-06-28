package com.library.repository;

import com.library.model.BorrowingRecord;
import com.library.util.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class BorrowingRecordRepository extends GenericRepository<BorrowingRecord> {
    public BorrowingRecordRepository() {
        super("records.dat");
    }

    public BorrowingRecord findById(String recordId) {
        return read(record -> record.getRecordId().equals(recordId));
    }

    public void updateBorrowingRecord(BorrowingRecord record) {
        update(record, r -> r.getRecordId().equals(record.getRecordId()));
    }

    public void deleteBorrowingRecord(String recordId) {
        delete(record -> record.getRecordId().equals(recordId));
    }
}