
DROP TABLE IF EXISTS OrderMaster;
DROP TABLE IF EXISTS AccountBalance;
DROP TABLE IF EXISTS TraderPosition;
DROP TABLE IF EXISTS InstrumentPriceHistory;
DROP TABLE IF EXISTS InstrumentPrice;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS InstrumentMaster;


CREATE TABLE Account (
    AccountId   INTEGER PRIMARY KEY,
    FirstName   VARCHAR(100) NOT NULL,
    LastName    VARCHAR(100) NOT NULL,
    Email       VARCHAR(100) NOT  NULL,
    AccountType VARCHAR(8) NOT NULL,  --TRADER - TRADER, EXT-EXTERNAL, TECH-TECHNOLOGY etc
    CreateDate  DATETIME DEFAULT SYSDATE(),
    ModifiedDate    DATETIME,
    IsActive        BIT
);

CREATE TABLE InstrumentMaster (
      Ticker        VARCHAR(8)  PRIMARY KEY,
      Issuer        VARCHAR(100) NOT NULL,
      ExchangeCode  VARCHAR(100),
      MarketSector    VARCHAR(100),
      CurrencyCode    VARCHAR(8)
);


CREATE TABLE AccountBalance(
    AccountId       INTEGER NOT NULL,
    CurrencyCode    VARCHAR(8) NOT NULL,
    CashBalance     NUMERIC(20, 8) DEFAULT 0.0,
    CONSTRAINT fk_AccountBalance_AccountId FOREIGN KEY (AccountId)  REFERENCES Account(AccountId)
);

CREATE TABLE InstrumentPriceHistory (
    ReportDate SMALLDATETIME NOT NULL,
    Ticker VARCHAR(8)   NOT NULL,
    BidPrice  NUMERIC(8,8) NOT NULL,
    AskPrice  NUMERIC(8,8) NOT NULL,
    MidPrice  NUMERIC(8,8) NOT NULL,
    LastUpdated DATETIME DEFAULT SYSDATE(),
    CONSTRAINT FK_InstrumentPriceHistory_Ticker FOREIGN KEY(Ticker) REFERENCES InstrumentMaster(Ticker)
);

CREATE TABLE InstrumentPrice
(
    PriceKey INTEGER PRIMARY KEY,
    ReportDate SMALLDATETIME NOT NULL,
    Ticker VARCHAR(8) NOT  NULL,
    BidPrice  NUMERIC(8,8) NOT NULL,
    AskPrice  NUMERIC(8,8) NOT NULL,
    MidPrice  NUMERIC(8,8) NOT NULL,
    CurveName   VARCHAR(100) DEFAULT 'Official_EOD',
    CONSTRAINT FK_InstrumentPrice_Ticker FOREIGN KEY(Ticker) REFERENCES InstrumentMaster(Ticker),
    CONSTRAINT UK_InstrumentPrice_ReportDate_Ticker UNIQUE (ReportDate, Ticker)
);

CREATE TABLE OrderMaster (
    OrderId         INTEGER PRIMARY KEY,
    OrderDesc       VARCHAR(200),
    Ticker          VARCHAR(8)  NOT NULL,
    TraderId        INTEGER NOT NULL,
    TradeDate       DateTime NOT NULL,
    BuySellFlag     VARCHAR(1) NOT NULL,
    Quantity        INTEGER NOT NULL,
    Price           NUMERIC(8,8),
    MarketValue     NUMERIC(20, 8),
    TradeSourceId   VARCHAR(100), -- Street or Broker
    ExecutedTime    DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_TradeOrder_Ticker FOREIGN KEY (Ticker) REFERENCES InstrumentMaster(Ticker),
    CONSTRAINT fk_TradeOrder_TraderId FOREIGN KEY (TraderId)  REFERENCES Account(AccountId)

);


CREATE TABLE TraderPosition(
    ReportDate  SMALLDATETIME NOT NULL,
    AccountId   INTEGER NOT NULL,
    Ticker      VARCHAR(8)  NOT NULL,
    Quantity    INTEGER,
    Price       NUMERIC(8, 8),
    MarketValue     NUMERIC(20, 8),
    OpenMarketValue NUMERIC(20, 8),
    CashBalance     NUMERIC(20, 8),
    OpenCashBalance NUMERIC(20, 8),
    TotalPL         NUMERIC(20, 8)
);

