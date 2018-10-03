package com.hmtmcse.phonebook

import grails.gorm.MultiTenant

import javax.persistence.FetchType
import javax.persistence.ManyToOne


class Contact implements MultiTenant<Contact>{

    Integer id
    String name
    String image
    Integer member

    Date dateCreated
    Date lastUpdated


    static hasMany = [contactNumber: ContactNumber, contactGroup: ContactGroup]


    static constraints = {
        image(nullable: true, blank: true)
    }

    static mapping = {
        version(false)
        contactNumber(cascade: 'all-delete-orphan')
    }
}
