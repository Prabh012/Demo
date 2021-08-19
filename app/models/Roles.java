package models;

import akka.stream.Attributes;
import io.ebean.Finder;

import javax.persistence.*;

@Entity(name = "roles")
public class Roles {


   //public enum RoleEnum {
       // USER,
        //ADMIN;
   //}
    public  static final Finder<Integer, Roles> find = new Finder<>(Roles.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @Column (name = "roles")
    private String roles;


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        roles = roles;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}


