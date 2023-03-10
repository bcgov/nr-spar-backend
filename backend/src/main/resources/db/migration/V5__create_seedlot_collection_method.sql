create table spar.seedlot_collection_method (
  seedlot_number              varchar(5) not null,
  cone_collection_method_code varchar(30) not null,
  cone_collection_method_desc varchar(400),
  entry_userid                varchar(30) not null,
  entry_timestamp             timestamp not null,
  update_userid               varchar(30) not null,
  update_timestamp            timestamp not null,
  revision_count              int not null,
  constraint seedlot_collection_method_pk
    primary key(seedlot_number, cone_collection_method_code),
  constraint seedlot_coll_met_seedlot_fk
    foreign key(seedlot_number) references spar.seedlot(seedlot_number)
);
