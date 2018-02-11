package application.utils;

import application.model.Event.Event;
import application.model.Menu.Menu;
import application.model.Account.Account;
import application.model.Account.Role;
import application.repository.*;
import application.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private  EventRepository eventRepository;
    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserServiceImpl userService;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    @Qualifier("menuRepository")
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void run(String... args) {

        Role role = new Role("ADMIN");
        Role role2 = new Role("USER");
        Role role3 = new Role("OWNER");
        roleRepository.save(role);
        roleRepository.save(role2);
        roleRepository.save(role3);

        Account account = new Account();
        account.setActive(1);
        account.setPassword("anyad");
        account.setEmail("vigyor99@gmail.com");
        account.setName("Barnabás");
        account.setLastName("Tóth");
        account.setImgPath("/profile/image/" + account.getId());
        account.setProfession("Junior Java Developer");
        account.setDescription("I consider myself a calm, relaxed and driven person who is easy to get along with. I love building and designing systems, I enjoy being able to see through the depth of complexness, that is why I became a programmer.");
        account.setMemberSince(LocalDateTime.now());
        userService.saveOwner(account);

        Account account2 = new Account();
        account2.setActive(1);
        account2.setPassword("anyad");
        account2.setEmail("vigyor999@gmail.com");
        account2.setName("anyad");
        account2.setLastName("anyad");
        account2.setImgPath("/images/user.png");
        account2.setMemberSince(LocalDateTime.now());
        userService.saveUser(account2);

        Set<String> speakers = new HashSet<>();
        speakers.add("vigyor99@gmail.com");
        speakers.add("vigyor999@gmail.com");

        Event event1 = new Event("Magyarország jövőjéről Budapesten", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simple ajax tutorial or case study for a simple input form, where I want to post a username through an input form, which sends it to the database and replies with the results.\n" +
                "Any recool but I'm searching for one using jQuery!", "400L", "40L", "bg-img-4", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
        event1.setSpeakers(speakers);
        event1.setActive(1);
        event1.setAddress("DEBRECEN valami utca1");
        event1.setDate(LocalDateTime.now());
        event1.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        eventRepository.save(event1);

        Event event2 = new Event("Magyarország jövőjéről DEBRECEN", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a s with the results.\n" +
                "Any retool but I'm searching for one using jQuery!", "400L", "40L", "bg-img-4", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
        event2.setSpeakers(speakers);
        event2.setAddress("DEBRECEN valami utca1");
        event2.setActive(1);
        event2.setDate(LocalDateTime.now());
        event2.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        eventRepository.save(event2);

        Event event3 = new Event("Magyarország jövőjéről szoszsozszolnok", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simpleth the results.\n" +
                "Any recog Mootool but I'm searching for one using jQuery!", "400L", "40L", "bg-img-4", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
        event3.setSpeakers(speakers);
        event3.setDate(LocalDateTime.now());
        event3.setAddress("DEBRECEN valami utca1");
        event3.setActive(1);
        event3.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        eventRepository.save(event3);

        Menu about = new Menu();
        about.setTitle("Rólunk");
        about.setDescription("A Rólunk szól i történet már indulásakor hatalmas siker volt. A három Golden Globe-díjra jelölt sorozat az Egyesült Államokban ...");
        about.setRoute("about");
        menuRepository.save(about);

        Menu contact = new Menu();
        contact.setTitle("Kapcsolat");
        contact.setDescription("A Kapcsolat , mely elérhető a Telekom mobil alkalmazásban. Töltsd le a mobilodra Telekom alkalmazásunkat, amelyben elérheted virtuális Kapcsolat kártyádat is. A Telekom alkalmazásban lévő Kapcsolat kártyádat teljes értékűen ...");
        contact.setRoute("contact");
        menuRepository.save(contact);
//
    }


}
