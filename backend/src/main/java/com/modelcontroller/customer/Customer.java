package com.modelcontroller.customer;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
/*
Avec l'annotation @Table, je peux avoir le plein contrôle sur ma table.
Par exemple, je peux renommer la table comme je veux. Par défaut, c’est le nom de la classe en minuscule.
Il vaut mieux laisser par défaut.
Je peux aussi y définir les contraintes sur les colonnes de cette table (entity).
*/
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                )
        }
)
public class Customer {


    @Id
    @SequenceGenerator(
            // Le "name" est le nom de la séquence utilisé dans le code Java. Il n'affecte pas la BD.
            name = "customer_id_seq",
            /*
             Le "sequenceName" est le nom de la séquence qui existera réellement dans la base de données.
             Remarque : La priorité est donnée à Flyway.
             Cela signifie que si la séquence est déjà définie par Flyway ou par défaut (sous forme nomTable_nomColumn_seq),
             Hibernate utilisera ce nom plutôt que celui défini ici.
             */
            sequenceName = "customer_id_seq",
            // Numéro de départ de la séquence
            initialValue = 1,
            // Taille du bloc de la séquence. Par défaut, c'est 50.
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            // Le "generator" doit correspondre au "name" défini dans @SequenceGenerator
            generator = "customer_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    /** @Enumerated: cette annotation indique a Hibernate comment stocker les valeurs de l'enum dans la base de données.
     * Par défaut, si tu ne mets pas cette annotation, JPA stocke l'index (l'ordre) de l'enum. MALE en 0 et FEMALE en 1.
     * Si demain tu décides d'ajouter une valeur au début de ton Enum (ex: UNKNOWN, MALE, FEMALE), l'index de MALE passerait
        de 0 à 1. Si tu avais stocké des chiffres, toutes tes données en base seraient faussées ! Avec STRING, "MALE" reste "MALE",
        peu importe l'ordre dans ton code.
    */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;


    public Customer() {
    }

    public Customer(String name, String email, Integer age, Gender gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public Customer(Integer id, String name, String email, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public void setGender(Gender gender) {  this.gender = gender; }

    public Gender getGender() { return this.gender; }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer personne = (Customer) o;
        return Objects.equals(id, personne.id) &&
                Objects.equals(name, personne.name) &&
                Objects.equals(email, personne.email) &&
                Objects.equals(age, personne.age)&&
                Objects.equals(gender, personne.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, gender);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }


}
