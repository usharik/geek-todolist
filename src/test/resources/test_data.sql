insert into users(username, password)
values  ('user1', 'pass1'),
        ('user2', 'pass1');

insert into todos(description, target_date, user_id)
select 'desc1', '2019-11-05', (select id from users where username = 'user1') union
select 'desc2', '2019-11-06', (select id from users where username = 'user1') union
select 'desc3', '2019-11-07', (select id from users where username = 'user1');

insert into todos(description, target_date, user_id)
select 'desc4', '2019-12-05', (select id from users where username = 'user2') union
select 'desc5', '2019-12-06', (select id from users where username = 'user2') union
select 'desc6', '2019-12-07', (select id from users where username = 'user2');
