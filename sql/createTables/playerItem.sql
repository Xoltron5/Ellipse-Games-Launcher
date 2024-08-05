CREATE TABLE IF NOT EXISTS playerItem (
    playerId BIGINT,
    itemId BIGINT,
    PRIMARY KEY (playerId, itemId),
    FOREIGN KEY (playerId) REFERENCES player(id),
    FOREIGN KEY (itemId) REFERENCES item(id)
);