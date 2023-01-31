CREATE TABLE IF NOT EXISTS spar.user_profile (
  user_id    VARCHAR(70) NOT NULL,
  dark_theme BOOLEAN DEFAULT FALSE,
  CONSTRAINT user_pk
    primary key(user_id)
);
