insert into user_details(id, birth_date, name)
values (10001, current_date(), 'Amy');


insert into user_details(id, birth_date, name)
values (10002, current_date(), 'Mon');


insert into user_details(id, birth_date, name)
values (10003, current_date(), 'Naing');


insert into post(id, description, user_id)
values (2001, 'I want to learn JAVA', 10001);

insert into post(id, description, user_id)
values (2002, 'I want to learn Spring Boot', 10001);

insert into post(id, description, user_id)
values (2003, 'I want to learn Microservice architecture', 10001);


insert into post(id, description, user_id)
values (2004, 'I want to learn Python', 10002);

insert into post(id, description, user_id)
values (2005, 'I want to learn Excel', 10002);

insert into post(id, description, user_id)
values (2006, 'I want to learn Excel', 10003);