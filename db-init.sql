DROP TABLE IF EXISTS lottery CASCADE;

create table lottery
(
    id     serial
        primary key,
    ticket varchar(6) not null
        unique
        constraint lottery_ticket_check
            check (length((ticket)::text) = 6),
    price  numeric    not null,
    amount integer    not null
);
-- INSERT INTO lottery(ticket, price, amount)
-- VALUES ('123456', 80, 1);
--
-- INSERT INTO lottery(ticket, price, amount)
-- VALUES ('654321', 80, 1);

DROP TABLE IF EXISTS user_ticket CASCADE;

create table user_ticket
(
    id           serial
        primary key,
    user_id      varchar(10) not null
        constraint user_ticket_user_id_check
            check (length((user_id)::text) = 10),
    ticket       varchar(6)  not null
        constraint user_ticket_ticket_check
            check (length((ticket)::text) = 6),
    ticket_price numeric     not null
);