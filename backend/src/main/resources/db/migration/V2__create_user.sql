CREATE TABLE IF NOT EXISTS spar.user (
  id         SERIAL,
  email      VARCHAR(100) NOT NULL,
  last_login TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT user_pk
    primary key(id)
);
