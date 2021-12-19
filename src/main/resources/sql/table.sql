create table StatisticsMtr
(
    id               bigint primary key auto_increment,
    url              varchar(255) not null,
    count            bigint       not null default 0,
    createdDateTime  TIMESTAMP    not null default current_timestamp(),
    modifiedDateTime TIMESTAMP    not null default CURRENT_TIMESTAMP(),
    userId           varchar(255)
);

create table StatisticsDtl
(
    id              bigint primary key auto_increment,
    mtrId           bigint    not null,
    createdDateTime TIMESTAMP not null default CURRENT_TIMESTAMP(),
    foreign key (mtrId) references StatisticsMtr (id) ON UPDATE CASCADE
);

