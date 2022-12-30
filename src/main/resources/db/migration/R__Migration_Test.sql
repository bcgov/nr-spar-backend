CREATE TABLE IF NOT EXISTS migration_test
  (message VARCHAR(15),
  execution_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP);

INSERT INTO migration_test
  (message)
  VALUES ('migration test');
