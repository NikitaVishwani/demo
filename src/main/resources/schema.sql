DROP TABLE IF EXISTS Business_Profile;

CREATE TABLE Business_Profile (
  id VARCHAR AUTO_INCREMENT  PRIMARY KEY,
  company_name VARCHAR(250) NOT NULL,
  legal_name VARCHAR(250) NOT NULL,
  business_address VARCHAR(650) NOT NULL,
  legal_address VARCHAR(250) NOT NULL,
  tax_identifiers VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  Website VARCHAR(250)
);

CREATE TABLE Subscription (
  id VARCHAR NOT NULL,
  subscription_id VARCHAR NOT NULL,
  PRIMARY KEY(id,subscription_id)
  );

CREATE TABLE Approval (
   id varchar NOT NULL,
   subscription_id varchar NOT NULL,
   status VARCHAR(250),
   update_req_id varchar NOT NULL,
   PRIMARY KEY(id,subscription_id)
 );



