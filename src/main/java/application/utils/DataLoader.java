package application.utils;

import application.model.Event.Event;
import application.model.Event.Speaker;
import application.model.anyad.Book;
import application.model.anyad.BookCategory;
import application.repository.BookCategoryRepository;
import application.repository.EventRepository;
import application.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {


    private  EventRepository eventRepository;
    private SpeakerRepository speakerRepository;
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    public DataLoader(EventRepository eventRepository, SpeakerRepository speakerRepository, BookCategoryRepository bookCategoryRepository) {
        this.eventRepository = eventRepository;
        this.speakerRepository = speakerRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Override
    public void run(String... args) {

        Event event1 = new Event("Magyarország jövőjéről Budapesten", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", 47.486548199999994D, 19.094626899999998D, new Date(), "I need a simple ajax tutorial or case study for a simple input form, where I want to post a username through an input form, which sends it to the database and replies with the results.\n" +
                "Any recommendation for such tutorial is welcome, because I've only got one using Mootool but I'm searching for one using jQuery!", 400L, 40L, "backgrounds/bg-img-4", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get");
        event1.addSpeaker(new Speaker("John Wick", "valami@gmail.com" ,"http://facebook.com", "http://google.com" ,"The first argument of the error function is the XMLHttpRequest", "img.jpg", "Budapest, Magyarország", "1992.04.21"));
        eventRepository.save(event1);

        Event event2 = new Event("Magyarország jövőjéről DEBRECEN", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", 47.486548199999994D, 19.094626899999998D, new Date(), "I need a simple ajax tutorial or case study for a simple input form, where I want to post a username through an input form, which sends it to the database and replies with the results.\n" +
                "Any recommendation for such tutorial is welcome, because I've only got one using Mootool but I'm searching for one using jQuery!", 400L, 40L, "backgrounds/bg-img-4", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get");
        event2.addSpeaker(new Speaker("John Wick", "valami@gmail.com" ,"http://facebook.com", "http://google.com" ,"The first argument of the error function is the XMLHttpRequest", "img.jpg", "Budapest, Magyarország", "1992.04.21"));
        eventRepository.save(event2);

        Event event3 = new Event("Magyarország jövőjéről szoszsozszolnok", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", 47.486548199999994D, 19.094626899999998D, new Date(), "I need a simple ajax tutorial or case study for a simple input form, where I want to post a username through an input form, which sends it to the database and replies with the results.\n" +
                "Any recommendation for such tutorial is welcome, because I've only got one using Mootool but I'm searching for one using jQuery!", 400L, 40L, "backgrounds/bg-img-4", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get");
        event3.addSpeaker(new Speaker("John Wick", "valami@gmail.com" ,"http://facebook.com", "http://google.com" ,"The first argument of the error function is the XMLHttpRequest", "img.jpg", "Budapest, Magyarország", "1992.04.21"));
        eventRepository.save(event3);
        for (Event event : eventRepository.findAll()) {
            System.out.println(event.toString());
        }

    }


}
