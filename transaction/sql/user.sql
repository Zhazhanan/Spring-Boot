CREATE TABLE dashboard_user (
  user_id varchar(50) NOT NULL,
  login_name varchar(100) DEFAULT NULL,
  user_name varchar(100) DEFAULT NULL,
  user_password varchar(100) DEFAULT NULL,
  user_status varchar(100) DEFAULT NULL,
  PRIMARY KEY (user_id)
);
INSERT INTO cboard.dashboard_user (user_id, login_name, user_name, user_password, user_status) VALUES ('1', 'admin', 'Administrator', 'c4ca4238a0b923820dcc509a6f75849b', null);