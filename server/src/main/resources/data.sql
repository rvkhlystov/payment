DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  balance int DEFAULT 0,
  currency VARCHAR DEFAULT 'RUB',
  account_types VARCHAR DEFAULT 'Debit',
  client_id  INT NOT NULL,
    foreign key (client_id) references Clients(id)
);

INSERT INTO Clients (first_name) VALUES
  ('Василий'),
  ('Петр'),
  ('Иван'),
  ('Джон'),
  ('Джек');

INSERT INTO Accounts (balance,currency,account_types,client_id) VALUES
  (10000, 'RUB', 'Debit', 1),
  (100000, 'RUB', 'Credit', 2),
  (100, 'RUB', 'Deposit', 1),
  (10, 'RUB', 'Debit', 3),
  (1000, 'RUB', 'Debit', 4),
  (1000, 'RUB', 'Debit', 5);