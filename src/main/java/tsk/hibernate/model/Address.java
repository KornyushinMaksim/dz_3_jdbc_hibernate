package tsk.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "postal_code")
    private Integer postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "name_street")
    private String nameStreet;
    @Column(name = "house")
    private Integer house;

    public Address setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public Address setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
        return this;
    }

    public Address setHouse(Integer house) {
        this.house = house;
        return this;
    }
}
