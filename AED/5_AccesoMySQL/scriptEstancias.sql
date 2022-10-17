
CREATE DATABASE IF NOT EXISTS central_reservas;
USE central_reservas;

DROP TABLE IF EXISTS Estancias;
DROP TABLE IF EXISTS Habitaciones;


CREATE TABLE Habitaciones (
    numHabitacion CHARACTER(4),
    codHotel CHARACTER(10),
    preciodia INTEGER NOT NULL DEFAULT 0 ,
    CONSTRAINT pk_habitaciones PRIMARY KEY (numHabitacion,codHotel )
);

CREATE TABLE Estancias (
	id Int(3) ,
    nombre VARCHAR(50),
	fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    numHabitacion CHARACTER(4) NOT NULL,
    codHotel CHARACTER(10) NOT NULL,
    
    CONSTRAINT pk_clientes PRIMARY KEY (id),
    CONSTRAINT fk_nHab 	FOREIGN KEY (numHabitacion,codHotel) 
    					REFERENCES Habitaciones(numHabitacion,codHotel)
    					ON DELETE CASCADE
    					ON UPDATE CASCADE
);

INSERT INTO Habitaciones VALUES ("301","H10",84);
INSERT INTO Habitaciones VALUES ("302","Riu1",76);
INSERT INTO Habitaciones VALUES ("501","Sol3",50);
INSERT INTO Habitaciones VALUES ("401","Luna4",120);

INSERT INTO Estancias VALUES (01,"Juan Pérez","2018-01-01","2019-01-01","301","H10");
INSERT INTO Estancias VALUES (02,"Pedro López Méndez","2017-01-01","2017-06-01","301","H10");
INSERT INTO Estancias VALUES (03,"María Rodríguez Márquez","2016-03-01","2016-05-08","301","H10");

INSERT INTO Estancias VALUES (04,  "Victor González Rámos", "2018-02-01", "2019-06-01" ,"302","Riu1");
INSERT INTO Estancias VALUES (05,   "Mario Arteaga Méndez", "2016-01-01", "2016-06-01" ,"302","Riu1");

INSERT INTO Estancias VALUES (06 , "Raquel Mateo Umpierrez", "2017-05-01", "2018-09-01"  ,"501","Sol3");
INSERT INTO Estancias VALUES (07 , "Teresa Gutierrez Estevez", "2017-01-01" , "2017-04-01"  ,"501","Sol3");
INSERT INTO Estancias VALUES (08 , "Roberto Fajardo Lopez",  "2017-06-01" , "2017-08-08"  ,"501","Sol3");
INSERT INTO Estancias VALUES (09 , "Raquel Mateo Umpierrez",   "2016-03-01" , "2016-03-08" ,"501","Sol3");

INSERT INTO Estancias VALUES (10, "María Rodríguez Márquez", "2017-05-01", "2018-09-01" ,"401","Luna4");
INSERT INTO Estancias VALUES (11, "Luis Martínez Estévez", "2017-01-01", "2017-04-01" ,"401","Luna4");
INSERT INTO Estancias VALUES (12, "Carlos Pérez López", "2017-06-01", "2017-08-08" ,"401","Luna4");
INSERT INTO Estancias VALUES (13, "Jose María Martínez Bacallado", "2016-03-01", "2016-03-08","401","Luna4");

-- INSERT INTO Estancias (nombre,fechaInicio,fechaFin,numHabitacion,codHotel) VALUES ("Fernando", "2022-09-01", "2022-09-18","401","Luna4");

