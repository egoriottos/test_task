CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE subscriptions (
                               id BIGSERIAL PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               user_id BIGINT NOT NULL,
                               start_date DATE NOT NULL,
                               end_date DATE NOT NULL,
                               period VARCHAR(32) NOT NULL,
                               CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
