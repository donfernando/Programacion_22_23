DROP TABLE IF EXISTS prueba;

CREATE TABLE prueba (
    id INTEGER,
    nombre VARCHAR(50) NOT NULL,

    CONSTRAINT PK_prueba PRIMARY KEY (id)
);

INSERT INTO prueba (nombre) VALUES ("Rodolfito");
INSERT INTO prueba (nombre) VALUES ("Jorgito");
INSERT INTO prueba (nombre) VALUES ("Juanito");
INSERT INTO prueba (nombre) VALUES ("Anita");
INSERT INTO prueba (nombre) VALUES ("Paulita");
SELECT * FROM prueba;