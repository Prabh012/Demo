package controllers;

import models.DbConnector;
import models.Person;
import models.Roles;
import play.core.j.HttpExecutionContext;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.*;



public class HomeController extends Controller {



    public Result index() {
//Roles r = new Roles();
//r.setRoles("Prabhjot");
//r.setId(2);
        //Person p=new Person() {

        ///////};

       // DbConnector.save(r);
        return ok();
    }

}
