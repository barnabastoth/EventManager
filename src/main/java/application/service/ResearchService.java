package application.service;

import application.model.Category;
import application.model.Research.NewResearch;
import application.model.Research.Research;
import application.model.authentication.User;
import application.repository.CategoryRepository;
import application.repository.ResearchRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ResearchService {
    
    @Autowired ResearchRepository researchRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired UserRepository userRepository;
    
    public Long handleNewResearchCreation(NewResearch newResearch) {
        System.out.println("ANYAD" + newResearch.getName());
        Research research = new Research();
        research.setName(newResearch.getName());
        research.setContent(newResearch.getContent());
        research.setDate(LocalDateTime.now());
        if(newResearch.getDate().length() > 0) {
            research.setDate(LocalDateTime.parse(newResearch.getDate()));
        } else {
            research.setDate(LocalDateTime.of(2080, 4, 20, 1, 30));
        }

        List<Category> categories = new ArrayList<>();
        for(int i = 0; i < newResearch.getCategories().size(); i++) {
            Optional<Category> category = categoryRepository.findById(Long.valueOf(newResearch.getCategories().get(i).get("id")));
            category.ifPresent(categories::add);
        }
        research.setCategories(categories);

        researchRepository.saveAndFlush(research);
        return research.getId();
    }
}
