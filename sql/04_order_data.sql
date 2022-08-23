DO $$
DECLARE
v_order_id integer;
v_order_item_id integer;
BEGIN
SELECT NEXTVAL('order_seq') INTO v_order_id;
INSERT INTO "order"(ID, ORDER_STATUS_ID, CUSTOMER_NAME, CUSTOMER_PHONE,
CUSTOMER_COMMENT)
VALUES (v_order_id, 1, 'Ivanov I.I', '+7(952)-634-55-23', 'Pls. call
before delivery');
SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
VALUES (v_order_item_id, v_order_id, 'Order Item #1');
SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
VALUES (v_order_item_id, v_order_id, 'Order Item #2');
SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
VALUES (v_order_item_id, v_order_id, 'Order Item #3');
END $$;
