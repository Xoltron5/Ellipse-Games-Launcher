CREATE TABLE IF NOT EXISTS gameGenre (
    gameId bigint,
    genreId bigint,
    PRIMARY KEY (gameId, genreId),
    FOREIGN KEY (gameId) REFERENCES game(id),
    FOREIGN KEY (genreId) REFERENCES genre(id)
);
