package services;

import models.Roles;

public class RolesService {
    public enum Enum {
        ADMIN(1), USER(2);

        Enum(int id) {
            this.id = id;
        }

        private int id;

        public int getId() {
            return id;
        }
    }

    public Roles findByName(String name ) {
        return Roles.find.query().where().eq("roles", name).findOne();
    }

    public Roles getinstanceof(Enum type) {
        Roles roles=new Roles();
        roles.setId(type.getId());
        return roles;
    }

    public Roles user(){
        return getinstanceof(Enum.USER);
    }

    public Roles admin(){
        return getinstanceof(Enum.ADMIN);
    }

}

