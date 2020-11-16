
insert into roles (name) values ('ADMIN')
insert into roles (name) values ('HR')
insert into roles (name) values ('EMPLOYEE')

insert into credentials (email, password) values ('admin@admin.com','admin')
insert into credentials (email, password) values ('hr@hr.com','hr')

insert into credentials_roles (credentials_id, roles_id) values (1, 1)
insert into credentials_roles (credentials_id, roles_id) values (2, 2)




insert into departments (name) values ('Oncology')
insert into departments (name) values ('Nursing')


insert into job_positions (name, department_name) values ('Oncology Department Chief','Oncology')
insert into job_positions (name, department_name) values ('Oncology Job Position 2 ','Oncology')
insert into job_positions (name, department_name) values ('Oncology Job Position 3 ','Oncology')

insert into job_positions (name, department_name) values ('Nursing Department Chief','Nursing')
insert into job_positions (name, department_name) values ('Nursing Job Position 2 ','Nursing')
insert into job_positions (name, department_name) values ('Nursing Job Position 3 ','Nursing')




insert into document_types (name) values ('Education')
insert into document_types (name) values ('Professional Certificate')
insert into document_types (name) values ('Drivers Licence')
insert into document_types (name) values ('SSN Card')
insert into document_types (name) values ('Birth Certificate')
