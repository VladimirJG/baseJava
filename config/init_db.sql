-- auto-generated definition
create table resume
(
    uuid      char(36) not null
        constraint uuid
            primary key,
    full_name text     not null
);

alter table resume
    owner to postgres;


-- auto-generated definition
create table contact
(
    id          integer  not null
        constraint id
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade
);

alter table contact
    owner to postgres;

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

