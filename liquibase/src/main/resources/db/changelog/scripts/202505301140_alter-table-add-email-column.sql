alter table person add column email TEXT;

update person
set email = 'robertin@email.com'
where email is null;

alter table person alter column email set NOT NULL;