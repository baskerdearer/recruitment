CREATE TABLE OFFER(
OFFER_ID LONG  PRIMARY KEY AUTO_INCREMENT,
JOB_TITLE VARCHAR(500) NOT NULL,
START_DATE DATE(15) NOT NULL
)

CREATE TABLE OFFER_APPLICATION(
 	ID LONG  PRIMARY KEY AUTO_INCREMENT,
    EMAIL_ID VARCHAR(100) NOT NULL,
    RESUME_TEXT VARCHAR(300) NOT NULL,
    STATUS VARCHAR(20) NOT NULL,
    OFFER_ID LONG,
  foreign key (OFFER_ID) references OFFER(OFFER_ID)
   
)