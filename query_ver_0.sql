create database IQuiz;
use IQUiz;
CREATE TABLE `Category` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`catName` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Course` (
	`idCourse` INT NOT NULL AUTO_INCREMENT,
	`idCategory` INT NOT NULL,
	`expert_id` INT NOT NULL,
	`course_name` varchar(50) NOT NULL,
	`dateStart` DATE NOT NULL,
	PRIMARY KEY (`idCourse`)
);

CREATE TABLE `Subject_detail` (
	`idSub` INT NOT NULL AUTO_INCREMENT,
	`id_course` INT NOT NULL,
	PRIMARY KEY (`idSub`)
);

CREATE TABLE `Subject_overview` (
	`idOverview` INT NOT NULL AUTO_INCREMENT,
	`idSub` INT NOT NULL,
	`status` BOOLEAN NOT NULL,
	`description` longtext NOT NULL,
	`image` blob NOT NULL,
	PRIMARY KEY (`idOverview`)
);

CREATE TABLE `Price_package` (
	`idPrice` INT NOT NULL AUTO_INCREMENT,
	`idSub` INT NOT NULL,
	`packageName` INT NOT NULL,
	`duration` INT NOT NULL,
	`price` INT NOT NULL,
	`salePrice` INT NOT NULL,
	`status` INT NOT NULL,
	PRIMARY KEY (`idPrice`)
);

CREATE TABLE `dimension` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`idSub` INT NOT NULL,
	`dimension` varchar(50) NOT NULL,
	`type` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `UserCourse` (
	`idCourse` INT NOT NULL AUTO_INCREMENT,
	`idUser` INT NOT NULL
);

CREATE TABLE `User` (
	`user_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`Full` varchar(50) NOT NULL,
	`Email` varchar(100) NOT NULL,
	`Gender` BOOLEAN NOT NULL,
	`Mobile` INT NOT NULL,
	`Password` varchar(100) NOT NULL,
	`role_id` INT NOT NULL,
	`course_id` INT NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `Role` (
	`role_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`role` varchar(50) NOT NULL,
	`role_description` mediumtext NOT NULL,
	PRIMARY KEY (`role_id`)
);

CREATE TABLE `UserProfile` (
	`user_profile_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`user_id` INT NOT NULL,
	`avatar` blob NOT NULL,
	`Headline` varchar(50) NOT NULL,
	PRIMARY KEY (`user_profile_id`)
);

CREATE TABLE `Posts` (
	`post_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`user_id` INT NOT NULL,
	`thumbnail` blob NOT NULL,
	`post_category_id` INT NOT NULL,
	`title` varchar(50) NOT NULL,
	`brief_infor` mediumtext NOT NULL,
	`description` longtext NOT NULL,
	`status` varchar(50) NOT NULL,
	`featuring` BOOLEAN NOT NULL,
	PRIMARY KEY (`post_id`)
);

CREATE TABLE `Post_category` (
	`post_category_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`post_category` varchar(50) NOT NULL,
	`post_category_description` mediumtext NOT NULL,
	PRIMARY KEY (`post_category_id`)
);

CREATE TABLE `Slider` (
	`slider_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`user_id` INT NOT NULL,
	`title` varchar(50) NOT NULL,
	`image` blob NOT NULL,
	`backlink` varchar(255) NOT NULL,
	`status` varchar(50) NOT NULL,
	`visibility` BOOLEAN NOT NULL,
	`slider_category_id` INT NOT NULL,
	PRIMARY KEY (`slider_id`)
);

CREATE TABLE `Slider_category` (
	`slider_catgory_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`slider_category` varchar(50) NOT NULL,
	`slider_category_description` mediumtext NOT NULL,
	PRIMARY KEY (`slider_catgory_id`)
);

