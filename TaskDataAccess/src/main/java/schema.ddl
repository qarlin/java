
    create table BusinessObject (
        uid bigint generated by default as identity,
        creationDate timestamp,
        STATUS_ID varchar(255),
        primary key (uid)
    );

    create table ExecutionLog (
        id bigint generated by default as identity,
        endDate timestamp,
        logFile varchar(255),
        starDate timestamp,
        status integer,
        TASK_ID bigint,
        primary key (id)
    );

    create table Schedule (
        id bigint generated by default as identity,
        cron varchar(255),
        notify boolean not null,
        TASK_ID bigint,
        primary key (id)
    );

    create table StatusType (
        code varchar(255) not null,
        color varchar(255),
        description varchar(255),
        icon varchar(255),
        primary key (code)
    );

    create table Task (
        id bigint generated by default as identity,
        name varchar(255),
        parameters varchar(255),
        taskProcess varchar(255),
        primary key (id)
    );

    create table ViewAtributes (
        uid bigint not null,
        stars integer not null,
        CATEGORY_ID bigint,
        primary key (uid)
    );

    create table ViewCategory (
        id bigint generated by default as identity,
        color varchar(255),
        name varchar(255),
        primary key (id)
    );

    alter table BusinessObject 
        add constraint FK34324B3F8E39AA7 
        foreign key (uid) 
        references ViewAtributes;

    alter table BusinessObject 
        add constraint FK34324B3F448B3DBA 
        foreign key (STATUS_ID) 
        references StatusType;

    alter table ExecutionLog 
        add constraint FKC13E7D2C1EF8F329 
        foreign key (TASK_ID) 
        references Task;

    alter table Schedule 
        add constraint FKDA40F6B71EF8F329 
        foreign key (TASK_ID) 
        references Task;

    alter table ViewAtributes 
        add constraint FK92F6CE405205E025 
        foreign key (CATEGORY_ID) 
        references ViewCategory;
