package com.hmtmcse.phonebook.controllers

import com.hmtmcse.phonebook.MemberService
import com.hmtmcse.phonebook.TokenCheckerService
import grails.gorm.multitenancy.CurrentTenant
import groovy.time.TimeCategory

@CurrentTenant
class CheckerController {

    TokenCheckerService tokenCheckerService
    MemberService memberService

    /*check method is used to check a token validity */
    def check() {
        def token = tokenCheckerService.getToken(params.token)
        if(!token.loggedIn) {
            Date tokenDate = token.dateCreated

            use(TimeCategory) {
                tokenDate = tokenDate + 5.minutes
            }

            Date currentDate = new Date()

            if (currentDate < tokenDate) {
                memberService.setMemberAuthorization(
                        tokenCheckerService.getMember(params.token).member
                )
                session.setAttribute("token",params.token)
                tokenCheckerService.setLoginStatus(params.token)
                redirect(controller: 'dashboard', action: 'index')
            }
        }else {
            flash.message = "Error login."
            redirect(url: "http://localhost:8080/authentication/login")
        }

    }
}
