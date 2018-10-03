package com.hmtmcse.phonebook

import grails.gorm.MultiTenant

class ContactNumber implements MultiTenant<ContactGroup>{

    Integer id
    String number
    String type
    Contact contact

    Date dateCreated
    Date lastUpdated


    static constraints = {
    }

    static mapping = {
        version(false)
    }
}
