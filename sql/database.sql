
create database books_lib default char set utf8;
use books_lib;
DROP TABLE book;
# DROP TABLE genre;
# DROP TABLE author;

CREATE TABLE `book`
(
    `id`           INT PRIMARY KEY  NOT NULL    AUTO_INCREMENT,
    `name`         VARCHAR(100)     NOT NULL    UNIQUE,
    `author`       VARCHAR(100)     NOT NULL,
    `release_year` INT              NOT NULL,
    `genre`        VARCHAR(100)     NOT NULL,
    `amount_of_page` INT            NOT NULL,
    `description`  TEXT             NOT NULL
);

# CREATE TABLE `genre`
# (
#     `id`           INT PRIMARY KEY  NOT NULL    AUTO_INCREMENT,
#     `name`         VARCHAR(100)     NOT NULL    UNIQUE
# );
# alter table book
#     add constraint book_genre_fk
#         foreign key (genre_id) references genre (id);
#
# CREATE TABLE `author`
# (
#     `id`           INT PRIMARY KEY     NOT NULL    AUTO_INCREMENT,
#     `name`         VARCHAR(100)   NOT NULL    UNIQUE
# );
#
# alter table book
#     add constraint book_author_fk
#         foreign key (author_id) references author (id);
