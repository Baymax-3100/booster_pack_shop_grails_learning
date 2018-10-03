package com.hmtmcse.phonebook

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.transactions.Transactional

@CurrentTenant
class PhoneBookService {

    ContactService contactService
    MemberService memberService

    @Transactional
    def saveContactNumber(Contact contact, def type, def number) {
        if (type instanceof String && number instanceof String && !number.equals("")) {
            new ContactNumber(number: number, contact: contact, type: type).save(flush: true)
        } else {
            Integer index = 0;
            number.each {
                if (it) {
                    contact.addToContactNumber([type: type[index], number: it]).save(flush: true)
                    index++
                }
            }
        }
    }

    @Transactional
    private Integer getContactNumber(def numberId, Integer index) {
        try {
            return Integer.parseInt(numberId.getAt(index))
        } catch (Exception e) {
            return null
        }
    }

    @Transactional
    def updateContactNumber(Contact contact, def type, def number, def numberId) {
        if (type instanceof String && number instanceof String) {
            updateContactNumber(numberId, type, number)
        } else {
            Integer index = 0
            String contactType
            String contactNumber
            def contactNumberId
            number.each {
                contactNumber = it
                contactType = type.getAt(index)
                contactNumberId = getContactNumber(numberId, index)
                if (contactNumberId) {
                    updateContactNumber(contactNumberId, contactType, contactNumber)
                } else {
                    if (contactNumber) {
                        contact.addToContactNumber([type: type[index], number: it]).save(flush: true)
                    }
                }
                index++
            }
        }
    }

    @Transactional
    def updateContactNumber(def id, String type, String number) {
        ContactNumber contactNumber = ContactNumber.get(id)
        if (contactNumber) {
            contactNumber.type = type
            contactNumber.number = number
            contactNumber.save(flush: true)
        }
    }

    @Transactional
    def deleteContactNumber(Serializable id) {
        ContactNumber contactNumber = ContactNumber.get(id)
        if (contactNumber) {
            contactNumber.delete(flush: true)
            return AppUtil.infoMessage("Deleted")
        }
        return AppUtil.infoMessage("Unable to Delete", false)
    }

    def getContactNumbersByContactId(Serializable id) {
        def contact = contactService.get(id)
        if (contact) {
            return ContactNumber.createCriteria().list {
                eq("contact", contact)
            }
        }
        return []
    }

    def getReport() {
        def group = ContactGroup.createCriteria().list {
            eq("member", memberService.getCurrentMember().id)
        }
        def contact = Contact.createCriteria().list {
            eq("member", memberService.getCurrentMember().id)
        }
        return [group: group.size(), contact: contact.size(), number: contact?.contactNumber?.size() ?: 0]
    }

}
