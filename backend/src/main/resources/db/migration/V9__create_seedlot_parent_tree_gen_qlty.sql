create table spar.seedlot_parent_tree_gen_qlty (
    seedlot_number        varchar(5) not null,
    parent_tree_id        int not null,
    genetic_type_code     varchar(2) not null,
    genetic_worth_code    varchar(3) not null,
    genetic_quality_value decimal(4, 1) not null,
    estimated_ind         boolean,
    untested_ind          boolean,
    entry_userid          varchar(30) not null,
    entry_timestamp       timestamp not null,
    update_userid         varchar(30) not null,
    update_timestamp      timestamp not null,
    revision_count        int,
    constraint seedlot_parent_tree_gen_qlt_pk
        primary key(seedlot_number, parent_tree_id, genetic_type_code, genetic_worth_code),
    constraint sl_ptree_genqly_sl_ptree_fk
        foreign key(seedlot_number, parent_tree_id) references spar.seedlot_parent_tree(seedlot_number, parent_tree_id)
);
