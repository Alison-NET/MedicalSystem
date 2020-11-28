-- insert into roles (name) values ('ADMIN')
-- insert into roles (name) values ('HR')
-- insert into roles (name) values ('EMPLOYEE')

insert into credentials (email, password) values ('admin@admin.com','admin')
insert into credentials (email, password) values ('hr@hr.com','hr')

insert into credentials_roles (credentials_id, roles_id) values (1, 1)
insert into credentials_roles (credentials_id, roles_id) values (2, 2)


insert into titles (name) values ('Mr')
insert into titles (name) values ('Ms')
insert into titles (name) values ('Phd')
insert into titles (name) values ('MD')



insert into departments (name) values ('Sales')
insert into job_positions (name, department_name) values ('Sales manager','Sales')
insert into job_positions (name, department_name) values ('Sales representative','Sales')


insert into departments (name) values ('Administration')
insert into job_positions (name, department_name) values ('Laboratory director','Administration')


insert into departments (name) values ('Consultant')
insert into job_positions (name, department_name) values ('Consultant','Consultant')


insert into departments (name) values ('CoreLab')
insert into job_positions (name, department_name) values ('Lab Admin','CoreLab')
insert into job_positions (name, department_name) values ('Analyst','CoreLab')
insert into job_positions (name, department_name) values ('Accessioner','CoreLab')
insert into job_positions (name, department_name) values ('Lab Aid','CoreLab')
insert into job_positions (name, department_name) values ('Technician','CoreLab')
insert into job_positions (name, department_name) values ('Technologist','CoreLab')


insert into departments (name) values ('Molecular')
insert into job_positions (name, department_name) values ('Director','Molecular')
insert into job_positions (name, department_name) values ('Technician','Molecular')
insert into job_positions (name, department_name) values ('Technologist','Molecular')



insert into document_types (name) values ('Education')
insert into document_types (name) values ('Professional Certificate')
insert into document_types (name) values ('Drivers Licence')
insert into document_types (name) values ('SSN Card')
insert into document_types (name) values ('Birth Certificate')


-- WHEN TREE LOOP
--  UPDATE employees SET supervisor_basic_info_id = 1 WHERE basic_info_id = 2