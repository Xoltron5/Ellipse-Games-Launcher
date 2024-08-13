CREATE TABLE  player  (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) ,
    email TEXT ,
    dateOfBirth DATE ,
    password VARCHAR(255) ,
    coins BIGINT ,
    xp BIGINT ,
    icon BLOB ,
    sessionCookie DATETIME , 
    dateJoined DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE  game (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name TEXT ,
    releasedDate DATE , 
    developer TEXT ,
    description TEXT ,
    iconPath TEXT
);


CREATE TABLE  item (
    id BIGINT PRIMARY KEY,
    gameId BIGINT ,
    name TEXT ,
    cost INT ,
    icon TEXT ,
    description TEXT 
);

CREATE TABLE  played (
    playerId BIGINT,
    gameId BIGINT,
    lastPlayed DATETIME ,
    PRIMARY KEY (playerId, gameId),
    FOREIGN KEY (playerId) REFERENCES player(id),
    FOREIGN KEY (gameId) REFERENCES game(id)
);

CREATE TABLE  playerItem (
    playerId BIGINT,
    itemId BIGINT,
    PRIMARY KEY (playerId, itemId),
    FOREIGN KEY (playerId) REFERENCES player(id),
    FOREIGN KEY (itemId) REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS genre (
    id bigint PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE IF NOT EXISTS gameGenre (
    gameId bigint,
    genreId bigint,
    PRIMARY KEY (gameId, genreId),
    FOREIGN KEY (gameId) REFERENCES game(id) ON DELETE CASCADE,
    FOREIGN KEY (genreId) REFERENCES genre(id) ON DELETE CASCADE
);
