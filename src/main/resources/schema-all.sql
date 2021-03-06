DROP TABLE people IF EXISTS;
DROP TABLE application IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    application_no VARCHAR(20),
    first_name VARCHAR(60),
    last_name VARCHAR(65)

);

CREATE TABLE application  (
  application_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
  application_no VARCHAR(20),
  product_code VARCHAR(5),
  sum_insured DOUBLE,
  net_premium DOUBLE,
  gross_premium DOUBLE,
  effective_date DATETIME,
  expired_date DATETIME,
  payer_title_name VARCHAR(40),
  payer_title_name_en VARCHAR(40),
  payer_name VARCHAR(60),
  payer_name_en VARCHAR(100),
  payer_lastname VARCHAR(65),
  payer_lastname_en VARCHAR(100),
  payer_birth_date DATETIME,
  payer_gender VARCHAR(1),
  payer_marriage_status VARCHAR(1),
  payer_type VARCHAR(1),
  payer_card_type VARCHAR(1),
  payer_card_id VARCHAR(20),
  payer_occupation VARCHAR(50),
  payer_moobarn VARCHAR(100),
  payer_room_number VARCHAR(10),
  payer_home_number VARCHAR(10),
  payer_moo VARCHAR(3),
  payer_soi VARCHAR(100),
  payer_road VARCHAR(100),
  payer_tumbol VARCHAR(100),
  payer_amphur VARCHAR(100),
  payer_province VARCHAR(100),
  payer_post_code VARCHAR(10),
  payer_telephone_extension VARCHAR(15),
  payer_telephone VARCHAR(50),
  payer_fax VARCHAR(20),
  mode_of_payment VARCHAR(2),
  payment_channel VARCHAR(2),
  payment_sub_channel VARCHAR(10),
  channel_flag VARCHAR(2),
  insurer_code VARCHAR(3),
  insurer_type VARCHAR(2),
  policy_year VARCHAR(3),
  selling_date DATETIME,
  campaign_code VARCHAR(10),
  package_code VARCHAR(10),
  subpackage_code VARCHAR(50),
  base_credit_card VARCHAR(16),
  payer_consent_flags VARCHAR(6),
  insured_persons INTEGER,
  payment_detail INTEGER
);