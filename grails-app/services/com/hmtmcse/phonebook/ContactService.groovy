package com.hmtmcse.phonebook

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.services.Join
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import org.hibernate.Hibernate

import static grails.gorm.multitenancy.Tenants.*


@CurrentTenant
class ContactService {

    GlobalConfigService globalConfigService
    MemberService memberService
    PhoneBookService phoneBookService

    @Transactional
    def save(GrailsParameterMap params) {
        Contact contact = new Contact(params)
        contact.member = memberService.getCurrentMember().id
        def response = AppUtil.saveResponse(false, contact)
        if (contact.validate()) {
            response.isSuccess = true
            contact.save(flush: true)
            if (params.number) {
                phoneBookService.saveContactNumber(contact, params.type, params.number)
            }
        }
        return response
    }
//    @Transactional
//    def update(Contact contact, GrailsParameterMap params) {
//        contact.properties = params
//        def response = AppUtil.saveResponse(false, contact)
//        if (contact.validate()) {
//            response.isSuccess = true
//            contact.save(flush: true)
//            if (params.number) {
//                phoneBookService.updateContactNumber(contact, params.type, params.number, params.numberId)
//            }
//        }
//        return response
//    }

    @Transactional
    def update(GrailsParameterMap params) {
        Contact contact = Contact.findById(params.id)
        def response = AppUtil.saveResponse(false, contact)
        if (contact != null) {
            contact.properties = params
            contact.save(failOnError: true)
            response.isSuccess = true
            if (params.number) {
                phoneBookService.updateContactNumber(contact, params.type, params.number, params.numberId)
            }
        }
        return response
    }

    @Transactional
    def get(Serializable id) {
        Contact contact1 = Contact.get(id)
        return contact1
    }
    @Transactional
    def showGet(Serializable id) {
        Contact contact1 = Contact.get(id)
        Hibernate.initialize(contact1.getMember())
        Hibernate.initialize(contact1.getContactGroup())
        Hibernate.initialize(contact1.getContactNumber())
        return contact1
    }



    @Transactional
    def list(GrailsParameterMap params) {
        params.max = params.max?:globalConfigService.itemsPerPage()
        List<Contact> contactList = Contact.createCriteria().list(params) {
            if (params?.colName && params?.colValue){
                like(params.colName, "%" +  params.colValue + "%")
            }
            if (!params.sort){
                order("id","desc")
            }
            eq("member", memberService.getCurrentMember().id)
        }
        return [list:contactList, count:Contact.count()]
    }

    @Transactional
    def delete(Contact contact) {
        try {
            contact.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }

}
