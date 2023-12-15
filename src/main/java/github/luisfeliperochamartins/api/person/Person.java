package github.luisfeliperochamartins.api.person;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class Person {
    @Id
    @SequenceGenerator(
            name           = "person_sequence",
            sequenceName   = "person_sequence",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy  = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )

    @Column(
            name      = "id",
            nullable  = false,
            updatable = false
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String email;

    public Person() {

    }

    public Person(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
    }

    /**
     * Retorna o Id da Pessoa
     *
     * @return Long - Id da Pessoa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o id da Pessoa.
     *
     * @param id Long - Id da pessoa
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome da Pessoa.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da Pessoa
     *
     * @param name String - Nome da Pessoa.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o email da Pessoa.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o Email da Pessoa
     *
     * @param email String - Email da Pessoa.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Id = ").append(id).append('\n');
        sb.append("Nome: = ").append(name).append('\n');
        sb.append("Email: = ").append(email).append('\n');
        return sb.toString();
    }
}
