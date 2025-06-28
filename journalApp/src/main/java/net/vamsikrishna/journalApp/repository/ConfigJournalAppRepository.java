package net.vamsikrishna.journalApp.repository;

import net.vamsikrishna.journalApp.entity.ConfigJournalAPPEntity;
import net.vamsikrishna.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAPPEntity, ObjectId> {
}
