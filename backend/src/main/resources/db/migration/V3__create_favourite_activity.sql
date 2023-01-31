CREATE TABLE IF NOT EXISTS spar.favourite_activity (
  id          SERIAL,
  user_id     VARCHAR(70) NOT NULL,
  activity    VARCHAR(50) NOT NULL,
  highlighted BOOLEAN DEFAULT FALSE,
  enabled     BOOLEAN DEFAULT TRUE,
  CONSTRAINT favourite_activity_pk
    primary key(id)
);
