CREATE TABLE IF NOT EXISTS spar.user_profile (
  user_id    VARCHAR(70) NOT NULL,
  dark_theme VARCHAR(100) NOT NULL,
  CONSTRAINT user_pk
    primary key(user_id)
);
