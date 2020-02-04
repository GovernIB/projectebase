
    alter table PBS_PROCEDIMENT 
       drop constraint PBS_PROCEDIMENT_UNITAT_FK;

    drop table if exists PBS_PROCEDIMENT cascade;

    drop table if exists PBS_UNITATORGANICA cascade;

    drop sequence if exists PBS_PROCEDIMENT_SEQ;

    drop sequence if exists PBS_UNITATORGANICA_SEQ;
