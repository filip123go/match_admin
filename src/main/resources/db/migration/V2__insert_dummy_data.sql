-- Insert Matches
INSERT INTO match (id, description, match_date, match_time, team_a, team_b, sport)
VALUES
    (1,'OSFP - PAO', '2024-08-01', '20:30:00', 'OSFP', 'PAO', 'FOOTBALL'),
    (2,'AEK - ARIS', '2024-08-02', '21:00:00', 'AEK', 'ARIS', 'FOOTBALL'),
    (3,'Lakers - Celtics', '2024-08-03', '02:00:00', 'Lakers', 'Celtics', 'BASKETBALL');

-- Insert Match Odds
INSERT INTO match_odds (match_id, specifier, odd)
VALUES
    (1, '1', 1.75),
    (1, 'X', 3.20),
    (1, '2', 4.10),

    (2, '1', 2.00),
    (2, 'X', 3.00),
    (2, '2', 3.90),

    (3, '1', 1.65),
    (2, 'X', 3.00),
    (3, '2', 2.20);
