package github.luisfeliperochamartins.api.person;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retorna todas as pessoas cadastradas no Banco de Dados.
     *
     * @return List<Person>
     */
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    /**
     * Retorna somente um pessoa através do parâmetro.
     *
     * @param personId Long - Id da Pessoa.
     * @return Optional<Person>
     */
    public Optional<Person> getPerson(Long personId) {
        return Optional.ofNullable(personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Person with id = " + personId + "does not exists" )));
    }

    /**
     * Adiciona uma nova Pessoa
     *
     * @param person Person - Pessoa
     */
    public void addNewPerson(@RequestBody Person person){
        verifyEmail(person.getEmail());
        personRepository.save(person);
    }

    /**
     * Método responsável por verificar se o email existe. Caso exista throws Exception.
     *
     * @param email String - Email da Pessoa
     * @throws IllegalStateException
     */
    public void verifyEmail(String email){
        Optional<Person> newEmail = personRepository.findPersonByEmail(email);

        if(newEmail.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
    }

    /**
     * Deleta uma pessoa com base no Id passado por parâmetro.
     *
     * @param personId Long - Id da Pessoa
     */
    public void deletePerson(Long personId){
        boolean person = personRepository.existsById(personId);

        if(!person){
            throw new IllegalStateException("Person with id = " + personId + "does not exists");
        }
        personRepository.deleteById(personId);
    }

    /**
     *Altera
     *
     * @param personId Long    - Id da Pessoa
     * @param person   Person  - Nome da Pessoa
     */

    @Transactional
    public void updatePerson(Long personId, Person person) {
        Person personDB = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Person with id " + personId + " does not exists"
                ));
        if (person.getName() != null && !person.getName().isBlank() && !Objects.equals(personDB.getName(), person.getName())){
            personDB.setName(person.getName());
        }

        if (person.getEmail() != null && !person.getEmail().isBlank()  && !Objects.equals(personDB.getEmail(), person.getEmail())){
            personDB.setEmail(person.getEmail());
        }
    }
}