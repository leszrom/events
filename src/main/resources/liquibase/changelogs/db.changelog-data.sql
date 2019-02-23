-- liquibase formatted sql

-- changeset leszrom:1
insert into Permissions(Role)
  value ('VIP'),('NON_VIP'),('ADMIN');

-- changeset leszrom:
insert into Events(Name, Description, Eventdate)
values ('Wyd_1', 'Opis_1', '2018-10-23 14:30'),
       ('Wyd_2', 'Opis_2', '2018-08-03 19:00'),
       ('Wyd_3', 'Opis_3', '2018-02-14 22:00');

-- changeset leszrom:3
insert into Members(Firstname, Lastname)
values ('Jan', 'Kot'),
       ('Michał', 'Zapata'),
       ('Ania', 'Chara'),
       ('Wiola', 'Paka'),
       ('Piotr', 'Szpak'),
       ('Filip', 'Pies'),
       ('Franek', 'Panek'),
       ('Maja', 'Szaja'),
       ('Wiola', 'Mola'),
       ('Kamil', 'Sroka'),
       ('Jarek', 'Katar'),
       ('Lech', 'Miech'),
       ('Anna', 'Panna'),
       ('Mirka', 'Kilof'),
       ('Olek', 'Kolka');

-- changeset leszrom:4
insert into Members_Permissions(MemberID, PermissionID)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 2),
       (14, 3),
       (15, 2);

-- changeset leszrom:5
insert into Events_Members(EventID, MemberID)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15);