create table spar.seedlot_genetic_worth (
    seedlot_number        varchar(5) not null,
    genetic_worth_code    varchar(3) not null,
    genetic_quality_value decimal(4, 1) not null,
    entry_userid          varchar(30) not null,
    entry_timestamp       timestamp not null,
    update_userid         varchar(30) not null,
    update_timestamp      timestamp not null,
    revision_count        int not null,
    constraint seedlot_genetic_worth_pk
        primary key(seedlot_number, genetic_worth_code),
    constraint seedlot_genet_worth_seedlot_fk
        foreign key(seedlot_number) references spar.seedlot(seedlot_number)
);
