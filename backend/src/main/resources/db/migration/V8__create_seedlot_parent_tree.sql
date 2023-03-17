create table spar.seedlot_parent_tree (
    seedlot_number                varchar(5) not null,
    parent_tree_id                int not null,
    cone_count                    decimal(20, 10) not null,
    pollen_count                  decimal(20, 10) not null,
    smp_success_pct               int,
    non_orchard_pollen_contam_pct int,
    entry_userid                  varchar(30) not null,
    entry_timestamp               timestamp not null,
    update_userid                 varchar(30) not null,
    update_timestamp              timestamp not null,
    revision_count                int not null,
	constraint seedlot_parent_tree_pk
		primary key(seedlot_number, parent_tree_id),
	constraint seedlot_parent_tree_seedlot_fk
		foreign key(seedlot_number) references spar.seedlot(seedlot_number)
);
