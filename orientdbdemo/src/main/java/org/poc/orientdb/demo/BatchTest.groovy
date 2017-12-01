package org.poc.orientdb.demo

import com.orientechnologies.orient.core.command.script.OCommandScript
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx

/**
 * Created by kisho on 2017/9/1.
 */

long start = System.currentTimeMillis()
ODatabaseDocumentTx tx = OrientSqlUtil.getDatabase()
StringBuilder builder = new StringBuilder()
builder.append('begin\n' +
        'let $member = (select from Member where memberId = #memberId) \n' +
        'if($member.size()<=0) {\n' +
        '  let $member = update Member set memberId = #memberId upsert return after where memberId = #memberId\n' +
        '}\n' +
        'let $phone = (select from Phone where phone = \'#phone\')\n' +
        'if($phone.size()<=0) {\n' +
        '  let $phone = update Phone set phone = \'#phone\',province=\'#province\',city=\'#city\' upsert return after where phone = \'#phone\'\n' +
        '}\n' +
        'let $addressBookFlow = (select from AddressBookFlow where memberId = #memberId and applyNo=\'#applyNo\' and activateNo=\'#activateNo\' and createdDatetime=\'#createdDatetime\')\n' +
        'if($addressBookFlow.size()<=0) {\n' +
        '  let $addressBookFlow = update addressBookFlow set memberId = #memberId,applyNo=\'#applyNo\',activateNo=\'#activateNo\',createdDatetime=\'#createdDatetime\' upsert return after where memberId = #memberId and applyNo=\'#applyNo\' and activateNo=\'#activateNo\' and createdDatetime=\'#createdDatetime\'\n' +
        '}\n' +
        'let $phoneCleanMark = (select from PhoneCleanMark where mark = \'#mark\')\n' +
        'if($phoneCleanMark.size()<=0) {\n' +
        '  let $phoneCleanMark = update PhoneCleanMark set mark = \'#mark\' upsert return after where mark = \'#mark\'\n' +
        '}\n' +
        'let $e = (select from (select expand(out_MemberHasAddressBookFlow) from $member[0]) where in = $addressBookFlow[0]) \n' +
        'if($e.size()<=0) {\n' +
        '   create edge MemberHasAddressBookFlow from $member[0] to $addressBookFlow[0] \n' +
        '}\n' +
        'let $e = (select from (select expand(out_HasAddressBook) from $addressBookFlow[0]) where in = $phone[0]) \n' +
        'if($e.size()<=0) {\n' +
        '   create edge HasAddressBook from $addressBookFlow[0] to $phone[0] set name = \'#contactName\',createdDatetime=\'#contactCreatedDatetime\'\n' +
        '}\n' +
        'let $e = (select from (select expand(out_HasPhoneCleanMark) from $phone[0]) where in = $phoneCleanMark[0]) \n' +
        'if($e.size()<=0) {\n' +
        '   create edge HasPhoneCleanMark from $phone[0] to $phoneCleanMark[0]\n' +
        '}\n' +
        '\n' +
        'commit retry 100\n' +
        'return 1')

String batchSql = builder.toString().replace('#memberId', '1')
        .replace('#phone', '15821180279')
        .replace('#province', '安徽')
        .replace('#city', '亳州')
        .replace('#applyNo', '123456')
        .replace('#activateNo', '2222222222')
        .replace('#createdDatetime', '2017-09-19 10:10:10')
        .replace('#mark', '00')
        .replace('#contactName', '代纪章')
        .replace('#contactCreatedDatetime', '2017-09-19 10:10:10')

println("batchSql->${batchSql}")
tx.command(new OCommandScript('sql', batchSql)).execute()
