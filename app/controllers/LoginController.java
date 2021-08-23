package controllers;

import dto.PersonDto;
import models.Person;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.LoginServiceImpl;
import services.RolesServiceImpl;
import services.StatusServiceImpl;

import javax.inject.Inject;
import java.util.List;

public class LoginController extends Controller {

    @Inject
    private StatusServiceImpl statusServiceImpl;

    @Inject
    private LoginServiceImpl loginServiceImpl;

    @Inject
    private RolesServiceImpl rolesServiceImpl;

    @Inject
    private FormFactory formFactory;


    public Result create(){
        Person person= loginServiceImpl.createUser("P","","k","Delhi","a@gmail.com","12345");

        return ok(Json.toJson(person));}

    public Result fetch(Integer id) {
        Person person = loginServiceImpl.fetchUser(id);
        if (person == null) {
            return ok("No Email exist " + id);
        }
        ////poitive case
        //System.out.println("Person " + person.getId());
        return ok(Json.toJson(loginServiceImpl.populatePerson(person)));
        //return ok("succes with persp Id "  + person.getId() + " Name : " + person.getFirstName() +
        //      " role name : " + person.getRoles().getRoles());
    }


    public Result updateStatus(Integer id, Integer  statusId){
        Person  person = loginServiceImpl.updateStatus(id, statusId);
        return ok("updated");
    }

    public Result save(Http.Request request) {
        Form<PersonDto> personDtoForm=formFactory.form(PersonDto.class).bindFromRequest(request);
loginServiceImpl.savePerson(personDtoForm.get());
        return ok("save successfully");
    }


    public Result login(Http.Request request) {
        Form<PersonDto> personDtoForm = formFactory.form(PersonDto.class).bindFromRequest(request);
        Person person = loginServiceImpl.fetchUserbyEmail(personDtoForm.get().getEmailId());
        if (person == null) {
            Logger.error("Emal ID doesnot exists");
            return ok("No Email exist");
        }
        if(person.getStatus().getId().equals(statusServiceImpl.inactive().getId()))
            return ok("Person not active"       );
        Logger.debug("database password " + person.getPassword() );
        Logger.debug("UI password " + personDtoForm.get().getPassword());
        if (person.getPassword().equals(personDtoForm.get().getPassword())) {
            Logger.info("Login successful");
            return ok("Login Successfully");
            //// SInce this is a negative condition so not
        }

        return ok("Invalid password");
    }
        public Result listOfStudent(Http.Request request){
            List<PersonDto> personDtoList= loginServiceImpl.listOfUser();
Logger.debug("Returning size  " + personDtoList.size() + "   " + Json.toJson(personDtoList));
        return ok(Json.toJson(personDtoList));
        }



    }

