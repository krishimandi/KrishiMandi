/* Create Database KrishiMandi */
CREATE DATABASE KrishiMandi;

/* Use Database KrishiMandi */
USE KrishiMandi;

/* Create Table UserInformation
	- Mobile Number length can vary across countries, Country with minimum mobile number length:4, Maximum length: 12.
	- The maximum length of an email address is 254 characters.
*/	
CREATE TABLE UserInformation (
	UserID CHAR(10) PRIMARY KEY,
    CustomerName VARCHAR(100) NOT NULL, 
    Country CHAR(2) NOT NULL, 
    MobileNumber VARCHAR(12) UNIQUE NOT NULL, 
    EmailID VARCHAR(256) NOT NULL,
    AppLanguage CHAR(3) NOT NULL,
    AlternateMobileNumber VARCHAR(12) UNIQUE, 
    ProfilePic VARCHAR(100),
    DateOfBirth DATE);
	
INSERT INTO UserInformation (UserID, CustomerName, Country, MobileNumber, EmailID, AppLanguage, DateOfBirth) VALUES
	('buyer12345', 'Vinod', 'in', '9739961685', 'vnod.hiremath@gmail.com', 'ka', '1987-06-24'); 
	
INSERT INTO UserInformation (UserID, CustomerName, Country, MobileNumber, EmailID, AppLanguage) VALUES
	('seller1234', 'Prashanth', 'in', '9964380058', 'hulsure.prashanth@gmail.com', 'ka'); 

INSERT INTO UserInformation (UserID, CustomerName, Country, MobileNumber, EmailID, AppLanguage) VALUES
	('seller5555', 'Kantesh', 'in', '9481210251', 'sbkantesh@gmail.com', 'ka'); 	

/* -------------------------------------------------------------------------------------------------------------------------*/
	
/* Create Table Language */	
CREATE TABLE Language (
	LanguageCode CHAR(2) UNIQUE NOT NULL,
    Language VARCHAR(30) UNIQUE NOT NULL);
	
INSERT INTO Language (LanguageCode, Language) VALUES ('ka', 'Kannada');

INSERT INTO Language (LanguageCode, Language) VALUES ('hi', 'Hindi');

INSERT INTO Language (LanguageCode, Language) VALUES ('ma', 'Marathi');

INSERT INTO Language (LanguageCode, Language) VALUES ('ta', 'Tamil');

INSERT INTO Language (LanguageCode, Language) VALUES ('te', 'Telugu');

INSERT INTO Language (LanguageCode, Language) VALUES ('ml', 'Malayalam');

/* -------------------------------------------------------------------------------------------------------------------------*/

/* Create Table Country */
CREATE TABLE Country (
	CountryID CHAR(2) PRIMARY KEY,
    Country Varchar(50) UNIQUE NOT NULL,
    CountryCode VARCHAR(4) UNIQUE NOT NULL);
	
INSERT INTO Country (CountryID, Country, CountryCode) VALUES ('in', 'India', '+91');

INSERT INTO Country (CountryID, Country, CountryCode) VALUES ('us', 'United States of America', '+1');

INSERT INTO Country (CountryID, Country, CountryCode) VALUES ('uk', 'United Kingdom', '+44');

INSERT INTO Country (CountryID, Country, CountryCode) VALUES ('gr', 'Germany', '+49');

INSERT INTO Country (CountryID, Country, CountryCode) VALUES ('au', 'Australia', '+61');	

/* -------------------------------------------------------------------------------------------------------------------------*/

/* Create Table State */
CREATE TABLE State (
    StateCode CHAR(2) PRIMARY KEY,
    CountryID CHAR(2) NOT NULL,
    State VARCHAR(50) NOT NULL,
    FOREIGN KEY (CountryID) REFERENCES Country(CountryID));	
	
INSERT INTO State (StateCode, CountryID, State) VALUES ('ka', 'in', 'Karnataka');

INSERT INTO State (StateCode, CountryID, State) VALUES ('ma', 'in', 'Maharashtra');

INSERT INTO State (StateCode, CountryID, State) VALUES ('ap', 'in', 'Andra Pradesh');

INSERT INTO State (StateCode, CountryID, State) VALUES ('te', 'in', 'Telangana');

