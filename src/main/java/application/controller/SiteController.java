package application.controller;

import application.model.SiteSettings;
import application.repository.SiteSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @PostMapping("/siteSettings/edit")
    public void editSiteSettings(@RequestBody SiteSettings newSettings) {
        SiteSettings oldSettings = siteSettingsRepository.getOne(newSettings.getId());
        oldSettings.setLeftBarOpen(newSettings.getLeftBarOpen());
        oldSettings.setRightBarOpen(newSettings.getRightBarOpen());
        siteSettingsRepository.saveAndFlush(oldSettings);
    }
}
