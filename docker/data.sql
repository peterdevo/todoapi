CREATE DATABASE IF NOT EXISTS todo;
USE todo;

CREATE TABLE project (
id varchar(40) primary key,
title varchar(300),
details varchar(10000),
isCompleted boolean,
created datetime
);