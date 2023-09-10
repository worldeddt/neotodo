create table neotodo_todo_list (
                                   ntl_index integer not null auto_increment,
                                   ntl_register_datetime datetime default '0000-00-00 00:00:00' comment '등록일시',
                                   ntl_update_datetime datetime default '0000-00-00 00:00:00' comment '갱신일시',
                                   ntl_status enum('ACTIVE', 'DELETED') default 'ACTIVE' comment '할일 상태',
                                   ntl_title varchar(500) default '' comment '할일 내용',
                                   primary key (ntl_index)
) engine=InnoDB