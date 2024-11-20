INSERT INTO CLIENT
(id, name, cpf)
VALUES('c121c15e-dd42-45be-88a7-9189c5222f66', 'paulo', '111.111.111-11');

INSERT INTO CINEMA
(id, phone, name)
VALUES('4497ec6f-ba6c-426b-a34d-a24f92cb5ca4', '011 111111111', 'Cinemax');

INSERT INTO ROOM
(id, total_capacity, room_name, cinema_id)
VALUES('8b295545-a37c-431c-901b-fc10c26ae1f0', 100, '3B', '4497ec6f-ba6c-426b-a34d-a24f92cb5ca4');

INSERT INTO MOVIE
(id, title, original_title, indicative_classification, duration, release_date, synopsis)
VALUES('03b12386-aa5f-4308-b66e-dad5a683accd', 'Harry Potter', 'Harry Potter', 14, '2h5m', DATEADD('DAY', -90, CURRENT_TIMESTAMP), 'bla bla bla');

INSERT INTO SEAT
(id, cod_seat, category, movie_id)
VALUES('db9deab5-606b-4f55-a510-3c58efcb85dd', 'H12', 'VIP', '03b12386-aa5f-4308-b66e-dad5a683accd');

INSERT INTO SESSION
(id, start_time, movie_id, room_id)
VALUES('7ee87f6b-e030-4ebd-9e85-70814a9f402e', CURRENT_TIMESTAMP, '03b12386-aa5f-4308-b66e-dad5a683accd', '8b295545-a37c-431c-901b-fc10c26ae1f0');

INSERT INTO SEAT_SESSION
(id, status, seat_id, session_id)
VALUES('4b74fde3-dae5-430e-bbf1-e90301ceb3f9', 'FREE', 'db9deab5-606b-4f55-a510-3c58efcb85dd', '7ee87f6b-e030-4ebd-9e85-70814a9f402e');