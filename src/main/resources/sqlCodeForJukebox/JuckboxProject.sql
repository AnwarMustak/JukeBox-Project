create database jukebox;
use jukebox;
create table songs(
song_id int primary key auto_increment,
song_name varchar(50) unique not null,
artist_name varchar(50) not null,
genre varchar(25)not null,
album_name varchar(25) not null,
duration varchar(10) not null	
);
insert  songs(song_id,song_name,artist_name,genre,album_name,duration) values(100,'Jawan Title Track','Anirudh Ravichander','Rock','Jawan','4:20');
insert  songs(song_id,song_name,artist_name,genre,album_name,duration) values(101,'Zinda Banda','Anirudh Ravichander','Rock','Jawan','4:00');
insert  songs(song_id,song_name,artist_name,genre,album_name,duration) values(102,'Chaleya','Arijit Singh','Classical music','Jawan','4:30');
insert  songs(song_id,song_name,artist_name,genre,album_name,duration) values(103,'Not Ramaiya Vastavaiya','Anirudh Ravichander','Rock','Jawan','3:00');
insert  songs(song_id,song_name,artist_name,genre,album_name,duration) values(104,'Faraatta','Anirudh Ravichander','Rock','Jawan','4:10');


create table playlist(
playlist_id int primary key auto_increment,
playlist_name varchar(50) unique not null);

alter table playlist auto_increment=10;

create table playlistContent(
playli_id int ,
songo_id int ,
primary key(playli_id,songo_id),
foreign key(playli_id) references playlist(playlist_id),
foreign key(songo_id) references songs(song_id)
);


drop database jukebox;
drop table playlist;
insert  playlist(playlist_name) values('demoplayList 2');
select * from playlist;
select * from playlistContent;
delete from playlist where playlist_name='travel';
delete from songs where song_id=107;