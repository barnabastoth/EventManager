package application.repository;

import application.model.Menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu getMenuByID(Long id);
}
