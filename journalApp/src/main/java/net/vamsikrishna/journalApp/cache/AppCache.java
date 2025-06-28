package net.vamsikrishna.journalApp.cache;

import lombok.extern.slf4j.Slf4j;
import net.vamsikrishna.journalApp.entity.ConfigJournalAPPEntity;
import net.vamsikrishna.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {



    public enum keys{
        WEATHER_API
    }

    public Map<String, String> APP_CACHE;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAPPEntity> all = configJournalAppRepository.findAll();
        log.info(" App Cache refreshed" + LocalDateTime.now());
        for(ConfigJournalAPPEntity configJournalAPPEntity:all){
            APP_CACHE.put(configJournalAPPEntity.getKey(), configJournalAPPEntity.getValue());
        }

    }
}
