CREATE TABLE 'match' (
                       id SERIAL PRIMARY KEY,
                       description VARCHAR(255) NOT NULL,
                       match_date DATE NOT NULL,
                       match_time TIME NOT NULL,
                       team_a VARCHAR(100) NOT NULL,
                       team_b VARCHAR(100) NOT NULL,
                       sport VARCHAR(20) NOT NULL
);

CREATE TABLE match_odds (
                            id SERIAL PRIMARY KEY,
                            match_id INTEGER NOT NULL,
                            specifier VARCHAR(10) NOT NULL,
                            odd DECIMAL(5,2) NOT NULL,
                            CONSTRAINT fk_match
                                FOREIGN KEY (match_id)
                                    REFERENCES 'match'(id)
                                    ON DELETE CASCADE
);
