package tn.esprit.autoloc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.entities.enums.Role;

@Entity@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idemployer;
    String nom;
    String prenom;
    @Enumerated(EnumType.STRING)
    Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="agence_id")
    Agence agnce;
}
