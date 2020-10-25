DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  balance int DEFAULT 0,
  client_id  INT NOT NULL,
    foreign key (client_id) references Clients(id)
);

INSERT INTO Clients (first_name) VALUES
  (1111),
  (2222),
  (3333);

INSERT INTO Accounts (balance, client_id) VALUES
  (10000, 1),
  (100000, 2),
  (100, 1),
    (10, 3);