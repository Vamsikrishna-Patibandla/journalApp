package net.vamsikrishna.journalApp.controller;

import net.vamsikrishna.journalApp.entity.JournalEntry;
import net.vamsikrishna.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll(){
        List<JournalEntry> all = journalEntryService.getAll();
        if(!all.isEmpty() && all != null){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry){
        try{
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(entry, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryId(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = Optional.ofNullable(journalEntryService.findById(myId));
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findById(id);
        if(oldEntry != null){
            oldEntry.setTitle( newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent( newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry );
            return new ResponseEntity<>(oldEntry, HttpStatus.CREATED);
        }
        journalEntryService.saveEntry(oldEntry );
        return new ResponseEntity<>(oldEntry, HttpStatus.BAD_REQUEST);

    }
}
