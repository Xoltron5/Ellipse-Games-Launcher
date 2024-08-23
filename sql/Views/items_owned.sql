DROP VIEW IF EXISTS items_owned;
CREATE VIEW items_owned AS
    SELECT 
        player.username,
        item.name
    FROM player
    INNER JOIN playeritem
        ON player.id = playeritem.playerId
    INNER JOIN item
        ON item.id = playeritem.itemId;