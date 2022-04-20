create table if not exists users (
  id serial primary key not null,
  username varchar(50),
  password varchar(50)
);

create table if not exists resource (
   id serial primary key not null,
    site varchar(50),
    user_id int references users(id)
);

create table if not exists link (
 id serial primary key not null,
url varchar(150),
resource_id int references resource(id)
);