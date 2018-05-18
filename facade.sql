SET SQL_MODE='ALLOW_INVALID_DATES';

DROP DATABASE IF EXISTS facade;
  
CREATE DATABASE facade;
  
USE facade;
  
CREATE TABLE User (
    UserID int NOT NULL AUTO_INCREMENT,
    Name VARCHAR(50),
    Email VARCHAR(50),
    Password VARCHAR(30),
    Address VARCHAR(50),
    
    PRIMARY KEY (UserID)
);

INSERT INTO User VALUES 
  ('1', 'Adam Araham', 'a@a.a', 'a', 'City Adam'),
  ('2', 'Bob Blair', 'b@b.b', 'b', 'City Briton!'),
  ('3', 'Carin Carlson', 'c@c.c', 'c', 'City Campton!'),
  ('4', 'David Dahlman', 'd@d.d', 'd', 'City Dalas');
  
CREATE TABLE BookOrder (
    OrderID int NOT NULL AUTO_INCREMENT,
    UserID int NOT NULL,
    OrderDate DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
    Total double NOT NULL DEFAULT '0',
      
    PRIMARY KEY (OrderID),
    FOREIGN KEY (UserID) REFERENCES User (UserID)
);
  
CREATE TABLE LineItem(
    LineItemID int NOT NULL AUTO_INCREMENT,
    OrderID int NOT NULL DEFAULT '0',
    BookID int NOT NULL DEFAULT '0',
    Quantity INT NOT NULL DEFAULT '0',
  
    PRIMARY KEY (LineItemID),
    FOREIGN KEY (OrderID) REFERENCES BookOrder (OrderID)
);
  
CREATE TABLE Book(
    BookID int NOT NULL AUTO_INCREMENT,
    Name varchar(100) not null default '',
    Author varchar(100) not null default '',
    Price DECIMAL(7,2) NOT NULL DEFAULT '0.00',
    Description VARCHAR(100) NOT NULL DEFAULT '',

    PRIMARY KEY (BookID)
);
  
INSERT INTO Book VALUES 
  ('1', 'The Gunilla', 'Gunilla Persson ', '199.95', 'An autography of Gunilla!'),
  ('2', 'The Chemist', 'Stephenie Meyer', '129.95', 'A Thriller!'),
  ('3', 'Home', 'Harlan Corben', '249.95', 'A drama!'),
  ('4', 'It', 'Stephen King', '399.95', 'A Horror!');

-- Create book_user and grant privileges

DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;

    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'facade_user' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER facade_user@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists() ;

CREATE USER facade_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE
ON facade.*
TO facade_user@localhost;

USE facade;
