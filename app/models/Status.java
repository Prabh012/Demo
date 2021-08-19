package models;

import io.ebean.Finder;
import services.LoginService;

import javax.persistence.*;

@Entity(name = "Status")
public class Status {



    @Column(name = "status")
    private String status;

    public  static final Finder<Integer, Status> find = new Finder<>(Status.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    public String getStatus() {
        return status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
       Id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
