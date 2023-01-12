INSERT INTO user (email)
  VALUES ('test.user@gov.bc.ca');

INSERT INTO favorite_activity (user_id, activity_title, highlighted, enabled)
  VALUES ((SELECT id FROM user where email = 'test.user@gov.bc.ca'), 'Activity one', 0, 1);

INSERT INTO favorite_activity (user_id, activity_title, highlighted, enabled)
  VALUES ((SELECT id FROM user where email = 'test.user@gov.bc.ca'), 'Activity two', 0, 1);