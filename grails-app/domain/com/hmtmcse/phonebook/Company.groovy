package com.hmtmcse.phonebook

import grails.gorm.MultiTenant

class Company implements MultiTenant<Company> {
    String name

    static constraints = {
        name (blank: false)
    }
}
