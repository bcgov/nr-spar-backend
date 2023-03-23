create table spar.smp_mix (
    seedlot_number 	varchar(5) not null,
    parent_tree_id 	int not null,
    amount_of_material	int not null,
    proportion		decimal(20,10),
    entry_userid	varchar(30) not null,
    entry_timestamp	timestamp not null,
    update_userid	varchar(30) not null,
    update_timestamp	timestamp not null,
    revision_count	int not null,
    constraint smp_mix_pk
        primary key(seedlot_number, parent_tree_id),
    constraint smp_mix_seedlot_fk
        foreign key(seedlot_number) references spar.seedlot(seedlot_number)
);
