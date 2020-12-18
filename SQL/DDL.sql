CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contact_name` varchar(45) DEFAULT NULL,
  `contact_email` varchar(45) DEFAULT NULL,
  `contact_phone` varchar(45) DEFAULT NULL,
  `img` blob,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `costumers_id_UNIQUE` (`customer_id`),
  UNIQUE KEY `contact_email_UNIQUE` (`contact_email`),
  UNIQUE KEY `contact_phone_UNIQUE` (`contact_phone`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `project_participants` (
  `user_id` int NOT NULL,
  `project_id` int NOT NULL,
  `project_role_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`,`project_id`),
  KEY `project_participants_ibfk_2` (`project_id`),
  KEY `project_participants_ibfk_3` (`project_role_id`),
  CONSTRAINT `project_participants_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `project_participants_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`) ON DELETE CASCADE,
  CONSTRAINT `project_participants_ibfk_3` FOREIGN KEY (`project_role_id`) REFERENCES `project_roles` (`project_role_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `project_roles` (
  `project_role_id` int NOT NULL AUTO_INCREMENT,
  `project_role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`project_role_id`),
  UNIQUE KEY `job_title_id_UNIQUE` (`project_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `projects` (
  `project_id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) NOT NULL,
  `kickoff` date NOT NULL,
  `deadline` date NOT NULL,
  `project_leader_id` int DEFAULT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `idprojects_UNIQUE` (`project_id`),
  UNIQUE KEY `project_name_UNIQUE` (`project_name`),
  KEY `project_leader_id` (`project_leader_id`),
  KEY `projects_ibfk_1` (`customer_id`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `projects_ibfk_2` FOREIGN KEY (`project_leader_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sub_tasks` (
  `task_id` int NOT NULL,
  `sub_task_description` varchar(500) DEFAULT NULL,
  `sub_task_id` int NOT NULL AUTO_INCREMENT,
  `sub_task_name` varchar(45) NOT NULL,
  `sub_task_hours` int DEFAULT NULL,
  PRIMARY KEY (`sub_task_id`),
  KEY `sub_tasks_ibfk_1` (`task_id`),
  CONSTRAINT `sub_tasks_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NOT NULL,
  `task_name` varchar(45) DEFAULT NULL,
  `task_description` varchar(500) DEFAULT NULL,
  `task_leader_id` int DEFAULT NULL,
  `kickoff` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `idtasks_UNIQUE` (`task_id`),
  KEY `tasks_ibfk_1` (`project_id`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `e_mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `is_admin` tinyint DEFAULT '0',
  `img` blob,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  UNIQUE KEY `E-mail_UNIQUE` (`e_mail`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
