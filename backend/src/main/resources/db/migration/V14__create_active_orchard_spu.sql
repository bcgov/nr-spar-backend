create table spar.active_orchard_spu (
  orchard_id          varchar(3) not null,
  seed_plan_unit_id   int not null,
  active_ind boolean  not null,
  retired_ind boolean not null,
  no_spu_ind boolean  not null,
  constraint active_orchard_spu_pk
    primary key(orchard_id, seed_plan_unit_id));
