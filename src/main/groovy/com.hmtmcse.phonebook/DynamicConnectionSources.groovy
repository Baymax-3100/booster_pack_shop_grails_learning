package com.hmtmcse.phonebook

import grails.events.annotation.gorm.Listener
import org.grails.datastore.mapping.engine.event.PostInsertEvent
import org.grails.orm.hibernate.HibernateDatastore
import org.springframework.beans.factory.annotation.Autowired

class DynamicConnectionSources {
    @Autowired
    HibernateDatastore hibernateDatastore

    //N.B: DatabaseProvisioningService bean is created

    @Autowired
    DatabaseProvisioningService databaseProvisioningService

    /* @Listener is an event listener for Member domain.
    * The event trigger after a new entry has been added to the Member domain
    * After insert, using event object we retrieve tenant name and call findDatabaseConfigurationBytenantID
    * and pass tenant name as parameter and it returns a databaseconfig object with all the configuration for new tenant
    * after that we add the newly configured connection to the hibernateDatastore by retriving all datasources
    * and then add the new connection config using addConnectionSource method */

    @Listener(Member)
    void onTenantPostInsertEvent(PostInsertEvent event) {
        String tenantID = event.getEntityAccess().getPropertyValue("tenantName")
        tenantID = tenantID.toLowerCase()
        DatabaseConfig databaseConfiguration = databaseProvisioningService.findDatabaseConfigurationBytenantID(tenantID)
        hibernateDatastore.getConnectionSources().addConnectionSource(databaseConfiguration.dataSourceName, databaseConfiguration.configuration)
    }
}
