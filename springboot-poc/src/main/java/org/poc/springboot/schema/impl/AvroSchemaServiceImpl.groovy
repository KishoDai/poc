package org.poc.springboot.schema.impl

import org.poc.springboot.schema.SchemaService
import org.springframework.stereotype.Service

import javax.annotation.Resource

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Service
class AvroSchemaServiceImpl implements SchemaService {

    @Resource(name = 'globalMap')
    private Map globalMap

    @Override
    def getSchemas(String queryCondition) {
        ['schemas', queryCondition, globalMap.test]
    }

    @Override
    def getShemaVersions(String queryCondition) {
        ['schemaVersion', queryCondition, globalMap.test]
    }

}
