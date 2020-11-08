
insert into roles (name) values ('ADMIN')
insert into roles (name) values ('HR')
insert into roles (name) values ('EMPLOYEE')

insert into departments (name) values ('Department1')
insert into departments (name) values ('Department2')

insert into credentials (email, password) values ('admin@admin.com','admin')
insert into credentials (email, password) values ('hr@hr.com','hr')

insert into credentials_roles (credentials_id, roles_id) values (1, 1)
