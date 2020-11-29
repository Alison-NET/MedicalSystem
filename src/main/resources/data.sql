insert into titles (name) values ('Mr')
insert into titles (name) values ('Ms')
insert into titles (name) values ('Phd')
insert into titles (name) values ('MD')


-- ADMIN CREATION

insert into basic_employees (title_id, first_name, middle_name, last_name, date_of_birth, address_first, address_second, city, state, zip, home_phone, mobile_phone, personal_email, social_security)
values (1, 'Admin', 'Admin', 'Admin', '2000-05-05 00:00:00', '-', '-', '-', '-', '-','-','-','-','-')

insert into departments (name) values ('System Admin')
insert into job_positions (name, department_name) values ('Admin','System Admin')

insert into work_shifts (beginning, ending) values ('08:00:00', '16:00:00')

insert into credentials (email, password) values ('admin@admin.com','admin')

insert into employees (basic_info_id, job_position_id, salary, commission, work_shift_id, credentials_id)
values (1, 1 , 0, 0, 1, 1)

--

insert into departments (name, chief_job_position_id) values ('Human Resources', 1)
insert into job_positions (name, department_name) values ('Human Resource Manager','Human Resources')


insert into departments (name, chief_job_position_id) values ('Sales', 1)
insert into job_positions (name, department_name) values ('Sales Manager','Sales')
insert into job_positions (name, department_name) values ('Sales Representative','Sales')


insert into departments (name, chief_job_position_id) values ('Administration', 1)
insert into job_positions (name, department_name) values ('Laboratory Director','Administration')


insert into departments (name, chief_job_position_id) values ('Consultant', 1)
insert into job_positions (name, department_name) values ('Consultant','Consultant')


insert into departments (name, chief_job_position_id) values ('CoreLab', 1)
insert into job_positions (name, department_name) values ('Lab Admin','CoreLab')
insert into job_positions (name, department_name) values ('Analyst','CoreLab')
insert into job_positions (name, department_name) values ('Accessioner','CoreLab')
insert into job_positions (name, department_name) values ('Lab Aid','CoreLab')
insert into job_positions (name, department_name) values ('Technician','CoreLab')
insert into job_positions (name, department_name) values ('Technologist','CoreLab')


insert into departments (name) values ('Molecular', 1)
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