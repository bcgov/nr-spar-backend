CREATE TABLE IF NOT EXISTS spar.favorite_activity (
  id             SERIAL,
  user_id        INT NOT NULL,
  activity_title VARCHAR(50) NOT NULL,
  highlighted    BIT DEFAULT 0::bit,
  enabled        BIT DEFAULT 1::bit,
  CONSTRAINT favorite_activity_pk
    primary key(id),
  CONSTRAINT favorite_activity_fk
    foreign key(user_id) REFERENCES spar.user(id)
);
