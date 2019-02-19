-- liquibase formatted sql

-- changeset leszrom:1
create table Members
(
  ID        serial,
  Firstname varchar(100),
  Lastname  varchar(100),
  constraint PK_Member primary key (ID)
);

-- changeset leszrom:2
create table Permissions
(
  ID     serial,
  `Role` varchar(100),
  constraint PK_Permission primary key (ID)
);

-- changeset leszrom:3
create table Events
(
  ID          serial,
  Name        varchar(100),
  Description varchar(1000),
  Eventdate   datetime,
  constraint PK_Event primary key (ID)
);

-- changeset leszrom:4
create table Members_Permissions
(
  MemberID     bigint unsigned not null,
  PermissionID bigint unsigned not null,
  constraint PK_Member_Permission primary key (MemberID, PermissionID),
  constraint FK_Member_2 foreign key (MemberID) references Members (ID),
  constraint FK_Permission foreign key (PermissionID) references Permissions (ID)
);

-- changeset leszrom:5
create table Events_Members
(
  EventID  bigint unsigned not null,
  MemberID bigint unsigned not null,
  constraint PK_Event_Member primary key (MemberID, EventID),
  constraint FK_Member foreign key (MemberID) references Members (ID),
  constraint FK_Event foreign key (EventID) references Events (ID)
);

-- changeset leszrom:6
insert into Permissions(`Role`) value ('VIP');
insert into Permissions(`Role`) value ('NON_VIP');
insert into Permissions(`Role`) value ('ADMIN');
commit;

-- changeset leszrom:7
insert into `Events`(`Name`, `Description`, `Eventdate`)
valueS ('Wyd_1', 'Opis_1', '2018-10-23 14:30');
insert into `Events`(`Name`, `Description`, `Eventdate`)
valueS ('Wyd_2', 'Opis_2', '2018-08-03 19:00');
insert into `Events`(`Name`, `Description`, `Eventdate`)
valueS ('Wyd_3', 'Opis_3', '2018-02-14 22:00');

-- changeset leszrom:8
insert into Members(Firstname, Lastname)
values ('Jan', 'Kot');
insert into Members(Firstname, Lastname)
values ('Michał', 'Zając');
insert into Members(Firstname, Lastname)
values ('Ania', 'Chmura');
insert into Members(Firstname, Lastname)
values ('Wiola', 'Żak');
insert into Members(Firstname, Lastname)
values ('Piotr', 'Szpak');

-- changeset leszrom:9
insert into Members_Permissions(MemberID, PermissionID)
values (1, 1);
insert into Members_Permissions(MemberID, PermissionID)
values (2, 2);
insert into Members_Permissions(MemberID, PermissionID)
values (3, 1);
insert into Members_Permissions(MemberID, PermissionID)
values (4, 3);
insert into Members_Permissions(MemberID, PermissionID)
values (4, 1);
insert into Members_Permissions(MemberID, PermissionID)
values (5, 1);

-- changeset leszrom:10
insert into Events_Members(EventID, MemberID)
values (1, 1);
insert into Events_Members(EventID, MemberID)
values (1, 2);
insert into Events_Members(EventID, MemberID)
values (1, 3);
insert into Events_Members(EventID, MemberID)
values (1, 4);
insert into Events_Members(EventID, MemberID)
values (1, 5);
insert into Events_Members(EventID, MemberID)
values (2, 3);
insert into Events_Members(EventID, MemberID)
values (2, 4);
insert into Events_Members(EventID, MemberID)
values (2, 5);
insert into Events_Members(EventID, MemberID)
values (3, 1);
insert into Events_Members(EventID, MemberID)
values (3, 3);
insert into Events_Members(EventID, MemberID)
values (3, 5);
