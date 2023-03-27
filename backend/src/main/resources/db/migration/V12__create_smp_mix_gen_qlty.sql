create table spar.smp_mix_gen_qlty (
    seedlot_number 		varchar(5) not null,
    parent_tree_id 		int not null,
    genetic_type_code 		varchar(2) not null,
    genetic_worth_code 		varchar(3) not null,
    genetic_quality_value 	decimal(4, 1) not null,
    estimated_ind 		boolean not null,
    entry_userid		varchar(30) not null,
    entry_timestamp		timestamp not null,
    update_userid		varchar(30) not null,
    update_timestamp		timestamp not null,
    revision_count		int not null,
    constraint smp_mix_gen_qlty_pk
        primary key(seedlot_number, parent_tree_id, genetic_type_code, genetic_worth_code),
    constraint smp_mix_gen_qlty_smp_mix_pk
        foreign key(seedlot_number, parent_tree_id) references spar.smp_mix(seedlot_number, parent_tree_id)
);
