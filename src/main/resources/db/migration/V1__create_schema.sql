create table public.accounts (
  id bigint primary key,
  description varchar(5000),
  email varchar(100) not null,
  is_admin boolean not null,
  is_business boolean not null,
  password varchar(255) not null,
  photo varchar(2000),
  username varchar(50)
);

create table public.cards (
  id bigint primary key,
  date date,
  description varchar(2000),
  photo varchar(2000),
  title varchar(255) not null,
  trip_id bigint
);

create table public.trips (
  id bigint primary key,
  end_date date,
  public_rating double precision not null default 0.0,
  start_date date,
  status integer,
  title varchar(255) not null,
  description text,
  account_id bigint

);