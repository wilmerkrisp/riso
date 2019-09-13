CREATE TABLE IF NOT EXISTS DEFAULT_CANVAS
(
    id     VARCHAR(30),
    name   VARCHAR(30),
    width  INT,
    height INT,
    x_max  INT,
    y_max  INT,
    screen VARCHAR(1600)
);
TRUNCATE TABLE DEFAULT_CANVAS;
