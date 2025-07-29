-- Insert Matches
INSERT INTO match (description, match_date, match_time, team_a, team_b, sport)
VALUES
    ('OSFP - PAO', '2024-08-01', '20:30:00', 'OSFP', 'PAO', 'FOOTBALL'),
    ('AEK - ARIS', '2024-08-02', '21:00:00', 'AEK', 'ARIS', 'FOOTBALL'),
    ('Lakers - Celtics', '2024-08-03', '02:00:00', 'Lakers', 'Celtics', 'BASKETBALL');

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
    (3, '2', 2.20);
