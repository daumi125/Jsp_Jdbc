create table tblTodo(
    seq number primary key,
    todo varchar2(100) not null,
    state char(1) default 'n' not null,   --완료(y), 미완료(n)
    regdate date default sysdate not null
);

create sequence seqTodo;

insert into tblTodo values (seqTodo.nextVal, '컴퓨터 포맷하기', default, default);
insert into tblTodo values (seqTodo.nextVal, '강아지랑 산책하기', default, default);
insert into tblTodo values (seqTodo.nextVal, '고양이 목욕 시키기', default, default);

drop table tblTodo;
drop SEQUENCE seqTodo;

select * from tblTodo;

commit;