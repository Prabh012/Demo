package services;

import dto.PersonDto;
import models.DbConnector;
import models.Person;
import models.Status;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Performs CRUD
 */
@Singleton
public class LoginServiceImpl implements LoginService{

    @Inject
    private StatusServiceImpl statusServiceImpl;

    @Inject
    private RolesServiceImpl rolesServiceImpl;

    public Person createUser(String firstName, String middleName, String lastName, String address, String email, String password) {

        Person p = new Person();

        p.setFirstName(firstName);
        p.setMiddleName(middleName);
        p.setLastName(lastName);
        p.setEmailId(email);
        p.setAddress(address);
        p.setPassword(password);
        //p.setRoles(roles);
        p.setCreatedOn(new Date());
        DbConnector.save(p);
        return p;

    }
    public Person fetchAdminByEmail(String email) {
        return Person.find.query().where().eq("email_id",email)
                .eq("roles", rolesServiceImpl.admin().getId()).findOne();

    }

    public Person fetchUserbyEmail(String email) {
        return Person.find.query().where().eq("email_id",email)
                .eq("roles_id", rolesServiceImpl.user().getId()).findOne();

    }

    public List<PersonDto> listOfUser(){
        List<PersonDto> personDtoList = new ArrayList<>();

        List<Person> personList = Person.find.query().where().findList();
        for(Person person : personList) { //Iterate through for each loop
            PersonDto personDto;
            personDto = populatePerson(person);
            personDtoList.add(personDto);
        }

        return personDtoList;

    }

    public String savePerson(PersonDto  personDto) {

        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setMiddleName(personDto.getMiddleName());
        person.setLastName(personDto.getLastName());
        person.setAddress(personDto.getAddress());
        person.setEmailId(personDto.getEmailId());
        person.setPassword(personDto.getPassword());
        person.setRoles(rolesServiceImpl.user());
        person.setCreatedBy(Person.find.byId(1));
        person.setCreatedOn(new Date());
        person.setUpdatedBy(Person.find.byId(1));
        person.setUpdatedOn(new Date());
        person.setStatus(statusServiceImpl.active());
        DbConnector.save(person);
        return "success , saved successfully";
    }

    public Person updateStatus(Integer id, Integer statusId) {
        Person person = Person.find.byId(id);
        person.setStatus(Status.find.byId(statusId));
        DbConnector.update(person);
        return person;

    }

    public Person fetchUser(Integer id) {
        Person person = Person.find.byId(id);
        return person;
    }


    public PersonDto populatePerson(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setMiddleName(person.getMiddleName());
        personDto.setLastName(person.getLastName());
        personDto.setAddress(person.getAddress());
        personDto.setEmailId(person.getEmailId());
        personDto.setPassword(person.getPassword());
        personDto.setStatus(person.getStatus().getStatus());

        personDto.setCreatedBy(person.getCreatedBy().getFirstName());
        personDto.setCreatedOn(person.getCreatedOn());
        personDto.setUpdatedBy(person.getUpdatedBy().getFirstName());
        personDto.setUpdatedOn(person.getUpdatedOn());
        personDto.setRoleName(person.getRoles().getRoles());
        return personDto;
    }

    public void deleteUser(Integer id) {
        Person person = Person.find.byId(id);
        DbConnector.delete(person);
    }

}



