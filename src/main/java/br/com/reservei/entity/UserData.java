package br.com.reservei.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date birthday;

    @OneToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

}
