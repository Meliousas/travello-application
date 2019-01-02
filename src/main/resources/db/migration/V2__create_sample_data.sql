insert into accounts (id, description,email,is_admin,is_business,password,photo,username)
values (0, 'First admin', 'admin@admin.com', true, false, 'admin', null, 'grubas');
insert into accounts (id, description,email,is_admin,is_business,password,photo,username)
values (1, 'First traveller', 'traveller@mail.com', false, false, 'traveller', null, 'grubas');
insert into accounts (id, description,email,is_admin,is_business,password,photo,username)
values (2, 'First business', 'business@mail.com', false, true, 'business', null, 'grubas');


insert into trips (id, description, start_date, end_date , public_rating, status, title, account_id)
values (1, 'First trip', now() , now() , 0.34, 1, 'First trip title', 0);

insert into trips (id, description, start_date, end_date , public_rating, status, title, account_id)
values (2, 'second trip', now() , now() , 0.34, 1, '2 trip title', 0);

insert into trips (id, description, start_date, end_date , public_rating, status, title, account_id)
values (3, 'First trip', now() , now() , 0.34, 1, 'First trip title', 0);

insert into trips (id, description, start_date, end_date , public_rating, status, title, account_id)
values (4, 'First trip', now() , now() , 0.34, 1, 'First trip title', 1);


insert into cards (id, title, type, trip_id) values (0, 'tralalalla', 0, 1);
insert into cards (id, title, type, trip_id) values (1, 'tralalallajajaaj', 0, 0);

insert into trip_countries (trip_id, country) values (0, 'poland');
insert into trip_countries (trip_id, country) values (0, 'germany');

