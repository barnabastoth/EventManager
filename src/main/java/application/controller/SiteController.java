package application.controller;

import application.model.SiteSettings;
import application.model.authentication.User;
import application.model.system.ContactMessage;
import application.model.system.NewContactMessage;
import application.repository.ContactMessageRepository;
import application.repository.SiteSettingsRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SiteController {

    @Autowired SiteSettingsRepository siteSettingsRepository;
    @Autowired ContactMessageRepository contactMessageRepository;
    @Autowired UserRepository userRepository;

    @GetMapping("/siteSettings")
    public SiteSettings serveSiteSettings() {
        for (SiteSettings siteSettings: siteSettingsRepository.findAll()) {
            if(siteSettings != null) {
                return siteSettings;
            }
        }
        return null;
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @PostMapping("/siteSettings/edit")
    public void editSiteSettings(@RequestBody SiteSettings newSettings) {
        SiteSettings oldSettings = siteSettingsRepository.getOne(newSettings.getId());
        oldSettings.setLeftBarOpen(newSettings.getLeftBarOpen());
        oldSettings.setRightBarOpen(newSettings.getRightBarOpen());
        oldSettings.setContactPageText(newSettings.getContactPageText());
        siteSettingsRepository.saveAndFlush(oldSettings);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/contactMessage/{id}")
    public ContactMessage serveContactMessageById(@PathVariable Long id) {
        Optional<ContactMessage> contactMessage = contactMessageRepository.findById(id);
        return contactMessage.orElse(null);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/contactMessage")
    public List<ContactMessage> serveAllContactMessage() {
        return contactMessageRepository.findAllByDateDesc();
    }

    @PostMapping("/contactMessage/new")
    public ResponseEntity<?> saveNewMessage(@RequestBody NewContactMessage newContactMessage) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setEmail(newContactMessage.getEmail());
        contactMessage.setMessage(newContactMessage.getMessage());
        contactMessage.setTopic(newContactMessage.getTopic());
        contactMessage.setDate(LocalDateTime.now());
        contactMessage.setIsRead(false);
        if(newContactMessage.getUserId() == null) {
            contactMessageRepository.saveAndFlush(contactMessage);
        } else {
            Optional<User> user = userRepository.findById(Long.parseLong(newContactMessage.getUserId()));
            user.ifPresent(contactMessage::setSender);
            user.get().getContactMessages().add(contactMessage);
            userRepository.saveAndFlush(user.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/contactMessage/{id}/read")
    public void readMessage(@PathVariable("id") Long id) {
        Optional<ContactMessage> contactMessage = contactMessageRepository.findById(id);
        contactMessage.ifPresent(contactMessage1 -> {
            contactMessage1.setIsRead(true);
            contactMessageRepository.saveAndFlush(contactMessage1);
        });
    }

    @GetMapping("/contactMessage/{id}/unRead")
    public void unreadMessage(@PathVariable("id") Long id) {
        Optional<ContactMessage> contactMessage = contactMessageRepository.findById(id);
        contactMessage.ifPresent(contactMessage1 -> {
            contactMessage1.setIsRead(false);
            contactMessageRepository.saveAndFlush(contactMessage1);
        });
    }

    @GetMapping("/contactMessage/unReadCount")
    public Long getUnreadContactMessageCount() {
        return contactMessageRepository.getUnReadMessageCount();
    }

}
