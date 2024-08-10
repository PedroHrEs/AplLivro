/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  pedro
 * Created: 10/08/2024
 */
create table autor(
    idautor serial primary key,
    nomeautor varchar(50)
);
create table editora(
    ideditora serial primary key,
    nomeeditora varchar(50)
);
create table livro(
    idlivro serial primary key,
    idautor integer,
    ideditora integer,
    titulo varchar(70),
    isbn varchar(50),
    numpaginas varchar(50),
    tipocapa varchar(50),
    constraint fk_autorlivro foreign key (idautor) references autor(idautor),
    constraint fk_editoralivro foreign key (ideditora) references editora(ideditora)
);
insert into autor (nomeautor) values('Pedro');
insert into editora (nomeeditora) values('editora1');
insert into livro (titulo,isbn,numpaginas,tipocapa,idautor,ideditora) values('eucalipto e sua historia', '4444', '50 pag', 'dura',1,1);