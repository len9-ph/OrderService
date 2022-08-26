create sequence session_seq start with 1000 increment by 1;

create table "session" (
    id integer,
    session_id varchar(24),
    start_time timestamp,
    timeout_minutes integer);
alter table "session" add constraint session_id_pk primary key (id);
create index session_pk_idx on "session"(id);
