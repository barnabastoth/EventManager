package application.repository;

import application.model.Menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRoute(String route);
}
