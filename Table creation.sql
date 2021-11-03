SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop database if exists SCUTCat;
create database SCUTCat;
use SCUTCat;

/*
    1.user
     -match with the wechat
     -more info need to be added
*/

drop table if exists user;
create table user
(
    uid varchar(50) primary key ,
    name varchar(50) character set utf8 collate utf8_general_ci null default null,
    type varchar(20) comment 'User type: Admin;common user;'default 'common user',
    registerTime date not null,
    gender int(1),
    avatar_url varchar(200) comment'User head image url'
)ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists userFollow;
create table userFollow
(
    uid varchar(50) comment 'The primary user',
    uid_followed varchar(50) comment 'The user followed by the primary user',
    foreign key (uid_followed) references userFollow(uid) on delete cascade,
    primary key (uid,uid_followed)
)ENGINE = InnoDB  AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ;

drop table if exists post;
create table post
(
    pid varchar(50) primary key ,
    uid varchar(50),
    content longtext character set utf8 collate utf8_general_ci not null,
    tag varchar(100) comment 'label of the post',
    `like` int(8)default 0,
    `read` int(8)default 0,
    follow int(8)default 0,
    share int(8)default 0,
    hot double(10,3)default 0.0,
    time datetime not null,
    foreign key (uid) references user(uid)
)ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists p_pic;#post picture
create table p_pic
(
    ppid varchar(20),
    pid varchar(50),
    url varchar(255)not null,
    primary key (ppid),
    foreign key (pid) references post(pid) on update cascade on delete cascade
)ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

drop table if exists userFollowPost;#post picture
create table userFollowPost
(
    uid varchar(50),
    pid varchar(50),
    primary key (uid, pid),
    foreign key (uid) references user (uid) ON DELETE RESTRICT ON UPDATE RESTRICT,
    foreign key (pid) references post (pid) ON DELETE RESTRICT ON UPDATE RESTRICT
)ENGINE = InnoDB  AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `history`;#post view history
create table `history`
(
    uid varchar(50),
    pid varchar(50),
    time datetime not null,
    primary key (uid,time),
    foreign key (uid)references user(uid) on delete cascade,
    foreign key (pid)references post(pid)
)ENGINE = InnoDB  AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists comment;
create table comment
(
    cid varchar(50)primary key,
    uid varchar(50),
    pid varchar(50),
    content longtext character set utf8 collate utf8_general_ci not null,
    `like` int(8)default 0,
    time datetime not null,
    top boolean default false comment 'whether the comment is stick to top by the poster',
    foreign key (uid) references user(uid) on delete cascade ,
    foreign key (pid) references post(pid) on delete cascade
)ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table iF exists sciArticle;
create table sciArticle
(
    aid varchar(50)primary key ,
    tag varchar(50),
    content longtext character set utf8 collate utf8_general_ci not null,
    `like` int(8) default 0,
    title varchar(50)character set utf8 collate utf8_general_ci not null
);

drop table if exists likeComment;
create table likeComment
(
    uid varchar(50),
    cid varchar(50),
    primary key (uid,cid),
    foreign key (uid)references user(uid) on delete cascade ,
    foreign key (cid)references comment(cid) on delete cascade
)ENGINE = InnoDB  AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists likePost;
create table likePost
(
    uid varchar(50),
    pid varchar(50),
    primary key (uid,pid),
    foreign key (uid)references user(uid) on delete cascade ,
    foreign key (pid)references post(pid) on delete cascade
)ENGINE = InnoDB  AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

set foreign_key_checks = 1;