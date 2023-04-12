insert into category (name, created)
values ('Картины', now())
on conflict (name) do nothing;
insert into category (name, created)
values ('Скульптуры', now())
on conflict (name) do nothing;
insert into category (name, created)
values ('Музыка', now())
on conflict (name) do nothing;
insert into category (name, created)
values ('Рукоделие', now())
on conflict (name) do nothing;
insert into category (name, created)
values ('Виртуальное', now())
on conflict (name) do nothing;