CREATE TABLE game (
	id SERIAL PRIMARY KEY,
	status GAME_STATUS NOT NULL,
	map INTEGER NOT NULL REFERENCES map(id));