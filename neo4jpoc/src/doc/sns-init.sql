connect remote:localhost/sns admin admin

create class Member extends V
create property Member.memberId LONG
create property Member.name STRING
create property Member.idNo STRING
create property Member.isBlack BOOLEAN

create index Member.memberId on Member(memberId) UNIQUE


create class Phone extends V
create property Phone.phone STRING

create index Phone.phone on Phone(phone) UNIQUE


create class PhoneMark extends V
create property PhoneMark.mark STRING

create index PhoneMark.mark on PhoneMark(mark) UNIQUE


create class Apply extends V
create property Apply.applyNo STRING
create property Apply.status STRING
create property Apply.createdDatetime Datetime

create index Apply.applyNo on Apply(applyNo) UNIQUE


create class Order extends V
create property Order.orderNo STRING


create index Order.orderNo on Order(orderNo) UNIQUE


create class Store extends V
create property Store.storeId STRING
create property Store.storeName STRING
create property Store.merchantId STRING
create property Store.province STRING
create property Store.city STRING

create index Store.storeId on Store(storeId) UNIQUE


create class Ip extends V
create property Ip.ip STRING
create property Ip.ipCity STRING

create index Ip.ip on Ip(ip) UNIQUE

create class Device extends V
create property Device.deviceId STRING

create index Device.deviceId on Device(deviceId) UNIQUE


create class HasPhone extends E
create class HasPhoneMark extends E
create class CallTo extends E

create class HasApply extends E abstract
create class MemberHasApply extends HasApply
create class PhoneHasApply extends HasApply

create class HasOrder extends E abstract
create class MemberHasOrder extends HasOrder
create class PhoneHasOrder extends HasOrder
create class ApplyHasOrder extends HasOrder

create class HasIp extends E abstract
create class MemberHasIp extends HasIp
create class ApplyHasIp extends HasIp
create class OrderHasIp extends HasIp

create class HasDevice extends E abstract
create class MemberHasDevice extends HasDevice
create class ApplyHasDevice extends HasDevice
create class OrderHasDevice extends HasDevice

create class HasStore extends E abstract
create class ApplyHasStore extends HasStore
create class OrderHasStore extends HasStore





create property Member.province STRING
create property Member.city STRING
create property Member.isOverdue BOOLEAN


create property Store.creditLimitType STRING
create property Store.policyBracket STRING
create property Store.businessFirstType STRING

create property Apply.originalStatus STRING

create property Order.amount Decimal
create property Order.originalStatus STRING
create property Order.status INTEGER
create property Order.createdDatetime Datetime

create class HasPhoneSource extends E

create class PhoneSource extends V
create property PhoneSource.source STRING
create index PhoneSource.source on PhoneSource(source) UNIQUE

create property Member.phone STRING
create property Apply.amount Decimal


create property Order.overdueDays Integer
create property Order.maxOverdueDays Integer
create property Order.delq24 STRING

create property Apply.zxResult STRING

create class Location extends V
create property Location.longitude STRING
create property Location.latitude STRING
create property Location.province STRING
create property Location.city STRING
create property Location.address STRING
create property Location.createdDatetime Datetime
create index Location.unique on Location(longitude,latitude) UNIQUE

create class HasLocation extends E abstract
create class MemberHasLocation extends HasLocation
create class ApplyHasLocation extends HasLocation
create class OrderHasLocation extends HasLocation


create class MemberHasContact extends E



create property Store.alliesCode String


create class PhoneCleanMark extends V
create property PhoneCleanMark.mark STRING

create index PhoneCleanMark.mark on PhoneCleanMark(mark) UNIQUE


create class HasPhoneCleanMark extends E


create index Apply.createdDatetime on Apply(createdDatetime) NOTUNIQUE

create index Order.createdDatetime on Order(createdDatetime) NOTUNIQUE


create class Address extends V
create property Address.province String
create property Address.city String
create index Address.unique on Address(province,city) UNIQUE

create class MemberBelongToAddress extends E
create class PhoneBelongToAddress extends E


create class HasAddressBook extends E


create class AddressBookFlow extends V
create property AddressBookFlow.activateNo String
create property AddressBookFlow.createdDatetime Datetime
create property AddressBookFlow.memberId Long
create property AddressBookFlow.applyNo String
create index AddressBookFlow.unique on AddressBookFlow(memberId,applyNo,activateNo,createdDatetime) UNIQUE


create class MemberHasAddressBookFlow extends E



create property Order.productName String
create property Order.receiptName String
create property Order.receiptAddr String
create property Order.receiptPhone String
create property Order.reasonCodeType String
create property Order.memo String
create property Order.reasonCode1 String
create property Order.reasonCode2 String
create property Order.reasonCode3 String
create property Order.involvedAmount Integer
create property Order.followUpStatus Integer
create property Order.qualitativeId Integer

create property Apply.reasonCodeType String
create property Apply.memo String
create property Apply.reasonCode1 String
create property Apply.reasonCode2 String
create property Apply.reasonCode3 String

create property Phone.provice STRING
create property Phone.city STRING
create property Phone.operator STRING
create property Phone.cleanMarks EMBEDDEDSET


ALTER PROPERTY Member.isBlack DEFAULT "false"


create class TxKey
create property TxKey.txKey String

create property Apply.storeId String
create index Apply.storeId on Apply(storeId) NOTUNIQUE
create property Order.storeId String
create index Order.storeId on Order(storeId) NOTUNIQUE


create class PhoneAddress
create property PhoneAddress.applyNo String
create property PhoneAddress.province String
create property PhoneAddress.city String
create index PhoneAddress.applyNo on PhoneAddress(applyNo) UNIQUE


create property Ip.ipNets EMBEDDEDSET

create property Phone.phoneMarks EMBEDDEDSET

create class Report extends V
create property Report.reportId Long
create property Report.reportType Integer
create property Report.mobile String
create property Report.reportStatus Integer
create property Report.reasonType String
create property Report.counterfeitState Integer
create index Report.reportId on Report(reportId) UNIQUE

create class PhoneHasReport extends E