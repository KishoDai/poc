package org.poc.springboot.schema.impl

import org.poc.springboot.schema.SchemaService
import org.springframework.stereotype.Service

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Service
class AvroSchemaServiceImpl implements SchemaService {

    @Override
    def getSchemas(String queryCondition) {
        ['schemas', queryCondition]
    }

    @Override
    def getShemaVersions(String queryCondition) {
        ['schemaVersion', queryCondition]
    }

}
