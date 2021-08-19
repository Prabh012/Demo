package services;

import models.Status;

public class StatusService {

    public enum Enum {
        ACTIVE(1), INACTIVE(2);

        Enum(int id) {
            this.id = id;
        }

        private int id;

        public int getId() {
            return id;
        }
    }

    public Status getinstanceof(Enum type) {//Change datatype to Status
        Status status=new Status();
        status.setId(type.getId());
        return status;
    }

    public Status active(){
        return getinstanceof(Enum.ACTIVE);
    }

    public Status inactive(){
        return getinstanceof(Enum.INACTIVE);
    }



}

