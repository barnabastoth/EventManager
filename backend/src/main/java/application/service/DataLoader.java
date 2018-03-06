package application.service;


import application.model.authentication.User;
import application.model.event.Comment;
import application.model.event.Event;
import application.repository.CommentRepository;
import application.repository.EventRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired UserService userService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private EventRepository eventRepository;

    @Override
    public void run(String[] args) {
        User user = new User();
        user.setUsername("anyad");
        user.setPassword(bCryptPasswordEncoder.encode("anyad"));
        userService.save(user);

        Event event1 = new Event("Magyarország jövőjéről Budapesten", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simple ajax tutorial or case study for a simple input form, where I want to post a username through an input form, which sends it to the database and replies with the results.\n" +
                "Any recool but I'm searching for one using jQuery!", "400L", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
        event1.setActive(1);
        event1.setAddress("DEBRECEN valami utca1");
        event1.setDate(LocalDateTime.now());
        event1.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        eventRepository.save(event1);

        Event event2 = new Event("Magyarország jövőjéről DEBRECEN", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a s with the results.\n" +
                "Any retool but I'm searching for one using jQuery!", "400L", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
        event2.setAddress("DEBRECEN valami utca1");
        event2.setActive(1);
        event2.setDate(LocalDateTime.now());
        event2.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        eventRepository.save(event2);

        Event event3 = new Event("Magyarország jövőjéről szoszsozszolnok", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simpleth the results.\n" +
                "Any recog Mootool but I'm searching for one using jQuery!", "400L", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
        event3.setDate(LocalDateTime.now());
        event3.setAddress("DEBRECEN valami utca1");
        event3.setActive(1);
        event3.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        eventRepository.save(event3);
    }


}
