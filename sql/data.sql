use books_lib;

# insert into genre (name)
# VALUES ('Fantasy'), ('History'), ('Drama'), ('Sport'), ('Education'), ('Technical');
#
# insert into author (name)
# VALUES ('Vashulenko'), ('Karpyuk'), ('Grushinska'), ('Listopad');

# insert into book (name, author_id, release_year, genre_id, amount_of_page, description)
# VALUES ('Bukvar',               1, 2020, 5, 112, 'for first class'),
#        ('English',              2, '2021', 5, 118, 'for first class'),
#        ('Ya doslidzhuyu svit',  3, '2018', 5, 120, 'for first class'),
#        ('Matematuka',           4, '2018', 5, 130, 'for first class');

# insert into book (name, release_year, genre_id, amount_of_page, description)
# VALUES ('Bukvar',               '2020', 5, 112, 'for first class'),
#        ('English',              '2021', 5, 118, 'for first class'),
#        ('Ya doslidzhuyu svit',  '2018', 5, 120, 'for first class'),
#        ('Matematuka',           '2018', 5, 130, 'for first class');

insert into book (name, author, release_year, genre, amount_of_page, description)
VALUES ('Bukvar',              'Vashulenko', 2020, 'Education', 112, 'for first class'),
       ('English',             'Karpyuk', 2021, 'Education', 118, 'for first class'),
       ('Ya doslidzhuyu svit', 'Grushinska', 2018, 'Education', 120, 'for first class'),
       ('Matematuka',           'Listopad', 2018, 'Education', 130, 'for first class');
select * from book where id=1;
