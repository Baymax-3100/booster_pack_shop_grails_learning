package com.hmtmcse.phonebook.controllers

import com.hmtmcse.phonebook.PhoneBookService
import grails.converters.JSON
import grails.gorm.multitenancy.CurrentTenant

class ContactNumberController {

    PhoneBookService phoneBookService
    @CurrentTenant
    def number() {
        [numbers: phoneBookService.getContactNumbersByContactId(params.id)]
    }

    def delete(Integer id){
        render(phoneBookService.deleteContactNumber(id) as JSON)
    }
}
