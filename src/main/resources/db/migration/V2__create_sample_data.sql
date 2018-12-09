insert into accounts (id, description,email,is_admin,is_business,password,photo,username)
values (0, 'First admin', 'admin@mail.com', true, false, 'admin', null, 'grubas');
insert into accounts (id, description,email,is_admin,is_business,password,photo,username)
values (1, 'First traveller', 'traveller@mail.com', false, false, 'traveller', null, 'grubas');
insert into accounts (id, description,email,is_admin,is_business,password,photo,username)
values (2, 'First business', 'business@mail.com', false, true, 'business', null, 'grubas');


insert into trips (id, description, start_date, end_date , public_rating, status, title, account_id)
values (0, 'First trip', now() , now() , 0.34, 1, 'First trip title', 0);
