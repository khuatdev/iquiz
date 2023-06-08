-- drop database iquiz;
-- create database iquiz;

use iquiz;

INSERT INTO `iquiz`.`role`
(
`name`,
`status`)
VALUES
('admin',1),
('expert',1),
('sale',1),
('marketing',1),
('sale',1),
('customer',1);
 INSERT INTO `iquiz`.`slider`
(
`title`,
`image`,
`back_link`,
`status`)
VALUES
('Become a Full Stack Software Engineer with The Hottest Technologies Right Now', 'slider1.png', '/subjects/subject-detail?id=5', 1),
('Anyone can get 6.5 IELTS!', 'slider2.png', '/subjects/subject-detail?id=3', 1),
('First Step to become a BA - Statistics and Probability', 'slider3.png', '/subjects/subject-detail?id=2', 1);

INSERT INTO `iquiz`.`lessontype`
(`name`,
`status`)
VALUES
('quiz', 1),
('lession', 1);

INSERT INTO `iquiz`.`dimension`
(
`description`,
`name`,
`status`,
`type`)
VALUES
('The distance between two points in space', 'Business', 1, 'domain'),
('The extent of something from side to side', 'Process', 1, 'domain'),
('How tall something is', 'People', 1, 'domain'),
('The distance between the front and back or top and bottom surfaces of something', 'Initiating', 1, 'domain'),
('A continuous sequence of events that occur in an irreversible succession from the past through the present to the future', 'Planning', 1, 'domain'),
('A measure of the degree of hotness or coldness of an object or substance', 'Executing', 1, 'group');

