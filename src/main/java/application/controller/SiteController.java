package application.controller;

import application.model.SiteSettings;
import application.model.system.ContactMessage;
import application.model.system.NewContactMessage;
import application.repository.ContactMessageRepository;
import application.repository.SiteSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SiteController {

    @Autowired SiteSettingsRepository siteSettingsRepository;
    @Autowired ContactMessageRepository contactMessageRepository;

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
        siteSettingsRepository.saveAndFlush(oldSettings);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/contact/message/{id}")
    public ContactMessage serveContactMessageById(@PathVariable Long id) {
        Optional<ContactMessage> contactMessage = contactMessageRepository.findById(id);
        return contactMessage.orElse(null);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/contact/message")
    public List<ContactMessage> serveAllContactMessage() {
        return contactMessageRepository.findAll();
    }

    @PostMapping("/contact/message/new")
    public ResponseEntity<?> saveNewMessage(@RequestBody NewContactMessage newContactMessage) {
        ContactMessage contactMessage = new ContactMessage()
        contactMessageRepository.saveAndFlush(contactMessage);
        System.out.println("CONTACT MESSAGE: " + contactMessage.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
