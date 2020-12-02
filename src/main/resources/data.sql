insert into pick_up_days_of_week (name) values ('Monday')
insert into pick_up_days_of_week (name) values ('Tuesday')
insert into pick_up_days_of_week (name) values ('Wednesday')
insert into pick_up_days_of_week (name) values ('Thursday')
insert into pick_up_days_of_week (name) values ('Friday')


insert into titles (name) values ('MR')
insert into titles (name) values ('MS')
insert into titles (name) values ('PHD')
insert into titles (name) values ('MD')
insert into titles (name) values ('DO')
insert into titles (name) values ('NP')
insert into titles (name) values ('PA')


-- ADMIN CREATION

insert into basic_employees (title_id, first_name, middle_name, last_name, date_of_birth, address_first, address_second, city, state, zip, home_phone, mobile_phone, personal_email, social_security)
values (1, 'Admin', 'Admin', 'Admin', '2000-05-05 00:00:00', '-', '-', '-', '-', '-','-','-','-','-')

insert into departments (name) values ('System Admin')
insert into job_positions (name, department_name) values ('Admin','System Admin')                       -- manages everything
update departments set chief_job_position_id=1 where name='System Admin'

insert into work_shifts (beginning, ending) values ('08:00:00', '16:00:00')

insert into credentials (email, password) values ('admin@admin.com','admin')

insert into employees (basic_info_id, job_position_id, salary, commission, work_shift_id, credentials_id)
values (1, 1 , 0, 0, 1, 1)

--

insert into departments (name) values ('Human Resources')
insert into job_positions (name, department_name) values ('Human Resource Manager','Human Resources')   -- approves employee, changes employee, manages documents for job positions
update departments set chief_job_position_id=2 where name='Human Resources'


insert into departments (name) values ('Sales')
insert into job_positions (name, department_name) values ('Sales Manager','Sales')                      -- sends account for approval
insert into job_positions (name, department_name) values ('Sales Representative','Sales')
update departments set chief_job_position_id=3 where name='Sales'


insert into departments (name) values ('Administration')
insert into job_positions (name, department_name) values ('Laboratory Director','Administration')
update departments set chief_job_position_id=5 where name='Administration'


insert into departments (name) values ('Consultant')
insert into job_positions (name, department_name) values ('Consultant','Consultant')
update departments set chief_job_position_id=6 where name='Consultant'


insert into departments (name) values ('CoreLab')
insert into job_positions (name, department_name) values ('Lab Admin','CoreLab')
insert into job_positions (name, department_name) values ('Analyst','CoreLab')
insert into job_positions (name, department_name) values ('Accessioner','CoreLab')
insert into job_positions (name, department_name) values ('Lab Aid','CoreLab')
insert into job_positions (name, department_name) values ('Technician','CoreLab')
insert into job_positions (name, department_name) values ('Technologist','CoreLab')
update departments set chief_job_position_id=7 where name='CoreLab'


insert into departments (name) values ('Molecular')
insert into job_positions (name, department_name) values ('Director','Molecular')
insert into job_positions (name, department_name) values ('Technician','Molecular')
insert into job_positions (name, department_name) values ('Technologist','Molecular')
update departments set chief_job_position_id=13 where name='Molecular'


insert into document_types (name) values ('Education')
insert into document_types (name) values ('Professional Certificate')
insert into document_types (name) values ('Drivers Licence')
insert into document_types (name) values ('SSN Card')
insert into document_types (name) values ('Birth Certificate')


-- WHEN TREE LOOP
--  UPDATE employees SET supervisor_basic_info_id = 1 WHERE basic_info_id = 2