create class Namespace extends V
create property Namespace.name String
create property Namespace.description String
create property Namespace.createdDatetime datetime
create property Namespace.modifiedDatetime datetime
create index Namespace.name on Namespace(name) UNIQUE

create class Entity extends V
create property Entity.name String
create property Entity.type String
create property Entity.description String
create property Entity.createdDatetime datetime
create property Entity.modifiedDatetime datetime
create index Entity.name on Entity(name,type) UNIQUE

create class EntityStruct extends V
create property EntityStruct.version Integer
create property EntityStruct.struct EMBEDDED
create property EntityStruct.structDoc EMBEDDED
create property EntityStruct.description String
create property EntityStruct.createdDatetime datetime
create property EntityStruct.modifiedDatetime datetime

create class HasEntity extends E
create class HasStruct extends E