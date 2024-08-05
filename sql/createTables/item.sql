CREATE TABLE IF NOT EXISTS item (
    id BIGINT PRIMARY KEY,
    gameId BIGINT NOT NULL,
    name TEXT NOT NULL,
    cost INT NOT NULL,
    icon TEXT NOT NULL,
    description TEXT NOT NULL
);