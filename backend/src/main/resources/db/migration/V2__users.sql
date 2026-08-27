create table users (
  id varchar(36) primary key,
  display_name varchar(160) not null,
  email varchar(255) not null unique,
  password_hash varchar(255) not null,
  role varchar(64) not null,
  created_at timestamp with time zone not null default current_timestamp
);
create unique index idx_users_email_lower on users (lower(email));
