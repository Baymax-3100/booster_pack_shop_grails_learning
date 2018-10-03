package com.hmtmcse.phonebook

class DatabaseProvisioningService {


    /*This retrive all the tenant name from member table. And pass the tenant name to the findDatabaseConfigurationBytenantID to create datasource configuration*/

    List<DatabaseConfig> findAllDatabaseConfiguration() {

            List<String> tenantName = Member.withCriteria {projections { property('tenantName')}}
            tenantName.collect { findDatabaseConfigurationBytenantID(it) }

    }

    /* findDatabaseConfigurationBytenantID take tenantID as string and create a new object of databaseconfig.
    * sets the datasource name from tenantID and generate a map of username,password,and url*/
    DatabaseConfig findDatabaseConfigurationBytenantID(String tenantID) {
        new DatabaseConfig(dataSourceName: tenantID, configuration: configurationByTenantID(tenantID))
    }


    Map<String, Object> configurationByTenantID(String tenantID) {
        [
                'hibernate.hbm2ddl.auto':'create-drop',
                'username': 'root',
                'password': '',
                'url':"jdbc:mysql://127.0.0.1:3306/$tenantID?createDatabaseIfNotExist=true"
        ] as Map<String, Object>
    }
}