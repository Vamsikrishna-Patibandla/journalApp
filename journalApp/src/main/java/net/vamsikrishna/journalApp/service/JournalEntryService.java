package net.vamsikrishna.journalApp.service;

import net.vamsikrishna.journalApp.entity.JournalEntry;
import net.vamsikrishna.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id){
        return journalEntryRepository.findById(id).orElse(null);
    }

    public boolean deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
        return true;
    }
}
