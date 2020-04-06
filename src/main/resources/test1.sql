
DROP TABLE IF EXISTS Order_Master;
DROP TABLE IF EXISTS Account_Balance;
DROP TABLE IF EXISTS Trader_Position;
DROP TABLE IF EXISTS Instrument_Price_History;
DROP TABLE IF EXISTS Instrument_Price;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Instrument_Master;


CREATE TABLE Account (
    Account_Id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    First_Name   VARCHAR(100) NOT NULL,
    Last_Name    VARCHAR(100) NOT NULL,
    Email       VARCHAR(100) NOT  NULL,
    Account_Type VARCHAR(8) NOT NULL,  --TRADER - TRADER, EXT-EXTERNAL, TECH-TECHNOLOGY etc
    Create_Date  DATETIME DEFAULT SYSDATE(),
    Modified_Date    DATETIME,
    Is_Active        BIT
);

CREATE TABLE Instrument_Master (
      Ticker        VARCHAR(8)  PRIMARY KEY,
      Issuer        VARCHAR(100) NOT NULL,
      Exchange_Code  VARCHAR(100),
      Market_Sector    VARCHAR(100),
      Currency_Code    VARCHAR(8)
);


CREATE TABLE Account_Balance(
    Account_Id       INTEGER NOT NULL,
    Currency_Code    VARCHAR(8) NOT NULL,
    Cash_Balance     NUMERIC(32, 8) DEFAULT 0.0,
    CONSTRAINT fk_AccountBalance_AccountId FOREIGN KEY (Account_Id)  REFERENCES Account(Account_Id)
);

CREATE TABLE Instrument_Price_History (
    Report_Date DATE NOT NULL,
    Ticker VARCHAR(8)   NOT NULL,
    Bid_Price  NUMERIC(32,8) NOT NULL,
    Ask_Price  NUMERIC(32,8) NOT NULL,
    Mid_Price  NUMERIC(32,8) NOT NULL,
    Last_Updated DATETIME DEFAULT SYSDATE(),
    CONSTRAINT FK_InstrumentPriceHistory_Ticker FOREIGN KEY(Ticker) REFERENCES Instrument_Master(Ticker)
);

CREATE TABLE Instrument_Price
(
    Price_Key INTEGER PRIMARY KEY AUTO_INCREMENT,
    Report_Date DATE NOT NULL,
    Ticker VARCHAR(8) NOT  NULL,
    Bid_Price  NUMERIC(32,8) NOT NULL,
    Ask_Price  NUMERIC(32,8) NOT NULL,
    Mid_Price  NUMERIC(32,8) NOT NULL,
    Curve_Name   VARCHAR(100) DEFAULT 'Official_EOD',
    CONSTRAINT FK_InstrumentPrice_Ticker FOREIGN KEY(Ticker) REFERENCES Instrument_Master(Ticker),
    CONSTRAINT UK_InstrumentPrice_ReportDate_Ticker UNIQUE (Report_Date, Ticker)
);

CREATE TABLE Order_Master (
    Order_Id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    Order_Desc       VARCHAR(200),
    Ticker          VARCHAR(8)  NOT NULL,
    Trader_Id        INTEGER NOT NULL,
    Trade_Date       DATETIME NOT NULL,
    Buy_Sell_Flag     VARCHAR(1) NOT NULL,
    Quantity        INTEGER NOT NULL,
    Price           NUMERIC(32,8),
    Market_Value     NUMERIC(32, 8),
    Trade_Source   VARCHAR(100), -- Street or Broker
    Executed_Time    DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_TradeOrder_Ticker FOREIGN KEY (Ticker) REFERENCES Instrument_Master(Ticker),
    CONSTRAINT fk_TradeOrder_TraderId FOREIGN KEY (Trader_Id)  REFERENCES Account(Account_Id)

);



CREATE TABLE Trader_Position(
    Report_Date  DATE NOT NULL,
    Account_Id   INTEGER NOT NULL,
    Ticker      VARCHAR(8)  NOT NULL,
    Quantity    INTEGER,
    Price       NUMERIC(32, 8),
    Market_Value     NUMERIC(32, 8),
    Open_Market_Value NUMERIC(32, 8),
    Cash_Balance     NUMERIC(32, 8),
    Open_Cash_Balance NUMERIC(32, 8),
    Total_PL         NUMERIC(32, 8),
    CONSTRAINT fk_Trader_Position_Ticker FOREIGN KEY (Ticker) REFERENCES Instrument_Master(Ticker),
    CONSTRAINT fk_Trader_Position_TraderId FOREIGN KEY (Account_Id)  REFERENCES Account(Account_Id)
);

