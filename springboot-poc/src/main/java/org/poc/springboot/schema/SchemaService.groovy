package org.poc.springboot.schema

/**
 * Created by Administrator on 2017/7/21 0021.
 */
interface SchemaService {

    def getSchemas(String queryCondition)

    def getShemaVersions(String queryCondition)

}