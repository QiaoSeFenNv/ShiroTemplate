CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      email VARCHAR(50) NOT NULL,
                      status INT DEFAULT 0
);

CREATE TABLE role (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(50) NOT NULL,
                      description VARCHAR(100)
);

CREATE TABLE permission (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(50) NOT NULL,
                            description VARCHAR(100)
);

CREATE TABLE user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, role_id)
);

CREATE TABLE role_permission (
                                 role_id BIGINT NOT NULL,
                                 permission_id BIGINT NOT NULL,
                                 PRIMARY KEY (role_id, permission_id)
);
