package tsk.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dessert implements Comparable{

    private Long id;
    private String name;
    private Integer weight;
    private Integer cost;
    private String description;

    @Override
    public int compareTo(Object o) {
        Dessert dessert = (Dessert) o;
        return this.id.compareTo(dessert.id);
    }
}
