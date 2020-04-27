# interactive-trader-service
#

# USER GUIDE


1.
# Introduction

  1.
## Scope and Purpose

This Document provides an overview of the application designed and installation steps required to view it.

  1.
## Process/Workflows

The Application as requested is Named as &quot;Interactive Trader&quot;. It&#39;s a web based application that provides following workflows.

1. A Dashboard summary of Trader&#39;s Trade Count, PL and Trade Details.

![](RackMultipart20200427-4-1vw109s_html_decb1df711698da9.png)

1. A Trading blotter which shows the orders (Last 5 by default).

![](RackMultipart20200427-4-1vw109s_html_1fb3de547fcd5d31.png)

1. The blotter does auto refresh as the trades are created.(e.g. if trades are booked by brokers. Broker screen is not completed yet)

![](RackMultipart20200427-4-1vw109s_html_70c302eca2bc3556.png)

1. An Instrument blotter that shows the pricing information of the tickers the trader is interested.

![](RackMultipart20200427-4-1vw109s_html_b8e55dda08b8848d.png)

1. The Instrument Blotter allows adding new tickers to monitor price change.

![](RackMultipart20200427-4-1vw109s_html_62762d20f919343f.png)

1. The instrument blotter auto refresh when prices are published from backend. (AMZN changed below)

![](RackMultipart20200427-4-1vw109s_html_5c9645bd243b5bc4.png)

1. A basic order create screen to input new order and it does show tickers with current prices. ![](RackMultipart20200427-4-1vw109s_html_494685837b7bf985.png)

1.

1.
# Technologies

Technologies Used are as Below.

| Type | Name | Notes |
| --- | --- | --- |
| Server Side | Spring Boot | Spring REST, spring web socket, |
| Language | Java | 11 |
| Persistence | Spring Data JPA |
 |
| Database | H2 | A simple db file has been created and uploaded to repository. Test data already created |
| Front End | ReactJS | React JS/Node JS based web UI. |
| Build Tool | Gradle | Java Project |
| Build Tool | Yarn | Front-end |

1.
# Install Steps

The project has been uploaded to git-hub open repository.

[interactive-trader-service]

URL: [https://github.com/gourisk/interactive-trader-service](https://github.com/gourisk/interactive-trader-service)

Clone URL: [https://github.com/gourisk/interactive-trader-service.git](https://github.com/gourisk/interactive-trader-service.git)

1. Go to interactive trader service

cd interactive-trader-service

1. Copy test data file to home dir

cp ./data/tradingdb.mv.db ~/

If gradle is already installed

1. To Directly run dev build

gradle --info bootRun

1. To build a production build.

gradle bootJar

1. Run the Jar file created.

java -jar ./build/libs/interactive-trader-service-0.0.1-SNAPSHOT.jar

If gradle is not installed

1. To Directly run dev build

./gradlew --info bootRun

1. To build a production build.

./gradlew bootJar

1. Run the Jar file created.

java -jar ./build/libs/interactive-trader-service-0.0.1-SNAPSHOT.jar

[interactive-trader-ui]

URL: [https://github.com/gourisk/interactive-trader-ui](https://github.com/gourisk/interactive-trader-ui)

Clone URL: [https://github.com/gourisk/interactive-trader-ui.git](https://github.com/gourisk/interactive-trader-ui.git)

1. Go to interactive trader ui

cd interactive-trader-ui

Install Yarn (go to step 4 if already done) [Assume NodeJs is already installed)

1. Install Yarn

npm install -g yarn

1. Install Server.

Npm install -g serve

Build the Project

1. Init the project

yarn install

1. To run a dev build.

Yarn start

1. To build a production build.

Yarn build

1. Run the production build

Serve -s build

Thank you

1