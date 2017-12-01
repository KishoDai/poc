create class Package extends V
create property Package.packageName String
create index Package.packageName on Package(packageName) UNIQUE

create class Database extends V
create property Database.packageName String
create property Database.type String
create property Database.databaseName String
create property Database.description String
create index Database.uniqueKey on Database(packageName,type,databaseName) UNIQUE

create class Table extends V
create property Table.packageName String
create property Table.databaseName String
create property Table.tableName String
create property Table.description String
create index Table.uniqueKey on Table(packageName,databaseName,tableName) UNIQUE

create class TableVersion extends V
create property TableVersion.packageName String
create property TableVersion.databaseName String
create property TableVersion.tableName String
create property TableVersion.version Integer
create property TableVersion.description String
create property TableVersion.createDatetime Datetime
create index TableVersion.uniqueKey on TableVersion(packageName,databaseName,tableName,version) UNIQUE

create class Column extends V
create property Column.packageName String
create property Column.databaseName String
create property Column.tableName String
create property Column.columnName String
create property Column.version Integer
create index Column.uniqueKey on Column(packageName,databaseName,tableName,columnName,version) UNIQUE

create class Index extends V
create property Index.packageName String
create property Index.databaseName String
create property Index.tableName String
create property Index.indexName String
create index Index.uniqueKey on Index(packageName,databaseName,tableName,indexName) UNIQUE


create edge HasPackage extends E
create edge HasDatabase extends E
create edge HasTable extends E
create edge HasVersion extends E
create edge HasColumn extends E
create edge HasIndex extends E
create edge HasRelation extends E