package application.controller;

import application.model.SiteSettings;
import application.repository.SiteSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SiteController {

    @Autowired SiteSettingsRepository siteSettingsRepository;

    @GetMapping("/siteSettings")
    public SiteSettings serveSiteSettings() {
        for (SiteSettings siteSettings: siteSettingsRepository.findAll()) {
            if(siteSettings != null) {
                return siteSettings;
            }
        }
        return null;
    }
}
