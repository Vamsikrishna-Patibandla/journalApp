package net.vamsikrishna.journalApp.scheduler;


import net.vamsikrishna.journalApp.cache.AppCache;
import net.vamsikrishna.journalApp.entity.JournalEntry;
import net.vamsikrishna.journalApp.entity.User;
import net.vamsikrishna.journalApp.enums.Sentiment;
import net.vamsikrishna.journalApp.repository.UserRepositoryImpl;
import net.vamsikrishna.journalApp.service.EmailService;
import net.vamsikrishna.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;


    //@Scheduled(cron = "0 0 9 * * SUN")
    public  void fetchUsersAndSendMail(){
        List<User> users = userRepositoryImpl.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());


            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment != null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0)+1);
                }
            }

            Sentiment mostFrequentSentiment = Sentiment.HAPPY;
            int maxCount = 0;
            for( Map.Entry<Sentiment, Integer> entry: sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if(mostFrequentSentiment != null){
                emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
            }





        }
    }

    @Scheduled(cron = "0 */2 * ? * *")
    public void clearAppCace(){
        appCache.init();
    }
}
