-- 메모 테이블
drop table tblMemo;

select * from tblUser;

create table tblUser(
    id varchar2(50) primary key,
    pw varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(100) not null,
    pic varchar2(100) default 'pic.png' not null,
    intro varchar2(500) not null,
    regdate date default sysdate not null,
    ing number(1) default 1 not null
);

insert into tblUser values('hong', '1111', '홍길동', 'hong@gmail.com', default,
'안녕하세요. 홍길동입니다.', default, default);

select * from tblMemo;
drop SEQUENCE seqMemo;
create SEQUENCE seqMemo;

create table tblMemo(
    seq number primary key,
    id varchar2(50) not null references tblUser(id),
    subject varchar2(1000) not null,
    content varchar2(2000) not null,
    regdate date default sysdate not null
);


insert into tblMemo values (seqMemo.nextVal, 'hong', '메모입니다.', '내용입니다.', default);

commit;

