SET MODE MYSQL;

DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info (
  id bigint NOT NULL,
  name varchar(64) NOT NULL,
  sex tinyint NOT NULL,
  birthday datetime NOT NULL,
  weight double NOT NULL,
  status int NOT NULL,
  remark varchar(512) NOT NULL,
  deleted int NOT NULL,
  create_time datetime NOT NULL,
  update_time datetime NOT NULL,
  PRIMARY KEY (id)
);

