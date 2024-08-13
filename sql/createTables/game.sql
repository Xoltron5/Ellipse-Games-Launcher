CREATE TABLE IF NOT EXISTS game (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    releasedDate DATE NOT NULL, 
    developer TEXT NOT NULL,
    description TEXT NOT NULL,
    iconPath TEXT NOT NULL,
    genre TEXT NOT NULL
);