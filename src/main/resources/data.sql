
insert into roles (name) values ('ADMIN')
insert into roles (name) values ('HR')
insert into roles (name) values ('EMPLOYEE')

insert into credentials (email, password) values ('admin@admin.com','admin')
insert into credentials (email, password) values ('hr@hr.com','hr')

insert into credentials_roles (credentials_id, roles_id) values (1, 1)

insert into departments (name) values ('Department1')
insert into departments (name) values ('Department2')

insert into document_types (name) values ('Education')
insert into document_types (name) values ('Professional Certificate')
insert into document_types (name) values ('Drivers Licence')
insert into document_types (name) values ('SSN Card')
insert into document_types (name) values ('Birth Certificate')
