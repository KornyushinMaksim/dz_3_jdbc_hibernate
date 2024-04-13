package tsk.jdbc.repository;

import org.springframework.stereotype.Repository;
import tsk.jdbc.model.Dessert;
import java.util.List;

@Repository
public interface DessertRepository {
    void createTable();
    void dropTable();
    void saveDessert(Dessert dessert);
    void removeDessertById(Long id);
    void updateDesert(Long id, Dessert dessert);
    Dessert getDessertById(Long id);
    List<Dessert> getAllDesserts ();
}
