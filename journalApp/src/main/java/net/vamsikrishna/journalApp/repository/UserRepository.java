package net.vamsikrishna.journalApp.repository;

import net.vamsikrishna.journalApp.entity.JournalEntry;
import net.vamsikrishna.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    public void deleteByUserName(String name);

    User findByUserName(String userName);
}
