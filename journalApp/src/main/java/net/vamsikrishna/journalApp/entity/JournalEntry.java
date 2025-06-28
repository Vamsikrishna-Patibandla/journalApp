package net.vamsikrishna.journalApp.entity;

import lombok.Data;
import net.vamsikrishna.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection =  "journal_entries")
@Data
public class JournalEntry{
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;




}
