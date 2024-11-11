package br.com.reservei.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @JsonIgnoreProperties("cities")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnoreProperties("state")
    private City city;

    private String neighborhood;

    private String address_line;

    private String complements;

}
