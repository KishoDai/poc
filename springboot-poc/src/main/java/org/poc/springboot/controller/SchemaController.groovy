package org.poc.springboot.controller

import org.apache.commons.lang3.StringUtils
import org.poc.springboot.schema.SchemaService
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource
/**
 * Created by Administrator on 2017/7/21 0021.
 */
@RestController
@RequestMapping('/schematype/{schemaType}')
class SchemaController {

    @Resource
    private ApplicationContext applicationContext

    @RequestMapping("/schemas/{queryCondition}")
    def getSchemas(@PathVariable String schemaType,
                   @PathVariable String queryCondition) {
        if (StringUtils.isBlank(schemaType)) {
            return ['respCode': '1001', 'respMsg': 'schemaType can\'t be blank!']
        }

        String schemaServiceName = "${schemaType}SchemaServiceImpl"
        if (!applicationContext.containsBean(schemaServiceName)) {
            return ['respCode': '1001', 'respMsg': "schemaType->${schemaType} does not exist!".toString()]
        }

        applicationContext.getBean(schemaServiceName, SchemaService.class).getSchemas(queryCondition)
    }

    @RequestMapping("/versions/{queryCondition}")
    def getSchemaVersions(@PathVariable String schemaType,
                          @PathVariable String queryCondition) {
        if (StringUtils.isBlank(schemaType)) {
            return ['respCode': '1001', 'respMsg': 'schemaType can\'t be blank!']
        }

        String schemaServiceName = "${schemaType}SchemaServiceImpl"
        if (!applicationContext.containsBean(schemaServiceName)) {
            return ['respCode': '1001', 'respMsg': "schemaType->${schemaType} does not exist!"]
        }

        applicationContext.getBean(schemaServiceName, SchemaService.class).getShemaVersions(queryCondition)
    }

}
