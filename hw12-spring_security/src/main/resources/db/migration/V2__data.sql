INSERT INTO users (username, password, role)
values ('admin',
        '$2y$12$uOrkk6Rs7af5e6tNX5KAM.9VNaOztZScSqz7EuQAzccdUHRmaDI5m',
        'ADMIN');

INSERT INTO users(username, password, role)
values ('user',
        '$2y$12$H0mbph/Es82Y/5KNM5zsBOC1jl/gnBQvc3LFgbP..CWOcJjIBLnhW',
        'USER');

insert into public.genres (name) values ('sci_fiction'), ('Detective'), ('Novel');
