create class Friend extends V
create property Friend.name String
create property Friend.createdDatetime Datetime
alter property Friend.createdDatetime DEFAULT "sysdate()"
create index Friend.name on Friend(name) UNIQUE

create class HasFriend extends E

