package services;

import dto.PersonDto;
import models.DbConnector;
import models.Person;
import models.Roles;
import models.Status;
import java.util.*;

import javax.inject.Inject;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * Performs CRUD
 */
public class LoginService {

    @Inject
    private StatusService statusService;


    @Inject
    private RolesService rolesService;

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
        //DbConnector.save(p);
        return p;

    }
    public Person fetchAdminByEmail(String email) {
        return Person.find.query().where().eq("email_id",email)
                .eq("roles",rolesService.admin().getId()).findOne();

    }

    public Person fetchUserbyEmail(String email) {
        return Person.find.query().where().eq("email_id",email)
                .eq("roles_id",rolesService.user().getId()).findOne();

    }

    public List<Person> listOfUser(){
        return Person.find.query().where().findList();

    }




    public String savePerson(PersonDto  personDto) {

        Person person = new Person();

        person.setFirstName(personDto.getFirstName());
        person.setMiddleName(personDto.getMiddleName());
        person.setLastName(personDto.getLastName());
        person.setAddress(personDto.getAddress());
        person.setEmailId(personDto.getEmailId());
        person.setPassword(personDto.getPassword());
        person.setRoles(rolesService.user());
        person.setCreatedBy(Person.find.byId(1));
        person.setCreatedOn(new Date());
        person.setUpdatedBy(Person.find.byId(1));
        person.setUpdatedOn(new Date());
        person.setStatus(statusService.active());
        DbConnector.save(person);
        return "success , saved successfully";
    }

    public Person updateStatus(Integer id, Integer statusId) {
        Person person = Person.find.byId(id);
//        person.setFirstName("abcd");
//        person.setEmailId("b@gmail.com");
        person.setStatus(Status.find.byId(statusId));
        // person.setRoles(roles);
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
        person.setStatus(person.getStatus());
        personDto.setCreatedBy(person.getCreatedBy().getFirstName());
        personDto.setCreatedOn(person.getCreatedOn());
        personDto.setUpdatedBy(person.getUpdatedBy().getFirstName());
        personDto.setUpdatedOn(person.getUpdatedOn());
        personDto.setRoleName(person.getRoles().getRoles());
        return personDto;
    }

    public void deleteUser(Integer id) {
        Person person = Person.find.byId(id);
        // DbConnector.delete(person);
    }

}



