package com.hmtmcse.phonebook

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@CurrentTenant
class ContactGroupService {

    GlobalConfigService globalConfigService
    MemberService memberService

    @Transactional
    def save(def params) {
        ContactGroup contactGroup = new ContactGroup(params)
        contactGroup.member = memberService.getCurrentMember().id
        def response = AppUtil.saveResponse(false, contactGroup)
        if (contactGroup.validate()) {
            response.isSuccess = true
            contactGroup.save()
        }
        return response
    }

    @Transactional
    def update(GrailsParameterMap params) {
        ContactGroup contactGroup1 = ContactGroup.findById(params.id)
        def response = AppUtil.saveResponse(false, contactGroup1)
        if (contactGroup1 != null) {
            contactGroup1.properties = params
            contactGroup1.save(failOnError: true)
            response.isSuccess = true
        }
        return response
    }

    @Transactional
    def get(Serializable id) {
        return ContactGroup.get(id)
    }

    @Transactional
    def list(GrailsParameterMap params) {
        params.max = params.max?:globalConfigService.itemsPerPage()
        List<ContactGroup> contactGroupList = ContactGroup.createCriteria().list(params) {
            if (params?.colName && params?.colValue){
                like(params.colName, "%" +  params.colValue + "%")
            }
            if (!params.sort){
                order("id","desc")
            }
            eq("member", memberService.getCurrentMember().id)
        }
        return [list:contactGroupList, count:ContactGroup.count()]
    }

    @Transactional
    def getGroupList(){
        ContactGroup.where {}
        return ContactGroup.createCriteria().list {
            eq("member", memberService.getCurrentMember().id)
        }
    }

    @Transactional
    def cleanGroupContactById(Integer id){
        ContactGroup contactGroup = ContactGroup.get(id)
        contactGroup.contact.each {contact ->
            contact.removeFromContactGroup(contactGroup)
        }
        contactGroup.save(flush:true)
    }

    @Transactional
    def delete(ContactGroup contactGroup) {
        try {
            cleanGroupContactById(contactGroup.id)
            contactGroup.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }



}