CREATE TABLE `Lesson_detail` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`lesson_name` varchar(50) NOT NULL,
	`lesson_type` varchar(50) NOT NULL,
	`topic` varchar(50) NOT NULL,
	`lesson_order` INT NOT NULL,
	`video_link` varchar(255) NOT NULL,
	`content` longtext NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Lesson` (
	`idLesson` INT NOT NULL AUTO_INCREMENT,
	`idCourse` INT NOT NULL,
	`lesson` varchar(50) NOT NULL,
	`lesson_order` INT NOT NULL,
	`lesson_type` varchar(50) NOT NULL,
	`status` varchar(50) NOT NULL,
	`action` varchar(50) NOT NULL,
	PRIMARY KEY (`idLesson`)
);

CREATE TABLE `Quiz_detail` (
	`idQuiz` INT NOT NULL AUTO_INCREMENT,
	`id_Lesson` INT NOT NULL,
	PRIMARY KEY (`idQuiz`)
);

CREATE TABLE `Quiz_overview` (
	`idQuiz` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`subject` varchar(50) NOT NULL,
	`level` varchar(50) NOT NULL,
	`duration` INT NOT NULL,
	`pass_rate` INT NOT NULL,
	`quiz_type` varchar(50) NOT NULL,
	`description` longtext NOT NULL,
	PRIMARY KEY (`idQuiz`)
);

CREATE TABLE `Setting` (
	`idQuiz` INT NOT NULL AUTO_INCREMENT,
	`number_of_quiz` INT NOT NULL,
	`quiz_type` varchar(50) NOT NULL,
	PRIMARY KEY (`idQuiz`)
);

CREATE TABLE `Quiz_review` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`subject` varchar(50) NOT NULL,
	`idQuiz` INT NOT NULL,
	`score` INT NOT NULL,
	`answer` BOOLEAN NOT NULL,
	PRIMARY KEY (`user_id`)
);

ALTER TABLE `Course` ADD CONSTRAINT `Course_fk0` FOREIGN KEY (`idCourse`) REFERENCES `Lesson`(`idCourse`);

ALTER TABLE `Course` ADD CONSTRAINT `Course_fk1` FOREIGN KEY (`idCategory`) REFERENCES `Category`(`id`);

ALTER TABLE `Course` ADD CONSTRAINT `Course_fk2` FOREIGN KEY (`expert_id`) REFERENCES `UserCourse`(`idUser`);

ALTER TABLE `Subject_detail` ADD CONSTRAINT `Subject_detail_fk0` FOREIGN KEY (`id_course`) REFERENCES `Course`(`idCourse`);

ALTER TABLE `Subject_overview` ADD CONSTRAINT `Subject_overview_fk0` FOREIGN KEY (`idSub`) REFERENCES `Subject_detail`(`idSub`);

ALTER TABLE `Price_package` ADD CONSTRAINT `Price_package_fk0` FOREIGN KEY (`idSub`) REFERENCES `Subject_detail`(`idSub`);

ALTER TABLE `dimension` ADD CONSTRAINT `dimension_fk0` FOREIGN KEY (`idSub`) REFERENCES `Subject_detail`(`idSub`);

ALTER TABLE `UserCourse` ADD CONSTRAINT `UserCourse_fk0` FOREIGN KEY (`idCourse`) REFERENCES `Course`(`idCourse`);

ALTER TABLE `UserCourse` ADD CONSTRAINT `UserCourse_fk1` FOREIGN KEY (`idUser`) REFERENCES `User`(`user_id`);

ALTER TABLE `User` ADD CONSTRAINT `User_fk0` FOREIGN KEY (`role_id`) REFERENCES `Role`(`role_id`);

ALTER TABLE `User` ADD CONSTRAINT `User_fk1` FOREIGN KEY (`course_id`) REFERENCES `Course`(`idCourse`);

ALTER TABLE `UserProfile` ADD CONSTRAINT `UserProfile_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Posts` ADD CONSTRAINT `Posts_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Posts` ADD CONSTRAINT `Posts_fk1` FOREIGN KEY (`post_category_id`) REFERENCES `Post_category`(`post_category_id`);

ALTER TABLE `Slider` ADD CONSTRAINT `Slider_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Slider` ADD CONSTRAINT `Slider_fk1` FOREIGN KEY (`slider_category_id`) REFERENCES `Slider_category`(`slider_catgory_id`);

ALTER TABLE `Lesson` ADD CONSTRAINT `Lesson_fk0` FOREIGN KEY (`idLesson`) REFERENCES `Lesson_detail`(`id`);

ALTER TABLE `Quiz_detail` ADD CONSTRAINT `Quiz_detail_fk0` FOREIGN KEY (`id_Lesson`) REFERENCES `Lesson`(`idLesson`);

ALTER TABLE `Quiz_overview` ADD CONSTRAINT `Quiz_overview_fk0` FOREIGN KEY (`idQuiz`) REFERENCES `Quiz_detail`(`idQuiz`);

ALTER TABLE `Setting` ADD CONSTRAINT `Setting_fk0` FOREIGN KEY (`idQuiz`) REFERENCES `Quiz_detail`(`idQuiz`);

ALTER TABLE `Quiz_review` ADD CONSTRAINT `Quiz_review_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Quiz_review` ADD CONSTRAINT `Quiz_review_fk1` FOREIGN KEY (`idQuiz`) REFERENCES `Quiz_detail`(`idQuiz`);





















