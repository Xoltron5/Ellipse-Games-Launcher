DROP VIEW IF EXISTS item_genre;
CREATE VIEW item_genre AS 
SELECT 
    genre.name AS genreName,
    item.name AS itemName
FROM game
INNER JOIN gamegenre
    ON game.id = gamegenre.gameId
INNER JOIN genre
    ON genre.id = gamegenre.genreId
INNER JOIN item 
    ON game.id = item.gameId;