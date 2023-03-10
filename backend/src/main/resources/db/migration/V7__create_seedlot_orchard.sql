create table spar.seedlot_orchard (
  seedlot_number    varchar(5) not null,
  orchard_id        varchar(3) not null,
  primary_ind       boolean not null,
  entry_userid      varchar(30) not null,
  entry_timestamp   timestamp not null,
  update_userid     varchar(30) not null,
  update_timestamp  timestamp not null,
  revision_count		int not null,
  constraint seedlot_orchard_pk
    primary key (seedlot_number, orchard_id),
  constraint seedlot_orchard_seedlot_fk
    foreign key(seedlot_number) references spar.seedlot(seedlot_number)
);
