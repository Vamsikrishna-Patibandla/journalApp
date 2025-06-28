package net.vamsikrishna.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection =  "config_journal_app")
@Data
public class ConfigJournalAPPEntity {

    private String key;
    private String value;


}
