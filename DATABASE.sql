CREATE TABLE exam (
	id_exam INTEGER NOT NULL AUTO_INCREMENT,
    _name VARCHAR(100) NOT NULL,
    _description VARCHAR(150) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    start_at TIMESTAMP NULL,
    end_at TIMESTAMP NULL,
    time_limit INTEGER NULL,
    state INTEGER NOT NULL,
    id_course VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_exam)
);

CREATE TABLE grade (
	id_grade INTEGER NOT NULL AUTO_INCREMENT,
    id_student INTEGER NOT NULL,
    id_exam INTEGER NOT NULL,
    _value DECIMAL(5,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY(id_grade),
    CONSTRAINT fk_exam_grade
		FOREIGN KEY(id_exam)
			REFERENCES exam(id_exam)
);

CREATE TABLE examXstudent(
	id_examXstudent INTEGER NOT NULL AUTO_INCREMENT,
	id_student INTEGER NOT NULL,
	id_exam INTEGER NOT NULL,
    completed_at TIMESTAMP NOT NULL,
    started_at TIMESTAMP NOT NULL,
	PRIMARY KEY(id_examXstudent),
	CONSTRAINT fk_exam_student
		FOREIGN KEY(id_exam)
			REFERENCES exam(id_exam)
);

CREATE TABLE question(
	id_question INTEGER NOT NULL AUTO_INCREMENT,
	_description VARCHAR(150) NOT NULL,
    _value DECIMAL(5,2) NOT NULL,
	id_exam INTEGER NOT NULL,
	PRIMARY KEY(id_question),
	CONSTRAINT fk_exam_question
		FOREIGN KEY(id_exam)
			REFERENCES exam(id_exam)
);

CREATE TABLE _option(
	id_option INTEGER NOT NULL AUTO_INCREMENT,
    id_question INTEGER NOT NULL,
	_description VARCHAR(150) NOT NULL,
    _type INTEGER NOT NULL,
    id_correct BOOLEAN NOT NULL,
	PRIMARY KEY(id_option),
	CONSTRAINT fk_question_option
		FOREIGN KEY(id_question)
			REFERENCES question(id_question)
);

CREATE TABLE optionXstudent(
	id_optionXstudent INTEGER NOT NULL AUTO_INCREMENT,
	id_student INTEGER NOT NULL,
	id_option INTEGER NOT NULL,
	PRIMARY KEY(id_optionXstudent),
	CONSTRAINT fk_option_student
		FOREIGN KEY(id_option)
			REFERENCES _option(id_option)
);