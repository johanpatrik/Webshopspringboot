package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.Record;
import com.g09.webshopspringboot.repository.RecordRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordService {

    RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getRecords(){
        return recordRepository.findAll();
    }

    public Record findById(Long id){
        return recordRepository.findById(id).orElse(null);
    }

    public List<Record> getFilteredRecords(String searchWord) {
        return recordRepository.findAll().stream()
                .filter(record -> record.getArtist().contains(searchWord) || record.getTitle().contains(searchWord))
                .collect(Collectors.toList());
    }
}
