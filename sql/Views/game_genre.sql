DROP VIEW IF EXISTS game_genre;
CREATE VIEW game_genre AS 
SELECT 
    game.name AS gameName, 
    genre.name AS genreName 
FROM game
INNER JOIN gamegenre
    ON game.id = gamegenre.gameId
INNER JOIN genre    
    ON genre.id = gamegenre.genreId;