INSERT INTO `iquiz`.`user`
(
`full_name`,                                                                                                             
`email`,
`gender`,
`mobile`,
`password`,
`role_id`,
`enable`,`avatar`, `last_update_date`, `token` )
VALUES
('Nguyen Anh Quan', 'forwork.quanna@gmail.com', 1, '0379039267', '$2a$12$0Pj0DSsujUeAc32SxWJA4ucmwImNQMqIpSJ2lElmee4acO88Ow6oe', 1,1, 'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Nguyen Van A', 'duongmai.hoamkt76@gmail.com', 1, '012345678', 'ahaha', 1,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Nguyen Thi B', 'dungnpn28@gmail.com', 1, '0987654321', 'bebebe', 1,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. Expert1', 'quannahe176660@gmail.com', 0, '068686868', 'hocgioi', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. Expert2', 'maintnhe176347@gmail.com', 0, '0973600757', 'xinhgai', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. Expert3', 'expert3@gmail.com', 0, '0978779519', 'numberone', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Lung Thi Linh', 'lunglinh2003@gmail.com', 0, '0982712755', 'yeudoi', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr.Expert4', 'ntnm222003@gmail.com', 0, '0978779519', 'numberone', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Khanh Ly', 'ntnm2003@gmail.com', 1, '0978279519', 'numberone', 6,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Phuong Anh', 'jsclub.bannd@gmail.com', 1, '0978379519', 'numberone', 6,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Hoang Ha', 'iamntnm@gmail.com', 1, '0978775519', 'numberone', 6,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mai Linh', 'laitrang143@gmail.com', 1, '0868459509', 'numberone', 6,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Anh Hong', 'lopchungminh@gmail.com', 1, '0978775519', 'numberone', 6,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Tran Thi Anh', 'maiaiai@gmail.com', 1, '0978775519', 'numberone', 6,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02','');

INSERT INTO `iquiz`.`subject`
(
`brief_info`,
`description`,
`thumbnail`,
`title`, `created_time`, `status`,
`dimension_id`,
`owner_id`)
VALUES
  ('Introduction to Calculus', 'Learn the basics of calculus, including limits, derivatives, and integrals.', 'https://example.com/thumbnails/calculus.jpg', 'Calculus', '2023-03-02', 1, 1, 4),
  ('Statistics for Data Analysis', 'Learn the basics of statistical analysis, including probability distributions and hypothesis testing.', 'https://example.com/thumbnails/stats.jpg', 'Statistics', '2023-04-02',  1, 1, 4),
  ('Finance Fundamentals', 'An introduction to financial concepts such as accounting, budgeting, and investment strategies.', 'https://example.com/thumbnails/finance.jpg', 'Finance', '2023-03-31',  1, 2, 4),
  ('Marketing Principles', 'Learn the basics of marketing, including market research, branding, and promotional strategies.', 'https://example.com/thumbnails/marketing.jpg', 'Marketing', '2023-04-04',  1, 2, 4),
  ('Art History', 'A comprehensive study of art throughout history, including major movements, styles, and artists.', 'https://example.com/thumbnails/art_history.jpg', 'Art History', '2023-02-02',  1, 2, 4),
  ('Creative Writing', 'Learn how to write fiction, poetry, and creative nonfiction. Explore different writing styles and techniques.', 'https://example.com/thumbnails/creative_writing.jpg', 'Creative Writing', '2023-02-06',  1, 3, 4),
  ('Public Speaking Skills', 'Develop effective public speaking skills and techniques, including audience analysis, delivery, and persuasion.', 'https://example.com/thumbnails/public_speaking.jpg', 'Public Speaking', '2022-12-02',  1, 4, 5),
  ('Critical Thinking', 'Learn how to analyze and evaluate arguments, make sound decisions, and develop logical reasoning skills.', 'https://example.com/thumbnails/critical_thinking.jpg', 'Critical Thinking', '2022-12-25',  1, 3, 4),
  ('Psychology Basics', 'An introduction to the fundamental concepts of psychology, such as perception, learning, memory, and motivation.', 'https://example.com/thumbnails/psychology.jpg', 'Psychology', '2023-05-02',  1, 3, 5),
  ('Sociology Fundamentals', 'Learn about the social structures and dynamics of human societies, including topics such as culture, inequality, and social change.', 'https://example.com/thumbnails/sociology.jpg', 'Sociology', '2023-01-02',  1, 3, 5),
  ('Political Science Basics', 'An introduction to the study of politics and government, including different political systems, ideologies, and international relations.', 'https://example.com/thumbnails/political_science.jpg', 'Political Science', '2023-03-21',  1, 4, 5),
  ('Philosophy for Beginners', 'Explore the major philosophical questions and ideas, such as ethics, metaphysics, and epistemology.', 'https://example.com/thumbnails/philosophy.jpg', 'Philosophy', '2023-05-02',  1, 4, 5),
  ('World Religions', 'A comparative study of the major world religions, including their beliefs, practices, and historical development.', 'https://example.com/thumbnails/religion.jpg', 'World Religions', '2023-01-20',  1, 4, 5),
  ('Artificial Intelligence Applications', 'Learn about the practical applications of artificial intelligence in fields such as healthcare, finance, and robotics.', 'https://example.com/thumbnails/ai_applications.jpg', 'AI Applications', '2023-03-02',  1, 4, 6),
  ('Database Design and Management', 'A deeper dive into database design and management, covering advanced SQL queries, normalization, and performance tuning.', 'https://example.com/thumbnails/dbms_advanced.jpg', 'Advanced Database Management', '2022-09-02',  1, 5, 6),
  ('Web Development with Frameworks', 'Learn how to build web applications using popular frameworks such as React, Angular, and Vue.', 'https://example.com/thumbnails/frameworks.jpg', 'Web Development with Frameworks','2023-03-20',  1, 5, 6),
  ('Mobile App Development', 'Learn how to develop mobile apps for iOS and Android platforms using native languages or cross-platform tools.', 'https://example.com/thumbnails/mobile_dev.jpg', 'Mobile App Development', '2023-02-20',  1, 5, 6),
  ('Cybersecurity Basics', 'An introduction to cybersecurity principles, including risk assessment, threat modeling, and defense strategies.', 'https://example.com/thumbnails/cybersecurity.jpg', 'Cybersecurity', '2023-04-02',  1, 6, 7),
  ('An introduction to Algebra', 'This subject covers the basics of algebraic equations and their solutions.', 'https://example.com/thumbnails/algebra.jpg', 'Algebra', '2023-04-02',  1, 5, 8),
  ('Introduction to Geometry', 'This subject covers the basics of geometric shapes, angles, and measurements.', 'https://example.com/thumbnails/geometry.jpg', 'Geometry', '2023-03-20',  1, 5, 6),
  ('Trigonometry for Beginners', 'This subject covers the basics of trigonometric functions and their applications in real life.', 'https://example.com/thumbnails/trigonometry.jpg', 'Trigonometry','2023-03-02',  1, 4, 6),
  ('World War II History', 'A comprehensive study of World War II, including key events, causes, and consequences.', 'https://example.com/thumbnails/ww2.jpg', 'World War II', '2023-05-02',  1, 4, 5),
  ('American Revolution', 'A comprehensive study of the American revolution, including key events, causes, and consequences.', 'https://example.com/thumbnails/amrev.jpg', 'American Revolution', '2023-01-02',  1, 3, 5),
  ('Ancient Civilizations: Egypt and Mesopotamia', 'A study of the ancient civilizations of Egypt and Mesopotamia, including their culture, religion, and achievements.', 'https://example.com/thumbnails/ancient_civ.jpg', 'Ancient Civilizations', '2023-05-02',  1, 3, 5),
  ('Goal Setting for Success', 'Learn how to set achievable goals and develop a plan to achieve them.', 'https://example.com/thumbnails/goal_setting.jpg', 'Goal Setting',  '2022-12-02',  1, 4, 5),
  ('Planning Techniques for Better Time Management', 'Learn effective planning techniques to manage your time more efficiently.', 'https://example.com/thumbnails/planning.jpg', 'Planning',  '2022-12-02',  1, 4, 5),
  ('Prioritization Strategies', 'Learn how to prioritize tasks and make informed decisions about how to allocate your time and resources.', 'https://example.com/thumbnails/prioritization.jpg', 'Prioritization', '2023-03-02',  1, 4, 6),
  ('Improving Productivity', 'Learn techniques and strategies to improve your productivity and get more done in less time.', 'https://example.com/thumbnails/productivity.jpg', 'Productivity','2023-03-20',  1, 4, 8),
  ('Human Anatomy Basics', 'Learn the basic anatomy of the human body, including its organs, systems, and functions.', 'https://example.com/thumbnails/human_anatomy.jpg', 'Human Anatomy','2023-04-20',  1, 3, 8),
  ('Introduction to Genetics', 'Learn the basics of genetics, including DNA structure, inheritance patterns, and mutations.', 'https://example.com/thumbnails/genetics.jpg', 'Genetics','2023-02-20',  1, 6, 8),
  ('Ecology and Environmental Science', 'Study the relationship between living organisms and their environment, and learn about topics such as climate change, conservation, and sustainability.', 'https://example.com/thumbnails/ecology.jpg', 'Ecology','2023-04-10',  1, 2, 7),
  ('Artificial Intelligence Fundamentals', 'Learn the basics of artificial intelligence, including machine learning, neural networks, and natural language processing.', 'https://example.com/thumbnails/ai.jpg', 'Artificial Intelligence', '2023-05-03',  1, 5, 7),
  ('Introduction to Database Management', 'Learn the basics of database design, management, and querying using SQL.', 'https://example.com/thumbnails/dbms.jpg', 'Database Management', '2023-02-28',  1, 5, 8),
  ('Web Development Fundamentals', 'Learn the basics of web development, including HTML, CSS, and JavaScript.', 'https://example.com/thumbnails/webdev.jpg', 'Web Development','2023-04-02',  1, 3, 7),
  ('Advanced Algebra', 'A more advanced study of algebraic equations, including higher-degree polynomials and complex numbers.', 'https://example.com/thumbnails/adv_algebra.jpg', 'Advanced Algebra', '2023-01-22',  1, 6, 7),
  ('Geometry in Three Dimensions', 'An introduction to three-dimensional geometry, including vectors, planes, and surfaces.', 'https://example.com/thumbnails/3d_geometry.jpg', 'Three-Dimensional Geometry', '2023-04-02',  1, 4, 8),
  ('Trigonometry in Real Life Applications', 'Learn how trigonometric functions are used in practical applications such as navigation, engineering, and physics.', 'https://example.com/thumbnails/trig_applications.jpg', 'Trigonometry Applications', '2023-02-22',  1, 6, 6);

update iquiz.subject
set featured = 1 where id=1;
update iquiz.subject
set featured = 1 where id=2;
update iquiz.subject
set featured = 1 where id=3;
update iquiz.subject
set featured = 1 where id=4;

update iquiz.subject
set thumbnail = 'subject1_img.png' where id=1;
update iquiz.subject
set thumbnail = 'subject2_img.png' where id=2;
update iquiz.subject
set thumbnail = 'subject3_img.png' where id=3;
update iquiz.subject
set thumbnail = 'subject6_img.png' where id=4;

update iquiz.subject
set brief_info = 'Chemistry Fundamentals'
where id = 8;

update iquiz.subject
set brief_info = 'Exploring the Human Mind'
where id = 13;

update iquiz.subject
set brief_info = 'Understanding Environmental Biology'
where id = 15;

update iquiz.subject
set brief_info = 'Mastering Communication Skills'
where id = 17;

INSERT INTO `iquiz`.`pricepackage`
(
`subject_id`,
`name`,
`access_duration`,
`list_price`,
`description`,
`sale_price`,
`status`)
VALUES
(1, 'Gói truy cập 3 tháng', 3, 500,'available', 320, 1),
(1, 'Gói truy cập 6 tháng', 6, 900,'available', 680, 1),
(1, 'Gói truy cập vô thời hạn', -1, 1500,'submitted', 1000, 1),
(2, 'Gói truy cập 3 tháng', 3, 400,'available' ,300, 1),
(2, 'Gói truy cập 6 tháng', 6, 700, 'available',650, 1),
(2, 'Gói truy cập vô thời hạn', -1, 1000,'submitted', 800, 1),
(3, 'Gói truy cập 3 tháng', 3, 700,'available', 549, 1),
(3, 'Gói truy cập 6 tháng', 6, 1200,'available', 999, 1),
(3, 'Gói truy cập vô thời hạn', -1, 1800,'available', 1000, 1),
(4, 'Gói truy cập 3 tháng', 3, 500,'available', 320, 1),
(4, 'Gói truy cập 6 tháng', 6, 900,'submitted', 680, 1),
(4, 'Gói truy cập vô thời hạn', -1, 1500, 'available',1000, 1),
(5, 'Gói truy cập 3 tháng', 3, 500,'available', 320, 0),
(5, 'Gói truy cập 6 tháng', 6, 900,'submitted', 680, 0),
(5, 'Gói truy cập vô thời hạn', -1, 1500,'available', 1000, 1),
(6, 'Gói truy cập 3 tháng', 3, 500, 'available',320, 1),
(6, 'Gói truy cập 6 tháng', 6, 900, 'submitted',680, 0),
(6, 'Gói truy cập vô thời hạn', -1, 1500, 'available',1000, 0),
(7, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
(7, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
(7, 'Gói truy cập vô thời hạn', -1, 1600, 'submitted', 1200, 1),
(8, 'Gói truy cập 3 tháng', 3, 450, 'available', 350, 1),
(8, 'Gói truy cập 6 tháng', 6, 750, 'available', 550, 1),
(8, 'Gói truy cập vô thời hạn', -1, 1300, 'available', 900, 1),
(9, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(9, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
(9, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1100, 1),
(10, 'Gói truy cập 3 tháng', 3, 600, 'available', 450, 1),
(10, 'Gói truy cập 6 tháng', 6, 1100, 'submitted', 900, 1),
(10, 'Gói truy cập vô thời hạn', -1, 1800, 'available', 1400, 1),
(11, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
(11, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
(11, 'Gói truy cập vô thời hạn', -1, 2000, 'available', 1500, 1),
(12, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(12, 'Gói truy cập 6 tháng', 6, 950, 'available', 700, 1),
(12, 'Gói truy cập vô thời hạn', -1, 1400, 'submitted', 1100, 1),
(13, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
(13, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
(13, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1200, 1),
(14, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
(14, 'Gói truy cập 6 tháng', 6, 800, 'submitted', 650, 1),
(14, 'Gói truy cập vô thời hạn', -1, 1300, 'available', 1000, 1),
(15, 'Gói truy cập 3 tháng', 3, 550, 'available', 400,1),
(15, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
(15, 'Gói truy cập vô thời hạn', -1, 1500, 'submitted', 1200, 1),
(16, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
(16, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
(16, 'Gói truy cập vô thời hạn', -1, 1600, 'available', 1200, 1),
(17, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
(17, 'Gói truy cập 6 tháng', 6, 800, 'available', 600, 1),
(17, 'Gói truy cập vô thời hạn', -1, 1300, 'available', 1000, 1),
(18, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(18, 'Gói truy cập 6 tháng', 6, 950, 'available', 700, 1),
(18, 'Gói truy cập vô thời hạn', -1, 1400, 'submitted', 1100, 1),
(19, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
(19, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
(19, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1200, 1),
(20, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
(20, 'Gói truy cập 6 tháng', 6, 800, 'submitted', 650, 1),
(20, 'Gói truy cập vô thời hạn', -1, 1300, 'available', 1000, 1),
(21, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
(21, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
(21, 'Gói truy cập vô thời hạn', -1, 1600, 'available', 1200, 1),
(22, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(22, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
(22, 'Gói truy cập vô thời hạn', -1, 1500, 'submitted', 1200, 1),
(23, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
(23, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
(23, 'Gói truy cập vô thời hạn', -1, 2000, 'available', 1500, 1),
(24, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(24, 'Gói truy cập 6 tháng', 6, 950, 'available', 700, 1),
(24, 'Gói truy cập vô thời hạn', -1, 1400, 'submitted', 1100, 1),
(25, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
(25, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
(25, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1200, 1),
(26, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
(26, 'Gói truy cập 6 tháng', 6, 800, 'submitted', 650, 1),
(26, 'Gói truy cập vô thời hạn', -1, 1300, 'available', 1000, 1),
(27, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
(27, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
(27, 'Gói truy cập vô thời hạn', -1, 1600, 'available', 1200, 1),
(28, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(28, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
(28, 'Gói truy cập vô thời hạn', -1, 1500, 'submitted', 1200, 1),
(29, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
(29, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
(29, 'Gói truy cập vô thời hạn', -1, 2000, 'available', 1500, 1),
(30, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
(30, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
(30, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1200, 1),
(31, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
(31, 'Gói truy cập 6 tháng', 6, 800, 'available', 600, 1),
(31, 'Gói truy cập vô thời hạn', -1, 1300, 'submitted', 1000, 1),
(32, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
(32, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
(32, 'Gói truy cập vô thời hạn', -1, 1600, 'available', 1200, 1),
(33, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
(33, 'Gói truy cập 6 tháng', 6, 1000, 'submitted', 750, 1),
(33, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1200, 1),
(34, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
(34, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
(34, 'Gói truy cập vô thời hạn', -1, 2000, 'available', 1500, 1),
(35, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
(35, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
(35, 'Gói truy cập vô thời hạn', -1, 1500, 'available', 1200, 1),
(36, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
(36, 'Gói truy cập 6 tháng', 6, 800, 'available', 600, 1),
(36, 'Gói truy cập vô thời hạn', -1, 1300, 'submitted', 1000, 1),
(37, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
(37, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
(37, 'Gói truy cập vô thời hạn', -1, 1600, 'available', 1200, 1);
INSERT INTO `iquiz`.`level`
(
`name`,
`status`)
VALUES
('easy', 1),
('medium', 1),
('difficult', 1),
('challenging', 1);
INSERT INTO `iquiz`.`category`
(`name`, `description`, `status`)
VALUES
('Mathematics', 'This category covers topics related to numbers, operations, and mathematical reasoning.',1),
('History', 'This category covers topics related to past events, people, and societies.',1),
('Time Management', 'This category covers topics related to managing time effectively, including goal setting, planning, prioritization, and productivity.',1),
('Biology', 'This category covers topics related to living organisms and their processes.',1),
('Computer Science', 'This category covers topics related to computing technology and its applications.',1);

INSERT INTO `iquiz`.`subcategory`
(
`name`,
`status`,
`category_id`)
VALUES 
  ('Algebra',1, 1),
  ('Geometry',1, 1),
  ('Trigonometry',1, 1),
  ('World War II',1, 2),
  ('American Revolution',1, 2),
  ('Ancient Civilizations',1, 2),
   ('Goal Setting', 1, 3),
  ('Planning', 1, 3),
  ('Prioritization', 1, 3),
  ('Productivity', 1, 3),
  ('Human Anatomy',1, 4),
  ('Genetics', 1,4),
  ('Ecology', 1,4),
  ('Artificial Intelligence',1, 5),
  ('Database Management',1, 5),
  ('Web Development', 1,5);

 INSERT INTO `iquiz`.`Subject_subcategory` (`subject_id`, `subcategory_id`)
VALUES
(1, 1), (1, 2), (1, 3),
(2, 4), (2, 5), (2, 6),
(3, 7), (3, 8), (3, 9), (3, 10),
(4, 11), (4, 12), (4, 13),
(5, 14), (5, 15), (5, 16),
(6, 7), (6, 8), (6, 9), (6, 10),
(7, 14), (7, 15), (7, 16),
(8, 11), (8, 12), (8, 13),
(9, 11), (9, 12), (9, 13),
(10, 14), (10, 15), (10, 16),
(11, 2), (11, 3),
(12, 1), (12, 3),
(13, 2), (13, 3),
(14, 14), (14, 15), (14, 16),
(15, 11), (15, 12), (15, 13),
(16, 14), (16, 15), (16, 16),
(17, 11),
(18, 2),
(19, 3),
(20, 4),
(21, 5),
(22, 6),
(23, 7),
(24, 8),
(25, 9),
(26, 10),
(27, 11),
(28, 12),
(29, 13),
(30, 14),
(31, 15),
(32, 16),
(33, 14),
(34, 15),
(35, 11),
(36, 3),
(37, 6);

INSERT INTO `iquiz`.`testtype`
(
`name`,
`status`)
VALUES
('Simulation Tests', 1),
('Lesson-Quiz Tests', 1);


INSERT INTO `iquiz`.`lesson`
(`lesson_type_id`,`subject_id`,`dimension_id`,`name`,`order`,`status`,`topic`,`creator_id`,`html_content`,
`duration`,`pass_rate`,
`question_number`,
`test_type_id`,`level_id`,
`video_link`
)
VALUES
(1, 1, 2, 'Calculus Basics Quiz', 1, 1, '', 4, '', '00:45:00', 0.5, 10, 1, 1, ''),
(1, 1, 2, 'Limits and Derivatives Quiz', 2, 1, '', 4, '', '01:15:00', 0.5, 15, 1, 2, ''),
(1, 1, 2, 'Integral Calculus Quiz', 3, 1, '', 4, '', '01:30:00', 0.6, 20, 1, 3, ''),
(1, 2, 2, 'Probability Theory Quiz', 1, 1, '', 4, '', '00:50:00', 0.6, 15, 1, 2, ''),
(1, 2, 2, 'Descriptive Statistics Quiz', 2, 1, '', 4, '', '00:20:00', 0.7, 10, 1, 1, ''),
(1, 2, 2, 'Statistical Inference Quiz', 3, 1, '', 4, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 3, 3, 'Financial Statements Quiz', 1, 1, '', 4, '', '00:30:00', 0.6, 15, 1, 2, ''),
(1, 3, 3, 'Time Value of Money Quiz', 2, 1, '', 4, '', '01:10:00', 0.7, 20, 1, 3, ''),
(1, 3, 3, 'Investment Analysis Quiz', 3, 1, '', 4, '', '01:20:00', 0.7, 25, 1, 4, ''),
(1, 4, 3, 'Product Development Quiz', 1, 1, '', 4, '', '00:40:00', 0.7, 20, 1, 2, ''),
(1, 4, 3, 'Consumer Behavior Quiz', 2, 1, '', 4, '', '00:20:00', 0.8, 10, 1, 1, ''),
(1, 4, 3, 'Marketing Strategy Quiz', 3, 1, '', 4, '', '01:30:00', 0.8, 30, 1, 4, ''),
(1, 5, 3, 'Ancient Art Quiz', 1, 1, '', 4, '', '00:50:00', 0.8, 25, 1, 3, ''),
(1, 5, 3, 'Renaissance Art Quiz', 2, 1, '', 4, '', '01:15:00', 0.9, 30, 1, 4, ''),
(1, 5, 3, 'Modern Art Quiz', 3, 1, '', 4, '', '00:30:00', 0.6, 15, 1, 2, ''),
(1, 6, 3, 'World Geography Quiz 1', 1, 1, '', 4, '', '00:45:00', 0.7, 20, 1, 2, ''),
(1, 6, 3, 'World Geography Quiz 2', 2, 1, '', 4, '', '01:00:00', 0.7, 25, 1, 3, ''),
(1, 6, 3, 'World Geography Quiz 3', 3, 1, '', 4, '', '01:30:00', 0.8, 30, 1, 2,''),
(1, 7, 3, 'Computer Networks Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 7, 3, 'Operating Systems Quiz', 2, 1, '', 5, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 7, 3, 'Database Management Quiz', 3, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 8, 3, 'Basic Chemistry Quiz', 1, 1, '', 4, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 8, 3, 'Organic Chemistry Quiz', 2, 1, '', 4, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 8, 3, 'Biochemistry Quiz', 3, 1, '', 4, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 9, 3, 'World History Quiz 1', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 9, 3, 'World History Quiz 2', 2, 1, '', 5, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 9, 3, 'Modern History Quiz', 3, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 10, 3, 'Microeconomics Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 10, 3, 'Macroeconomics Quiz', 2, 1, '', 5, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 10, 3, 'International Trade Quiz', 3, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 11, 3, 'Foundations of Ethics Quiz', 1, 1, '', 5, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 11, 3, 'Applied Ethics Quiz', 2, 1, '', 5, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 11, 3, 'Metaethics Quiz', 3, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 12, 3, 'Intro to Law Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 12, 3, 'Business Law Quiz', 2, 1, '',5, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 12, 3, 'Intellectual Property Law Quiz', 3, 1, '',5, '', '01:30:00', 0.8, 35, 1, 3,''),
(1, 13, 3, 'Intro to Psychology Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 13, 3, 'Social Psychology Quiz', 2, 1, '', 5, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 13, 3, 'Cognitive Psychology Quiz', 3, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 14, 3, 'Political Science Quiz', 1, 1, '', 6, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 14, 3, 'Comparative Politics Quiz', 2, 1, '',6, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 14, 3, 'International Relations Quiz', 3, 1, '', 6, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 15, 3, 'Environmental Science Quiz', 1, 1, '', 6, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 15, 3, 'Ecology Quiz', 2, 1, '', 6, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 15, 3, 'Conservation Biology Quiz', 3, 1, '', 6, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 16, 3, 'Intro to Philosophy Quiz', 1, 1, '', 6, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 16, 3, 'Ethics Quiz', 2, 1, '', 6, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 16, 3, 'Logic and Critical Thinking Quiz', 3, 1, '', 6, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 17, 3, 'Public Speaking Quiz', 1, 1, '', 6, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 17, 3, 'Interpersonal Communication Quiz', 2, 1, '', 6, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 17, 3, 'Intercultural Communication Quiz', 3, 1, '', 6, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 18, 3, 'Anthropology Quiz', 1, 1, '', 7, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 18, 3, 'Sociology Quiz', 2, 1, '', 7, '', '01:20:00', 0.8, 30, 1, 4, ''),
(1, 18, 3, 'Cultural Studies Quiz', 3, 1, '', 7, '', '01:30:00', 0.8, 35, 1, 2, ''),
(1, 19, 3, 'Intro to Music Quiz', 1, 1, '', 8, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 19, 3, 'Music Theory Quiz', 2, 1, '', 8, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 20, 3, 'Intro to Programming Quiz', 1, 1, '', 6, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 20, 3, 'Data Structures Quiz', 2, 1, '', 6, '', '01:30:00', 0.8, 35, 1, 1, ''),
(1, 21, 3, 'Intro to Marketing Quiz', 1, 1, '', 6, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 21, 3, 'Consumer Behavior Quiz', 2, 1, '', 6, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 22, 3, 'Intro to Statistics Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 22, 3, 'Probability Quiz', 2, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 3, ''),
(1, 23, 3, 'Intro to Art Quiz', 1, 1, '', 5, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 23, 3, 'Art History Quiz', 2, 1, '', 5, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 24, 3, 'Intro to Astronomy Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 24, 3, 'Astrophysics Quiz', 2, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 1, ''),
(1, 25, 3, 'Intro to Linguistics Quiz', 1, 1, '', 5, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 25, 3, 'Phonetics and Phonology Quiz', 2, 1, '', 5, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 26, 3, 'Intro to Chemistry Quiz', 1, 1, '', 5, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 26, 3, 'Organic Chemistry Quiz', 2, 1, '', 5, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 27, 3, 'Intro to Economics Quiz', 1, 1, '', 6, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 27, 3, 'Microeconomics Quiz', 2, 1, '', 6, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 28, 3, 'Intro to Geology Quiz', 1, 1, '', 8, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 28, 3, 'Mineralogy Quiz', 2, 1, '', 8, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 29, 3, 'Intro to Computer Science Quiz', 1, 1, '', 8, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 29, 3, 'Algorithms and Complexity Quiz', 2, 1, '', 8, '', '01:30:00', 0.8, 35, 1, 1, ''),
(1, 30, 3, 'Intro to World History Quiz', 1, 1, '', 8, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 30, 3, 'Modern World History Quiz', 2, 1, '', 8, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 31, 3, 'Intro to Astronomy Quiz', 1, 1, '', 7, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 31, 3, 'Planetary Science Quiz', 2, 1, '', 7, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 32, 3, 'Intro to Psychology Quiz', 1, 1, '', 7, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 32, 3, 'Abnormal Psychology Quiz', 2, 1, '', 7, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 33, 3, 'Intro to Sociology Quiz', 1, 1, '', 8, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 33, 3, 'Social Movements Quiz', 2, 1, '', 8, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 34, 3, 'Intro to Philosophy Quiz', 1, 1, '', 7, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 35, 3, 'Intro to Political Science Quiz', 1, 1, '', 7, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 35, 3, 'International Relations Quiz', 2, 1, '', 7, '', '01:15:00', 0.7, 25, 1, 3, ''),
(1, 36, 3, 'Intro to Environmental Science Quiz', 1, 1, '', 8, '', '01:00:00', 0.7, 20, 1, 3, ''),
(1, 36, 3, 'Climate Change Quiz', 2, 1, '', 8, '', '01:30:00', 0.8, 35, 1, 4, ''),
(1, 37, 3, 'Intro to Linguistics Quiz', 1, 1, '', 6, '', '00:45:00', 0.6, 15, 1, 2, ''),
(1, 37, 3, 'Semantics and Pragmatics Quiz', 2, 1, '', 6, '', '01:15:00', 0.7, 25, 1, 3, '');

update iquiz.lesson
set question_number = 10 where id = 1;

INSERT INTO `iquiz`.`registrationstatus`
(
`name`)
VALUES
('active'),
('inactive');


INSERT INTO `iquiz`.`user_subject`
(`user_id`,
`subject_id`,
`price_package_id`,
`registration_time`,
`valid_from`,
`registration_status_id`)
VALUES
(9, 3, 7, '2023-05-05', '2023-05-12', 1),
(9, 4, 12, '2023-04-05', '2023-04-12', 1),
(9, 1, 26, '2023-05-02', '2023-05-09', 1),
(9, 12, 33, '2023-05-05', '2023-05-12', 1),
(9, 18, 54, '2023-01-05', '2023-01-12', 1),
(10, 1, 6, '2023-03-05', '2023-03-12', 1),
(10, 3, 17, '2023-04-30', '2023-05-07', 1),
(10, 8, 22, '2023-05-05', '2023-05-12', 1),
(10, 13, 39, '2023-05-05', '2023-05-12', 1),
(10, 15, 43, '2023-05-05', '2023-05-12', 1),
(10, 17, 50, '2023-05-05', '2023-05-12', 1),
(11, 1, 37, '2023-05-05', '2023-05-12', 1),
(11, 14, 41, '2023-05-05', '2023-05-12', 1),
(12, 3, 28, '2023-05-05', '2023-05-12', 1),
(12, 11, 32, '2023-05-05', '2023-05-12', 1);

INSERT INTO `iquiz`.`question`
(`dimension_id`,
`question_level_id`,
`audio`,
`content`,
`image`,
`status`,
`video`)
VALUES
(3, 2, '','What is the limit of f(x) = (x^2 - 2x - 3)/(x - 3) as x approaches 3?' ,'', 1, ''),
(3, 2, '','What is the derivative of f(x) = 4x^3 - 5x^2 + 2x - 1 using the power rule?' ,'', 1, ''),
(3, 2, '','Find the integral of f(x) = 2x + 1 dx.' ,'', 1, ''),
(3, 2, '','What is the second derivative of f(x) = 3x^2 + 2x - 1?' ,'', 1, ''),
(3, 2, '','Evaluate the following limit: lim x->-infinity (3x^3 + 4x^2 - 2x)/(x^3 - 2x^2 + x)' ,'', 1, ''),
(3, 2, '','Find the critical points of the function f(x) = 2x^3 - 3x^2 + 6x + 1.' ,'', 1, ''),
(3, 2, '','What is the antiderivative of f(x) = sin(x)?' ,'', 1, ''),
(3, 2, '','Find the derivative of f(x) = ln(x^2 + 1).' ,'', 1, ''),
(3, 2, '','Evaluate the integral of f(x) = e^x dx.' ,'', 1, ''),
(3, 2, '','Find the limit of f(x) = (sin(x) - x)/(x^3) as x approaches 0.' ,'', 1, ''),
(2, 2, '','Which financial statement shows the companys revenues and expenses during a specific period?' ,'', 1, ''),
(2, 2, '','What is the purpose of the balance sheet?' ,'', 1, ''),
(2, 2, '','Which financial statement shows a companys cash inflows and outflows over a specific period of time?' ,'', 1, ''),
(2, 2, '','What is the formula for calculating net income?' ,'', 1, ''),
(2, 2, '','Which financial statement shows how a companys retained earnings have changed over a specific period?' ,'', 1, ''),
(2, 2, '','What is the formula for calculating total assets?' ,'', 1, ''),
(2, 2, '','Which financial statement shows a companys debt and equity financing activities?' ,'', 1, ''),
(2, 2, '','What is the formula for calculating gross profit?' ,'', 1, ''),
(2, 2, '','Which financial statement shows a companys revenues, expenses, and net income over a specific period of time?' ,'', 1, ''),
(2, 2, '','What is the formula for calculating current ratio?' ,'', 1, ''),
(2, 2, '','Which financial statement shows how a companys assets are financed (with debt or equity)?' ,'', 1, ''),
(2, 2, '','What is the formula for calculating return on equity?' ,'', 1, ''),
(2, 2, '','Which financial statement shows the cash balance at the beginning and end of a specific period?' ,'', 1, ''),
(2, 2, '','What is the formula for calculating debt-to-equity ratio?' ,'', 1, ''),
(2, 2, '','Which financial statement shows a companys dividends paid to shareholders over a specific period?' ,'', 1, '');

INSERT INTO `iquiz`.`answer`
(
`checking`,
`explanation`,
`status`,
`question_id`)
VALUES
( 1, '6', 1, 1),
( 0, '0', 1, 1),
( 0, '-6', 1, 1),
( 0, 'undefined', 1, 1),
( 1, '12x^2 - 10x + 2', 1, 2),
( 0, '12x^2 - 10x', 1, 2),
( 0, '12x^4 - 10x^3 + 2x^2 - x', 1, 2),
( 0, '12x^4 - 10x^3 + 2x^2', 1, 2),
( 0, 'x^2 + x + C', 1, 3),
( 0, 'x^2 + 2x + C', 1, 3),
( 1, 'x^2/2 + x + C', 1, 3),
( 0, 'x^2/2 + 2x + C', 1, 3),
( 1, '6x + 2', 1, 4),
( 0, '6x', 1, 4),
( 0, '6', 1, 4),
( 0, '0', 1, 4),
( 0, '-3', 1, 5),
( 1, '3', 1, 5),
( 0, '-4', 1, 5),
( 0, '4', 1, 5),
( 1, 'x = -1, x = 1/2', 1, 6),
( 0, 'x = -1/2, x = 1', 1, 6),
( 0, 'x = -1, x = 3/2', 1, 6),
( 0, 'x = -3/2, x = 1', 1, 6),
( 1, '-cos(x)', 1, 7),
( 0, 'cos(x)', 1, 7),
( 0, '-sin(x)', 1, 7),
( 0, 'sin(x)', 1, 7),
( 1, '(2x)/(x^2 + 1)', 1, 8),
( 0, '(2x)/(2x^2 + 1)', 1, 8),
( 0, '(2x^2)/(x^2 + 1)', 1, 8),
( 0, '(2)/(x^2 + 1)', 1, 8),
( 1, 'e^x + C', 1, 9),
( 0, 'e^x^2 / 2 + C', 1, 9),
( 0, ' ln|x| + C', 1, 9),
( 0, '1/x + C', 1, 9),
( 0, '1/6', 1, 10),
( 1, '-1/6', 1, 10),
( 0, '1/3', 1, 10),
( 0, '-1/3', 1, 10) ,
( 0, 'Balance Sheet', 1, 11),
( 0, 'Cash Flow Statement', 1, 11),
( 1, 'Income Statement', 1, 11),
( 0, 'Retained Earnings Statement', 1, 11),
( 0, 'To show a companys revenues and expenses over a period of time', 1, 12),
( 0, 'To show the amount of cash flow in and out of a company', 1, 12),
( 1, 'To show a companys assets, liabilities, and equity at a specific point in time', 1, 12),
( 0, 'To show the difference between a companys current assets and current liabilities', 1, 12),
( 0, 'Balance Sheet', 1, 13),
( 1, 'Cash Flow Statement', 1, 13),
( 0, 'Income Statement', 1, 13),
( 0, 'Retained Earnings Statement', 1, 13),
( 0, 'Revenues + Expenses = Net Income', 1, 14),
( 0, 'Assets - Liabilities = Net Income', 1, 14),
( 0, 'Gross Profit - Operating Expenses = Net Income', 1, 14),
( 1, 'Total Revenue - Total Expenses = Net Income', 1, 14),
( 0, 'Balance Sheet', 1, 15),
( 0, 'Cash Flow Statement', 1, 15),
( 0, 'Income Statement', 1, 15),
( 1, 'Retained Earnings Statement', 1, 15),
( 0, 'Total Revenue - Operating Expenses', 1, 16),
( 1, 'Total Revenue - Cost of Goods Sold', 1, 16),
( 0, 'Gross Profit Margin x Total Revenue', 1, 16),
( 0, 'Net Income - Dividends', 1, 16),
( 0, 'Balance Sheet', 1, 17),
( 1, 'Cash Flow Statement', 1, 17),
( 0, 'Income Statement', 1, 17),
( 0, 'Retained Earnings Statement', 1, 17),
( 0, 'Total Revenue - Operating Expenses', 1, 18),
( 1, 'Total Revenue - Cost of Goods Sold', 1, 18),
( 0, 'Gross Profit Margin x Total Revenue', 1, 18),
( 0, 'Net Income - Dividends', 1, 18),
( 0, 'Balance Sheet', 1, 19),
( 0, 'Cash Flow Statement', 1, 19),
( 1, 'Income Statement', 1, 19),
( 0, 'Retained Earnings Statement', 1, 19),
( 1, 'Current Assets / Current Liabilities', 1, 20),
( 0, 'Total Assets / Total Liabilities', 1, 20),
( 0, 'Total Equity / Total Liabilities', 1, 20),
( 0, 'Net Income / Total Revenue', 1, 20),
( 1, 'Balance Sheet', 1, 21),
( 0, 'Cash Flow Statement', 1, 21),
( 0, 'Income Statement', 1, 21),
( 0, 'Retained Earnings Statement', 1, 21),
( 0, 'Net Income / Total Assets', 1, 22),
( 0, 'Net Income / Total Equity', 1, 22),
( 0, 'Gross Profit / Total Assets', 1, 22),
( 0, 'Gross Profit / Total Equity', 1, 22),
( 0, 'Balance Sheet', 1, 23),
( 1, 'Cash Flow Statement', 1, 23),
( 0, 'Income Statement', 1, 23),
( 0, 'Retained Earnings Statement', 1, 23),
( 1, 'Total Liabilities / Total Equity', 1, 24),
( 0, 'Current Assets / Current Liabilities', 1, 24),
( 0, 'Gross Profit / Total Equity', 1, 24),
( 0, 'Net Income / Total Equity', 1, 24),
( 0, 'Balance Sheet', 1, 25),
( 0, 'Cash Flow Statement', 1, 25),
( 0, 'Income Statement', 1, 25),
( 1, 'Retained Earnings Statement', 1, 25) ;
INSERT INTO `iquiz`.`question_subcategory`
(`question_id`,
`subcategory_id`)
VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2), (2, 3),
(3, 1), (3, 2), (3, 3),
(4, 1), (4, 2), (4, 3),
(5, 1), (5, 2), (5, 3),
(6, 1), (6, 2), (6, 3),
(7, 1), (7, 2), (7, 3),
(8, 1), (8, 2), (8, 3),
(9, 1), (9, 2), (9, 3),
(10, 1), (10, 2), (10, 3),
(11, 7), (11,8),
(12, 7), (12,8),
(13, 7), (13,8),
(14, 7), (14,8),
(15, 7), (15,8),
(16, 7), (16,8),
(25, 7), (25,8),
(17, 7), (17,8),
(18, 7), (18,8),
(19, 7), (19,8),
(20, 7), (20,8),
(21, 7), (21,8),
(22, 7), (22,8),
(23, 7), (23,8),
(24, 7), (24,8)
;
INSERT INTO `iquiz`.`lesson_question`
(`lesson_id`,
`question_id`)
VALUES
(1, 2), 
(1, 1), 
(1, 3), 
(1, 4), 
(1, 5), 
(1, 6), 
(1, 7), 
(1, 8), 
(1, 9), 
(1, 10), 
(7, 11), 
(7, 12), 
(7, 13), 
(7, 14), 
(7, 15), 
(7, 16), 
(7, 17), 
(7, 18), 
(7, 19), 
(7, 20), 
(7, 21), 
(7, 22), 
(7, 23), 
(7, 24), 
(7, 25);
 
INSERT INTO `iquiz`.`lesson_subcategory`
(`lesson_id`,
`subcategory_id`)
VALUES
(1,1 );


INSERT INTO `iquiz`.`quizreview`
(`id`,
`date_taken`,
`duration`,
`lesson_id`,
`user_id`)
VALUES
(1, '2023-06-01', '00:05:03' , 7, 9),
(2, '2023-06-02', '00:03:03' , 7, 10),
(3, '2023-06-02', '00:07:30' , 7, 12),
(4, '2023-06-06', '00:06:03' , 1, 10),
(5, '2023-06-06', '00:06:03' , 1, 9);

update iquiz.quizreview
set user_id = 10 where id=5;

update iquiz.quizreview
set user_id = 9 where id=2;

update iquiz.quizreview
set user_id = 10 where id=1;

update iquiz.quizreview
set user_id = 9 where id=5;

INSERT INTO `iquiz`.`quizreview_question`
(`bookmark`,
`status`,
`user_answer`,
`quizreview_id`,
`question_id`, 
`is_correct`)
VALUES
(0, 1, '6', 5, 1, 1),
(1, 1, '12x^2 - 10x + 2', 5, 2, 1),
(0, 1,'x^2 + x + C' , 5, 3, 0),
(1, 1, '6x' , 5, 4, 0),
(1, 1, '3', 5, 5, 1),
(0, 1, 'x = -1, x = 1/2', 5, 6, 1),
(1, 1,'-sin(x)' , 5, 7, 0),
(0, 1, '(2x)/(x^2 + 1)' , 5, 8, 1),
(0, 1, 'e^x + C', 5, 9, 1),
(0, 1, '-1/6', 5, 10, 1),
(0, 1,'Income Statement' , 1, 11, 1),
(0, 1, 'To show a companys assets, liabilities, and equity at a specific point in time', 1, 12, 1),
(0, 1, 'Cash Flow Statement', 1, 13, 1),
(0, 1, 'Assets - Liabilities = Net Income' , 1, 14, 0),
(1, 1, 'Income Statement' , 1, 15, 0),
(0, 1, 'Total Revenue - Cost of Goods Sold', 1, 16, 1),
(0, 1, 'Cash Flow Statement', 1, 17, 1),
(0, 1, 'Total Revenue - Cost of Goods Sold', 1, 18, 1),
(1, 1,'Income Statement' , 1, 19, 1),
(0, 1, 'Current Assets / Current Liabilities' , 1, 20, 1),
(1, 1, 'Balance Sheet', 1, 21, 1),
(0, 1, 'Net Income / Total Equity', 1, 22, 0),
(0, 1, 'Cash Flow Statement', 1, 23, 1),
(1, 1,'Total Liabilities / Total Equity' , 1, 24, 1),
(0, 1, 'Retained Earnings Statement', 1, 25, 1),
(0, 1, '6', 4, 1, 1),
(1, 1, '12x^2 - 10x + 2', 4, 2, 1),
(0, 1,'x^2 + x + C' , 4, 3, 0),
(1, 1, '6x' , 4, 4, 0),
(0, 1,'3' , 4, 5, 1),
(0, 1, 'x = -1, x = 1/2', 4, 6, 1),
(1, 1,'-sin(x)' , 4, 7, 0),
(0, 1, '(2x)/(x^2 + 1)' , 4, 8, 1),
(1, 1, 'e^x + C', 4, 9, 1),
(0, 1, '-1/6', 4, 10, 1);
-- (0, 1,'Income Statement' , 4, 11, 1),
-- (0, 1, 'To show a companys assets, liabilities, and equity at a specific point in time', 4, 12, 1),
-- (0, 1, 'Cash Flow Statement', 4, 13, 1),
-- (0, 1, 'Assets - Liabilities = Net Income' , 4, 14, 0),
-- (1, 1, 'Income Statement' , 4, 15, 0),
-- (0, 1, 'Total Revenue - Cost of Goods Sold', 4, 16, 1),
-- (0, 1, 'Cash Flow Statement', 4, 17, 1),
-- (0, 1, 'Total Revenue - Cost of Goods Sold', 4, 18, 1),
-- (1, 1, 'Income Statement' , 4, 19, 1),
-- (0, 1, 'Current Assets / Current Liabilities' , 4, 20, 1),
-- (1, 1, 'Balance Sheet', 4, 21, 1),
-- (0, 1, 'Net Income / Total Equity', 4, 22, 0),
-- (0, 1, 'Cash Flow Statement', 4, 23, 1),
-- (1, 1,'Total Liabilities / Total Equity' , 4, 24, 1),
-- (0, 1, 'Retained Earnings Statement', 4, 25, 1);

-- drop table iquiz.quizreview_question;

INSERT INTO `iquiz`.`blogcategory`
(`name`, `status`)
VALUES
('Technology', true),
('Travel', true),
('Food', false);

INSERT INTO `iquiz`.`blog`
(`brief_info`, `content`, `featuring`, `last_updated`, `status`, `thumbnail`, `title`, `user_id`)
VALUES
('Brief info about technology', 'Content about technology', 1, '2023-06-05', 'uploaded', 'post4_thumbnail.png', 'Technology Blog', 14),
('Brief info about travel', 'Content about travel', 1, '2023-06-05', 'uploaded', 'post5_thumbnail.png', 'Travel Blog', 13),
('Brief info about food', 'Content about food', 1, '2023-06-05', 'uploaded', 'post3_thumbnail.png', 'Food Blog', 14),
('Brief info about education', 'Content about education', 1, '2023-06-05', 'uploaded', 'post2_thumbnail.png', 'Education Blog', 14);

-- Insert data into blog_category table
INSERT INTO `iquiz`.`blog_category`
(`blog_id`, `category_id`)
VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(2, 1),
(3, 3);


INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`role_id`)
VALUES
(1,true,'User Roles','admin',1),
(2,true,'User Roles','expert',2),
(3,true,'User Roles','sale',3),
(4,true,'User Roles','marketing',4),
(5,true,'User Roles','sale',5),
(6,true,'User Roles','customer',6);

INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`blogcategory_id`)
VALUES
(1,true,'Post Categories','Technology',1),
(2,true,'Post Categories','Travel',2),
(3,false,'Post Categories','Food',3);

INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`category_id`)
VALUES
(1,true,'Subject Categories','Mathematics',1),
(2,true,'Subject Categories','History',2),
(3,true,'Subject Categories','Time Management',3),
(4,true,'Subject Categories','Biology',4),
(5,true,'Subject Categories','Computer Science',5);
INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`testtype_id`)
VALUES
(1,true,'Test Types','Simulation Tests',1),
(2,true,'Test Types','Lesson-Quiz Tests',2);


INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`level_id`)
VALUES
(1,true,'Question Levels','easy',1),
(2,true,'Question Levels','medium',2),
(3,true,'Question Levels','difficult',3),
(4,true,'Question Levels','challenging',4);

INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`lessontype_id`)
VALUES
(1,true,'Lesson Types','quiz',1),
(2,true,'Lesson Types','lession',2);

INSERT INTO `iquiz`.`settings`
(
`order`,
`status`,
`type`,
`value`,
`dimension_id`)
VALUES
(1,true,'Subject Dimension','Business',1),
(2,true,'Subject Dimension','Process',2),
(3,true,'Subject Dimensions','People',3),
(4,true,'Subject Dimension','Initiating',4),
(5,true,'Subject Dimension','Planning',5),
(6,true,'Subject Dimension','Executing',6);


