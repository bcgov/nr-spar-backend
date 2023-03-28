create table spar.seedlot_parent_tree_smp_mix (
    seedlot_number		varchar(5) not null,
    parent_tree_id		int not null,
    genetic_type_code		varchar(2) not null,
    genetic_worth_code		varchar(3) not null,
    genetic_quality_value	decimal(4, 1) not null,
    entry_userid		varchar(30) not null,
    entry_timestamp		timestamp not null,
    update_userid		varchar(30) not null,
    update_timestamp		timestamp not null,
    revision_count		int not null,
    constraint seedlot_parent_tree_smp_mix_pk
        primary key(seedlot_number, parent_tree_id, genetic_type_code, genetic_worth_code),
    constraint sl_ptree_smp_mix_sl_ptree_fk
        foreign key(seedlot_number, parent_tree_id) references spar.seedlot_parent_tree(seedlot_number, parent_tree_id)
);
