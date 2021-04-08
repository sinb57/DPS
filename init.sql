

create table hibernate_sequence (
   next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );



create table member (
   member_id bigint not null,
    name varchar(255),
    password varchar(255),
    primary key (member_id)
) engine=InnoDB;


create table scenario (
   scenario_id bigint not null,
    content varchar(255),
    stage_count integer not null,
    title varchar(255),
    primary key (scenario_id)
) engine=InnoDB;


create table stage (
   stage_id bigint not null,
    content varchar(255),
    no bigint,
    scenario_id bigint,
    primary key (stage_id)
) engine=InnoDB;




create table scenario_pass_log (
   scenario_pass_id bigint not null,
    local_date_time datetime(6),
    member_id bigint,
    scenario_id bigint,
    primary key (scenario_pass_id)
) engine=InnoDB;


create table stage_pass_log (
   stage_pass_id bigint not null,
    local_date_time datetime(6),
    member_id bigint,
    stage_id bigint,
    primary key (stage_pass_id)
) engine=InnoDB;




create table poc (
   dtype varchar(31) not null,
    poc_id bigint not null,
    stage_id bigint,
    primary key (poc_id)
) engine=InnoDB;


create table functional_poc (
   content varchar(255),
    type varchar(255),
    poc_id bigint not null,
    primary key (poc_id)
) engine=InnoDB;


create table secure_poc (
   content varchar(255),
    type varchar(255),
    poc_id bigint not null,
    primary key (poc_id)
) engine=InnoDB;




create table poc_log (
   dtype varchar(31) not null,
    poc_log_id bigint not null,
    member_id bigint,
    poc_id bigint,
    primary key (poc_log_id)
) engine=InnoDB;


create table functional_poc_log (
   local_date_time datetime(6),
    result varchar(255),
    poc_log_id bigint not null,
    primary key (poc_log_id)
) engine=InnoDB;


create table secure_poc_log (
   local_date_time datetime(6),
    result varchar(255),
    poc_log_id bigint not null,
    primary key (poc_log_id)
) engine=InnoDB;




alter table scenario_pass_log
   add constraint FK7v593wqk5egboj6f06lx01y4b
   foreign key (member_id)
   references member (member_id);


alter table scenario_pass_log
   add constraint FK94tfhp1datariak9edv29q25p
   foreign key (scenario_id)
   references scenario (scenario_id);




alter table stage
   add constraint FKhgktns5eij0v85uv9ha6br7jx
   foreign key (scenario_id)
   references scenario (scenario_id);


alter table stage_pass_log
   add constraint FK62i39ydq9uhidyky4civlyspj
   foreign key (member_id)
   references member (member_id);


alter table stage_pass_log
   add constraint FKrm6oxualpnjkcb1iv071eillg
   foreign key (stage_id)
   references stage (stage_id);




alter table poc
   add constraint FKf59mmb7y4llthlppgcvhqxexg
   foreign key (stage_id)
   references stage (stage_id);


alter table functional_poc
   add constraint FKm4f7vfqnty2mpq49fdrwqgppu
   foreign key (poc_id)
   references poc (poc_id);


alter table secure_poc
   add constraint FK77gmh6ebkgvaijyu2id2nxhyl
   foreign key (poc_id)
   references poc (poc_id);




alter table poc_log
   add constraint FKgrupafrefxq6sq2rcnt2mmcgm
   foreign key (poc_id)
   references poc (poc_id);


alter table poc_log
   add constraint FKs0v4bihcdgq6m9rnr6xhjlf5s
   foreign key (member_id)
   references member (member_id);


alter table functional_poc_log
   add constraint FKlqg2q7mg74rmoidy97dh2chvd
   foreign key (poc_log_id)
   references poc_log (poc_log_id);


alter table secure_poc_log
   add constraint FKptwxy4yvtae22n4olikmrh63g
   foreign key (poc_log_id)
   references poc_log (poc_log_id);

