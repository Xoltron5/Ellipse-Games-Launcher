CREATE TABLE IF NOT EXISTS played (
    playerId BIGINT,
    gameId BIGINT,
    lastPlayed DATETIME NOT NULL,
    PRIMARY KEY (playerId, gameId),
    FOREIGN KEY (playerId) REFERENCES player(id),
    FOREIGN KEY (gameId) REFERENCES game(id)
);