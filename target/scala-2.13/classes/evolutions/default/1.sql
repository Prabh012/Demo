# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table status (
  id                            integer auto_increment not null,
  status                        varchar(255),
  constraint pk_status primary key (id)
);

create table person (
  id                            integer auto_increment not null,
  roles                         integer,
  status                        integer,
  first_name                    varchar(255),
  middle_name                   varchar(255),
  last_name                     varchar(255),
  email_id                      varchar(255),
  address                       varchar(255),
  password                      varchar(255),
  created_on                    datetime(6),
  created_by                    integer,
  updated_on                    datetime(6),
  updated_by                    integer,
  constraint pk_person primary key (id)
);

create table roles (
  id                            integer auto_increment not null,
  roles                         varchar(255),
  constraint pk_roles primary key (id)
);

create index ix_person_roles on person (roles);
alter table person add constraint fk_person_roles foreign key (roles) references roles (id) on delete restrict on update restrict;

create index ix_person_status on person (status);
alter table person add constraint fk_person_status foreign key (status) references status (id) on delete restrict on update restrict;

create index ix_person_created_by on person (created_by);
alter table person add constraint fk_person_created_by foreign key (created_by) references person (id) on delete restrict on update restrict;

create index ix_person_updated_by on person (updated_by);
alter table person add constraint fk_person_updated_by foreign key (updated_by) references person (id) on delete restrict on update restrict;


# --- !Downs

alter table person drop foreign key fk_person_roles;
drop index ix_person_roles on person;

alter table person drop foreign key fk_person_status;
drop index ix_person_status on person;

alter table person drop foreign key fk_person_created_by;
drop index ix_person_created_by on person;

alter table person drop foreign key fk_person_updated_by;
drop index ix_person_updated_by on person;

drop table if exists status;

drop table if exists person;

drop table if exists roles;

