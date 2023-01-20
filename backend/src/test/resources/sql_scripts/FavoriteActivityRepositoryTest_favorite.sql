INSERT INTO user (email)
  VALUES ('test.user@gov.bc.ca');

INSERT INTO favorite_activity (user_id, activity_title, highlighted, enabled)
  VALUES ((SELECT id FROM user where email = 'test.user@gov.bc.ca'), 'SEEDLOT_REGISTRATION', 0, 1);

INSERT INTO favorite_activity (user_id, activity_title, highlighted, enabled)
  VALUES ((SELECT id FROM user where email = 'test.user@gov.bc.ca'), 'PARENT_TREE_ORCHARD', 0, 1);