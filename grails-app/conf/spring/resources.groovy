// Place your Spring DSL code here
import com.hmtmcse.phonebook.DynamicConnectionSources
import com.hmtmcse.phonebook.DatabaseProvisioningService
beans = {
    dynamicConnectionSources(DynamicConnectionSources)
    databaseProvisioningService(DatabaseProvisioningService)
}
