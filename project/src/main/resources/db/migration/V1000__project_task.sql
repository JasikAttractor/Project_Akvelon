use `project_akvelon`;
CREATE TABLE `projects` (
                         `id` bigint auto_increment NOT NULL,
                         `name` varchar(128) NOT NULL,
                         `start_date` date not null,
                         `end_date` date,
                         `project_status` varchar(32) not null,
                         `priority` int not null,
                         `task` bigint,
                             PRIMARY KEY (`id`)
);
CREATE TABLE `tasks` (
                         `id` bigint auto_increment NOT NULL,
                         `name` varchar(128) NOT NULL,
                         `status` varchar(32) not null,
                         `description` varchar(128) NOT NULL,
                         `priority` int not null,
                         `projectID` bigint not null,
                         PRIMARY KEY (`id`),
                             CONSTRAINT `fk_projectID`
                             foreign key (`projectID`)
                             references `projects` (`id`)
);



