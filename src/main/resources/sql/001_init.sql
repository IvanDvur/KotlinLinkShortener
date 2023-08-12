START TRANSACTION;
DROP TABLE IF EXISTS "links" CASCADE;
DROP SEQUENCE IF EXISTS "links_seq";
CREATE SEQUENCE "links_seq" START WITH 100000;
CREATE TABLE "links"(
    "id" bigint PRIMARY KEY DEFAULT "nextval"('"links_seq"'),
    "text" text NOT NULL
);
END TRANSACTION;