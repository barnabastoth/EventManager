package application.controller;

import application.model.Research.NewResearch;
import application.model.Research.Research;
import application.repository.CategoryRepository;
import application.repository.ResearchRepository;
import application.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/research")
public class ResearchController {

    @Autowired ResearchRepository researchRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired ResearchService researchService;


    @GetMapping("/")
    public List<Research> serveAllResearch() {
        return researchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Research serveResearchById(@PathVariable("id") Long id) {
        return researchRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @PostMapping("/new")
    public Long handleNewResearch(@RequestBody NewResearch newResearch) {
        return researchService.handleNewResearchCreation(newResearch);
    }
}
