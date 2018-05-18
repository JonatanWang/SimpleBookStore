SET SQL_MODE='ALLOW_INVALID_DATES';

DROP DATABASE IF EXISTS store;

CREATE DATABASE store;

USE store;

CREATE TABLE User (
  UserID INT NOT NULL AUTO_INCREMENT,
  Email VARCHAR(50),
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  
  PRIMARY KEY(UserID) 
);

INSERT INTO User 
  (Email, FirstName, LastName)
VALUES 
  ('aa@aa.com', 'a', 'a'),
  ('bb@bb.com', 'b', 'b'), 
  ('cc@c.com', 'c', 'c');

CREATE TABLE Download (
  DownloadID INT NOT NULL AUTO_INCREMENT,
  UserID INT NOT NULL,
  DownloadDate DATETIME NOT NULL,
  DownloadFilename VARCHAR(30) NOT NULL,
  ProductCode VARCHAR(10) NOT NULL,

  PRIMARY KEY (DownloadID), 
  FOREIGN KEY (UserID) REFERENCES User (UserID)
);

INSERT INTO Download 
  (UserID, DownloadDate, DownloadFilename, ProductCode)
VALUES 
  (1, '2017-09-01', 'it_chapter_1.pdf', 'it_c1');
  
  
CREATE TABLE UserPass (
  Username varchar(15) NOT NULL PRIMARY KEY,
  Password varchar(15) NOT NULL
);
  
INSERT INTO UserPass VALUES ('ali', 'sesame'),
                            ('bob', 'sesame'),
                            ('cai', 'sesame');
                          
CREATE TABLE UserRole (   
  Username VARCHAR(15) NOT NULL,
  Rolename VARCHAR(15) NOT NULL,

  PRIMARY KEY (Username, Rolename)
);
  
INSERT INTO UserRole VALUES ('ali', 'service'),
                            ('bob', 'programmer'),
                            ('cai', 'programmer');


-- Create store_user and grant privileges

DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;

    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'store_user' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER store_user@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists() ;

CREATE USER store_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON store.*
TO store_user@localhost;

/************************************************************
* Create the database named book, its tables, and a user
************************************************************/
  
DROP DATABASE IF EXISTS book;
  
CREATE DATABASE book;
  
USE book;
  
CREATE TABLE User (
    UserID INT NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(50),
    Password VARCHAR(30),
    CompanyName VARCHAR(50),
    Address1 VARCHAR(50),
    Address2 VARCHAR(50),
    City VARCHAR(50),
    State VARCHAR(50),
    Zip VARCHAR(50),
    Country VARCHAR(50),
    CreditCardType VARCHAR(50),
    CreditCardNumber VARCHAR(50),
    CreditCardExpirationDate VARCHAR(50),
  
    PRIMARY KEY (UserID)
);
  
CREATE TABLE Invoice(
    InvoiceID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    InvoiceDate DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
    TotalAmount FLOAT NOT NULL DEFAULT '0',
    IsProcessed enum('y','n') DEFAULT NULL,
  
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (UserID) REFERENCES User (UserID)
);
  
CREATE TABLE LineItem(
    LineItemID INT NOT NULL AUTO_INCREMENT,
    InvoiceID INT NOT NULL DEFAULT '0',
    ProductID INT NOT NULL DEFAULT '0',
    Quantity INT NOT NULL DEFAULT '0',
  
    PRIMARY KEY (LineItemID),
    FOREIGN KEY (InvoiceID) REFERENCES Invoice (InvoiceID)
);
  
CREATE TABLE Product(
    ProductID INT NOT NULL AUTO_INCREMENT,
    ProductCode VARCHAR(10) NOT NULL DEFAULT '',
    ProductDescription VARCHAR(100) NOT NULL DEFAULT '',
    ProductPrice DECIMAL(7,2) NOT NULL DEFAULT '0.00',
  
    PRIMARY KEY (ProductID)
);
  
INSERT INTO Product VALUES 
  ('1', 'gptg', 'Gunilla Persson - The Gunilla', '199.95'),
  ('2', 'smtc', 'Stephenie Meyer - The Chemist', '129.95'),
  ('3', 'hchm', 'Harlan Corben - Home', '249.95'),
  ('4', 'skit', 'Stephen King - It', '399.95');
  
CREATE TABLE Download (
    DownloadID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    DownloadDate DATETIME NOT NULL,
    ProductCode VARCHAR(10)  NOT NULL,
  
    PRIMARY KEY (DownloadID), 
    FOREIGN KEY (UserID) REFERENCES User (UserID)
);
  
-- Create book_user and grant privileges

DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;

    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'book_user' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER book_user@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists() ;

CREATE USER book_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE
ON book.*
TO book_user@localhost;

USE store;
USE book;
