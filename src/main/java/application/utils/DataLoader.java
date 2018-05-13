package application.utils;


import application.model.Constants;
import application.model.SiteSettings;
import application.model.authentication.Role;
import application.model.authentication.User;
import application.model.event.Comment;
import application.repository.*;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private EventRepository eventRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired SiteSettingsRepository siteSettingsRepository;

    @Override
    public void run(String[] args) {
//        Role userRole = new Role("Felhasználó");
//        Role adminRole = new Role("Admin");
//        Role ownerRole = new Role("Tulajdonos");
//        roleRepository.saveAndFlush(userRole);
//        roleRepository.saveAndFlush(adminRole);
//        roleRepository.saveAndFlush(ownerRole);
//
////        Event event1 = new Event("Magyarország jövője", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simple ajax tutorial or case study for a simple input form, where I want to post a username through an input form, which sends it to the database and replies with the results.\n" +
////                "Any recool but I'm searching for one using jQuery!", "I like Spring Data JPA. It helps simplify your codebase, and frees me up from writing JPAQL or SQL.", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
////        event1.setActive(true);
////        event1.setAddress("DEBRECEN valami utca1");
////        event1.setDate(LocalDateTime.of(2018, 4,20,18,20,30));
////        event1.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
////        eventRepository.save(event1);
////
////        Event event2 = new Event("Magyarország jövőjéről DEBRECEN", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a s with the results.\n" +
////                "Any retool but I'm searching for one using jQuery!", "I like Spring Data JPA. It helps simplify your codebase, and frees me up from writing JPAQL or SQL.", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
////        event2.setAddress("DEBRECEN valami utca1");
////        event2.setActive(true);
////        event2.setDate(LocalDateTime.now());
////        event2.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
////        eventRepository.save(event2);
////
////        Event event3 = new Event("Magyarország jövője", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simpleth the results.\n" +
////                "Any recog Mootool but I'm searching for one using jQuery!", "I like Spring Data JPA. It helps simplify your codebase, and frees me up from writing JPAQL or SQL.", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
////        event3.setDate(LocalDateTime.of(2012, 6, 20, 13, 20 ,36));
////        event3.setAddress("DEBRECEN valami utca1");
////        event3.setActive(true);
////        event3.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
////
////        eventRepository.save(event3);
////
////        Event event4 = new Event("Magyarország jövője", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simpleth the results.\n" +
////                "Any recog Mootool but I'm searching for one using jQuery!", "I like Spring Data JPA. It helps simplify your codebase, and frees me up from writing JPAQL or SQL.", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
////        event4.setDate(LocalDateTime.of(2012, 6, 20, 13, 20 ,36));
////        event4.setAddress("DEBRECEN valami utca1");
////        event4.setActive(true);
////        event4.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
////
////        eventRepository.save(event4);
////
////        Event event5 = new Event("Magyarország jövője", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simpleth the results.\n" +
////                "Any recog Mootool but I'm searching for one using jQuery!", "I like Spring Data JPA. It helps simplify your codebase, and frees me up from writing JPAQL or SQL.", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
////        event5.setDate(LocalDateTime.of(2012, 6, 20, 13, 20 ,36));
////        event5.setAddress("DEBRECEN valami utca1");
////        event5.setActive(true);
////        event5.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
////
////        eventRepository.save(event5);
////
////        Event event6 = new Event("Magyarország jövője", "Magyarország jövőjéről ülünk le beszélgetni x,y,zvel ügyesen", "gyere a 86os busszal aztán sétálj kurva sokat", "gyere a 6os uton, aztán majd találj ide", "1124 Váci út 4/2 28as kapucsefewfewfewfewfewfwengő", "47.486548199999994D", "19.094626899999998D", "I need a simpleth the results.\n" +
////                "Any recog Mootool but I'm searching for one using jQuery!", "I like Spring Data JPA. It helps simplify your codebase, and frees me up from writing JPAQL or SQL.", "40L", "https://stackoverflow.com/questions/9436534/ajax-tutorial-for-post-and-get", "asd");
////        event6.setDate(LocalDateTime.of(2012, 6, 20, 13, 20 ,36));
////        event6.setAddress("DEBRECEN valami utca1");
////        event6.setActive(true);
////        event6.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
////
////        eventRepository.save(event6);
//
//        User user = new User();
//        user.setActive(1);
//        user.setPassword(bCryptPasswordEncoder.encode("password"));
//        user.setEmail("vigyor99@gmail.com");
//        user.setUsername("Exworm");
//        user.setName("Barnabss");
//        user.setLastName("Toth");
//        user.setProfession("Junior Java Developer");
//        user.setDescription("I consider myself a calm, relaxed and driven person who is easy to get along with. I love building and designing systems, I enjoy being able to see through the depth of complexness, that is why I became a programmer.");
//        user.setMemberSince(LocalDateTime.now());
//        user.setWebsite("http://event-manager.com");
//        user.setRole(ownerRole);
//        userService.save(user);
//
//        User guest = new User();
//        guest.setUsername("Vendég");
//        guest.setEmail("vendeg@gmail.com");
//        userService.save(guest);
//
////
////        Comment comment = new Comment();
////        comment.setDate(LocalDateTime.now());
////        comment.setEvent(event2);
////        comment.setMessage("Ez egy komment");
////        comment.setSender(user);
////        commentRepository.saveAndFlush(comment);
//
//        Set<Comment> comments = new HashSet<>();
////        comments.add(comment);
//
////        event1.setComments(comments);
////        eventRepository.saveAndFlush(event1);
//
//        SiteSettings siteSettings = new SiteSettings();
////        siteSettings.setWelcomeMessage("Ebben az életben sokféle tapasztalást kellett eddig megtanulnom (vagyis közel 33 év alatt). Sok tanulással és sok felemelkedéssel járó idő volt az eltelt jó pár év. Egyfajta gondolkodásmódbeli felemelkedés, a világ és a nőiség más szemmel való megtapasztalása. A jóga megtanított arra (és a buddhista…");
//        siteSettings.setLeftBarOpen(true);
//        siteSettings.setRightBarOpen(true);
//        siteSettings.setEventBarOpen(true);
//        siteSettings.setServerEmail(Constants.SERVER_EMAIL);
//        siteSettings.setCompanyPhone("0670555555");
//        siteSettings.setContactPageText("Ez a Ebben az életben sokféle tapasztalást kellett eddig megtanulnom (vagyis közel 33 év alatt). Sok tanulással és sok felemelkedéssel járó i");
//        siteSettingsRepository.saveAndFlush(siteSettings);
    }


}
