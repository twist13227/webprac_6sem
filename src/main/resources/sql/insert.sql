INSERT INTO relationship.residence(country, town, address, description) VALUES
	('Россия', 'Москва', 'улица Пушкина, дом 5, кв. 50', 'Квартира в спальном районе Москвы'),
	('Россия', 'Санкт-Петербург', 'улица Лермонтова, дом 53, кв. 10', 'Квартира в спальном районе Санкт-Петербурга'),
	('Россия', 'Омск', 'улица Булгакова, дом 10, кв. 23', 'Квартира в центре Омска'),
	('Россия', 'Новосибирск', 'улица Чехова, дом 12, кв. 47', 'Квартира в спальном районе Новосибирска'),
	('Россия', 'Обнинск', 'улица Ленина, дом 20, кв. 36', 'Квартира в панельке в Обнинске'),
	('Россия', 'Одинцово', 'улица Гоголя, дом 13, ', 'Частный дом в Подмосковье'),
	('Россия', 'Москва', 'Ломоносовский проспеpatronymicкт, дом 27, корпус 11', 'Общежитие ДСЛ МГУ'),
	('Россия', 'Жуков', 'улица Зеленая, дом 8, ', 'Частный дом в Калужской области'),
	('Россия', 'Пушкино', 'улица Красная, дом 4, ', 'Частный дом в Ленинградской области'),
	('Россия', 'Краснознаменск', 'улица Маркса, дом 37, ', 'Частный дом в Подмосковье');
	
	
INSERT INTO relationship.person(surname, name, patronymic, gender, birth_date, characteristics) VALUES
		('Тютиков', 'Эдуард', 'Леонидович', 'м', DATE '1975-06-05', 'Работает программистом в Яндексе. Домосед'),
        ('Курылкина', 'Карина', 'Альбертовна', 'ж', DATE '1970-11-08', 'Любящая жена и мать, работает вместе с мужем в технической академии Росатома'),
        ('Курылкин', 'Геннадий', 'Ярославович', 'м', DATE '1968-08-02',  'Семьянин, работает вместе с женой в технической академии Росатома');
        
INSERT INTO relationship.person(surname, name, patronymic, gender, birth_date, death_date, characteristics) VALUES       
        ('Грачевская', 'Анна', 'Антоновна', 'ж', DATE '1940-06-22', DATE '2020-12-10', 'Одинокая женищна, работала няней');
        
INSERT INTO relationship.person(surname, name, patronymic, gender, birth_date, characteristics) VALUES        
	('Бабина', 'Марина', 'Казимировна', 'ж', DATE '1960-03-09', 'Повар в пекарне, любит мужа несмотря на его пьянки'),
	('Бабин', 'Вадим', 'Олегович', 'м', DATE '1962-02-22', 'Алкоголик, работающий на заводе'),
	('Носова', 'Римма', 'Олеговна', 'ж', DATE '1985-04-18', 'Жена бизнесмена, домохозяйка'),
	('Носов', 'Тихон', 'Данилович', 'м', DATE '1960-09-15', 'Бизнесмен, владеет сетью кофеен'),
	('Гавшина', 'Юлиана', 'Васильевна', 'ж', DATE '1990-07-15', 'Индивидуальный предприниматель, продаёт куртки на вайлдберриз'),
	('Курылкин', 'Эмиль', 'Геннадьевич', 'м', DATE '2001-10-09', 'Студент, учится на ВМК МГУ');


INSERT INTO relationship.person_residence (person_id, residence_id) VALUES
	(1, 1),
	(2, 2),
	(3, 2),
	(4, 3),
	(5, 4),
	(6, 4),
	(7, 5),
	(8, 5),
	(9, 6),
	(10, 7),
	(1, 10),
	(2, 9),
	(3, 9),
	(7, 8),
	(8, 8);
INSERT INTO relationship.relation(first_person_id, second_person_id, relation_type, information) VALUES
	(2, 3, 'spouse', 'Дата женитьбы: 2000-03-19'),
	(3, 2, 'spouse', 'Дата женитьбы: 2000-03-19'),
	(2, 10, 'parent', 'Единственный сын'),
	(3, 10, 'parent', 'Единственный сын'),
	(10, 2, 'child', 'Сильно любим родителями'),
	(10, 3, 'child', 'Сильно любим родителями'),
	(4, 5, 'parent', 'Родила от мужчины, который от нее сбежал'),
	(5, 4, 'child', 'Видела только маму'),
	(5, 6, 'spouse', 'Дата женитьбы: 1985-07-21'),
	(6, 5, 'spouse', 'Дата женитьбы: 1985-07-21'),
	(7, 8, 'spouse', 'Дата женитьбы: 2010-12-03'),
	(7, 8, 'spouse', 'Дата женитьбы: 2010-12-03'),
	(7, 9, 'adoptive parent', 'Не может иметь своих детей, поэтому взял дочь из детдома'),
	(9, 7, 'adopted child', 'Любит приёмного отца, своих родителей не знает');
	
	
	