create table if not exists users (
  id serial primary key not null,
  username varchar(50),
  password text
);

create table if not exists resource (
   id serial primary key not null,
    site varchar(50),
    user_id int references users(id)
);

create table if not exists link (
 id serial primary key not null,
uri varchar(150),
code text,
count int,
resource_id int references resource(id)
);