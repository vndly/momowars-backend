DO $$
BEGIN

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'player_status') THEN
		CREATE TYPE PLAYER_STATUS AS ENUM ('PLAYING', 'WAITING', 'VICTORIOUS', 'DEFEATED', 'SURRENDERED');
	END IF;

END $$;