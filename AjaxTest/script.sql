--설문조사
create table tblSurvey(
    seq number primary key,
    question varchar2(300) not null,
    item1 varchar2(300) not null,
    item2 varchar2(300) not null,
    item3 varchar2(300) not null,
    item4 varchar2(300) not null,
    cnt1 number default 0 not null,
    cnt2 number default 0 not null,
    cnt3 number default 0 not null,
    cnt4 number default 0 not null
);

insert into tblSurvey values(1,'가장 자신있는 프로그래밍 언어는?', 'JAVA', 'Python', 'C#', 'JavaScript', default, default, default, default);

select * from tblSurvey;

update tblSurvey set
    cnt1 =17,
    cnt2 =5,
    cnt3 =5,
    cnt4 =3
        where seq = 1;

commit;
    
    