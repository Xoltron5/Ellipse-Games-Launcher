CREATE TABLE IF NOT EXISTS item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    gameId BIGINT,
    name TEXT,
    levelReq INT,
    cost INT,
    iconPath TEXT,
    description TEXT 
);