INSERT INTO State (StateCode, CountryID, State) VALUES ('tn', 'in', 'Tamil Nadu');	

/* -------------------------------------------------------------------------------------------------------------------------*/

/* Create Table Address */
CREATE TABLE Address (
	UserID CHAR(10),
    FirstName VARCHAR(100) NOT NULL,
    SecondName VARCHAR(100) NOT NULL,
    Street VARCHAR(500) NOT NULL,
    Landmark VARCHAR(100),
    City VARCHAR(100) NOT NULL,
    StateCode CHAR(2) NOT NULL,
    PinCode VARCHAR(10) NOT NULL,
    CountryID CHAR(4) NOT NULL,
    AddressType INTEGER NOT NULL CHECK(AddressType BETWEEN 0 AND 1),
    FOREIGN KEY (UserID) REFERENCES UserInformation(UserID),
    FOREIGN KEY (StateCode) REFERENCES State(StateCode),
    FOREIGN KEY (CountryID) REFERENCES Country(CountryID));
	
INSERT INTO Address (UserID, FirstName, SecondName, Street, City, StateCode, PinCode, CountryID, AddressType) VALUES ('buyer12345', 'Vinod', 'Hiremath', '19 Basaveshwar Nagar, Gokul Road', 'Hubballi', 'ka', '580030', 'in', 0);

INSERT INTO Address (UserID, FirstName, SecondName, Street, City, StateCode, PinCode, CountryID, AddressType) VALUES ('buyer12345', 'Vinod', 'Hiremath', '264, 21st Main Rd, Stage 2 Phase 1, BTM Layout', 'Bengaluru', 'ka', '560076', 'in', 0);

/* -------------------------------------------------------------------------------------------------------------------------*/
CREATE TABLE DeviceDetails (
	UserID CHAR(10),
    AndroidDeviceID VARCHAR(16) UNIQUE NOT NULL,
    IMEINumber DECIMAL (15, 0) UNIQUE NOT NULL,
    AndroidVersion VARCHAR(30) NOT NULL,
    ScreenResolution VARCHAR(12) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES UserInformation(UserID));

/* -------------------------------------------------------------------------------------------------------------------------*/	
CREATE TABLE ProductMainCategory (
	ProductMainCategoryID CHAR(2) PRIMARY KEY,
    ProductMainCategory VARCHAR(30) UNIQUE NOT NULL);
	
INSERT INTO ProductMainCategory (ProductMainCategoryID, ProductMainCategory) VALUES ('fd', 'food');

/* -------------------------------------------------------------------------------------------------------------------------*/	

CREATE TABLE ProductSubCategory (
	ProductSubCategoryID CHAR(2) PRIMARY KEY,
    ProductSubCategory VARCHAR(30) UNIQUE NOT NULL,
	ProductMainCategoryID CHAR(2),
    FOREIGN KEY (ProductMainCategoryID) REFERENCES ProductMainCategory(ProductMainCategoryID));
	

INSERT INTO ProductSubCategory (ProductSubCategoryID, ProductSubCategory, ProductMainCategoryID) VALUES ('gr', 'grains','fd');
/* -------------------------------------------------------------------------------------------------------------------------*/	

CREATE TABLE ProductCategoryMapping (
	ProductTypeID CHAR(10) PRIMARY KEY,
    ProductName VARCHAR(50) UNIQUE NOT NULL,
    ProductSubCategoryID CHAR(2),
    ProductUnit VARCHAR(20) NOT NULL,
    IsProcessingNeeded BOOLEAN,
    FOREIGN KEY (ProductSubCategoryID) REFERENCES ProductSubCategory(ProductSubCategoryID));
    
INSERT INTO ProductCategoryMapping (ProductTypeID, ProductName, ProductSubCategoryID, ProductUnit, IsProcessingNeeded) VALUES ('wheat12345', 'Wheat','gr', 'kg', false);

INSERT INTO ProductCategoryMapping (ProductTypeID, ProductName, ProductSubCategoryID, ProductUnit, IsProcessingNeeded) VALUES ('redgram123', 'Red Gram','gr', 'kg', true);
/* -------------------------------------------------------------------------------------------------------------------------*/	

