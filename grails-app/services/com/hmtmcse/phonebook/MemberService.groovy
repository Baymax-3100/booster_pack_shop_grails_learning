package com.hmtmcse.phonebook

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.multitenancy.WithoutTenant
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import groovy.transform.CompileStatic

class MemberService {

    private static final String AUTHORIZED = "AUTHORIZED"
    @WithoutTenant
    def registerMember(GrailsParameterMap params) {
        Member member = new Member(params)
        def response = AppUtil.saveResponse(false, member)
        if (member.validate()) {
            response.isSuccess = true
            member.save()
        }
        return response
    }

    @CurrentTenant
    def setMemberAuthorization(Member member) {
        def authorization = [isLoggedIn: true, member: member]
        AppUtil.getAppSession()[AUTHORIZED] = authorization
    }


    @WithoutTenant
    def doLogin(String email, String password){
        password = password.encodeAsMD5()
        Member member = Member.findByEmailAndPassword(email, password)
        def response = AppUtil.saveResponse(false, member)
        if (member) {
            response.isSuccess = true
        }
        return response
    }

    @WithoutTenant
    boolean isAuthenticated(){
        def authorization = AppUtil.getAppSession()[AUTHORIZED]
        if (authorization && authorization.isLoggedIn){
            return true
        }
        return false
    }

    @CurrentTenant
    def getMember(){
        def authorization = AppUtil.getAppSession()[AUTHORIZED]
        return authorization?.member
    }

//    @WithoutTenant
    def getMemberName(){
        def member = getMember()
        return "${member.firstName} ${member.lastName}"
    }

    @CurrentTenant
    def getCurrentMember(){
        return getMember()
    }

    @WithoutTenant
    def changePassword(String oldPassword, String newPassword, String retrievePassword){
        Member member = (Member) getCurrentMember()
        if (!newPassword || !retrievePassword || !newPassword.equals(retrievePassword)) {
            return AppUtil.infoMessage("Your Entered Password Not Matched.", false)
        } else if (member && !member.password.equals(oldPassword.encodeAsMD5())) {
            return AppUtil.infoMessage("Incorrect Old Password.", false)
        } else {
            member = Member.get(member.id)
            member.password = newPassword
            member.save(flush: true)
            setMemberAuthorization(member)
        }
        return AppUtil.infoMessage("Password Changed")
    }

    @WithoutTenant
    String getTenantName(String email){
        return Member.where {
            email == email
        }.first().tenantName
    }
}
