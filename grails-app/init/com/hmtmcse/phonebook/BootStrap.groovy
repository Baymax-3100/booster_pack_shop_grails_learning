package com.hmtmcse.phonebook

import org.grails.orm.hibernate.HibernateDatastore

class BootStrap {

    HibernateDatastore hibernateDatastore
    DatabaseProvisioningService databaseProvisioningService

    def init = { servletContext ->
        for (DatabaseConfig databaseConfiguration : databaseProvisioningService.findAllDatabaseConfiguration() ) {

            hibernateDatastore.getConnectionSources().addConnectionSource(databaseConfiguration.dataSourceName, databaseConfiguration.configuration)
        }
    }

    def destroy = {
    }
}
