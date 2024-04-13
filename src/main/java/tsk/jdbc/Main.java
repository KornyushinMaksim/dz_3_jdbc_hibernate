package tsk.jdbc;

import tsk.jdbc.model.Dessert;
import tsk.jdbc.service.DessertService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DessertService dessertService = new DessertService();

        dessertService.createTable();

        Dessert napoleon = Dessert.builder()
                .name("Наполеон")
                .weight(210)
                .cost(230)
                .description("Слоеный, хрустящий")
                .build();

        Dessert tiramisu = Dessert.builder()
                .name("Тирамису")
                .weight(180)
                .cost(280)
                .description("Кофейный, нежный")
                .build();

        Dessert honeyPie = Dessert.builder()
                .name("Медовик")
                .weight(200)
                .cost(180)
                .description("Сладкий и ароматный")
                .build();

        Dessert zaher = Dessert.builder()
                .name("Захер")
                .weight(800)
                .cost(2800)
                .description("Шоколадный")
                .build();

        Dessert donut = Dessert.builder()
                .name("Пончик")
                .weight(80)
                .cost(110)
                .description("Идеально сочетается с джемами")
                .build();

        Dessert eclair = Dessert.builder()
                .name("Эклер")
                .weight(60)
                .cost(90)
                .description("Легкий, кремовый")
                .build();

//        dessertService.saveDessert(napoleon);
//        dessertService.saveDessert(tiramisu);
//        dessertService.saveDessert(honeyPie);
//        dessertService.saveDessert(zaher);
//        dessertService.saveDessert(donut);

        dessertService.removeDessertById(2L);

        System.out.println(dessertService.getDessertById(5L));
        System.out.println();

        List<Dessert> desserts = dessertService.getAllDesserts();
        desserts.forEach(System.out::println);
        System.out.println();

        dessertService.updateDesert(3L, eclair);

        List<Dessert> desserts1 = dessertService.getAllDesserts();
        desserts1.forEach(System.out::println);

//        dessertService.dropTable();
    }
}
