create table spar.seedlot_owner_quantity (
  seedlot_number          varchar(5) not null,
  owner_client_number     varchar(8) not null,
  owner_locn_code         varchar(2) not null,
  original_pct_owned      decimal(4, 1) not null,
  original_pct_rsrvd      decimal(4, 1) not null,
  original_pct_srpls      decimal(4, 1) not null,
  method_of_payment_code  varchar(3),
  spar_fund_srce_code     varchar(3),
  entry_userid            varchar(30) not null,
  entry_timestamp         timestamp not null,
  update_userid           varchar(30) not null,
  update_timestamp        timestamp not null,
  revision_count          int not null,
  constraint seedlot_owner_quantity_pk
    primary key (seedlot_number, owner_client_number, owner_locn_code),
  constraint seedlot_owner_qty_seedlot_fk
    foreign key(seedlot_number) references spar.seedlot(seedlot_number)
);
