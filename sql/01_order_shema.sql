CREATE SEQUENCE ORDER_SEQ START WITH 1000 INCREMENT BY 1;

CREATE TABLE "order" (
ID INTEGER,
ORDER_STATUS_ID SMALLINT,
PATIENT_ID INTEGER,
CUSTOMER_NAME VARCHAR(64),
CUSTOMER_PHONE VARCHAR(24),
CUSTOMER_COMMENT VARCHAR(128));
ALTER TABLE "order" ADD CONSTRAINT ORDER_ID_PK PRIMARY KEY (ID);
CREATE INDEX ORDER_PK_IDX ON "order"(ID);
