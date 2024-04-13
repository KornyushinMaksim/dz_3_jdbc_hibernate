package tsk.jdbc.service;

import org.springframework.stereotype.Service;
import tsk.jdbc.model.Dessert;
import tsk.jdbc.repository.DessertRepository;
import tsk.jdbc.repository.DessertRepositoryImpl;
import java.util.List;

@Service
public class DessertService {

    public final DessertRepository dessertRepository = new DessertRepositoryImpl();

    public void createTable(){
        dessertRepository.createTable();
    }
    public void dropTable() {
        dessertRepository.dropTable();
    }
    public void saveDessert(Dessert dessert) {
        dessertRepository.saveDessert(dessert);
    }
    public void removeDessertById(Long id) {
        dessertRepository.removeDessertById(id);
    }
    public List<Dessert> getAllDesserts () {
        return dessertRepository.getAllDesserts();
    }
    public Dessert getDessertById(long id) {
        return dessertRepository.getDessertById(id);
    }
    public void updateDesert(long id, Dessert dessert) {
        dessertRepository.updateDesert(id, dessert);
    }
}
