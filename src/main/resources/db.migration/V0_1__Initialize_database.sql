create table team
(
    id          serial,
    name        varchar,
    primary key (id)
);

create table player
(
    id          serial,
    name        varchar,
    number      int,
    id_team     int,
    primary key (id),
    foreign key (id_team) references team(id)
);

create table play_against
(
    id                  serial,
    datetime            timestamp,
    stadium             varchar,
    id_opponent_team    int,
    id_adversary_team   int,
    primary key (id),
    foreign key (id_opponent_team) references team(id),
    foreign key (id_adversary_team) references team(id)
);

create table sponsor
(
    id          serial,
    name        varchar,
    primary key (id)
);

create table team_sponsor
(
    id          serial,ds
    id_team     int,
    id_sponsor  int,
    foreign key (id_team) references team(id),
    foreign key (id_sponsor) references sponsor(id)
);