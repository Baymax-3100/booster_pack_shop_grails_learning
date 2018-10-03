package com.hmtmcse.phonebook.controllers

import com.hmtmcse.phonebook.MemberService
import com.hmtmcse.phonebook.TokenCheckerService
import grails.gorm.multitenancy.WithoutTenant

@WithoutTenant
class AuthenticationController {

    MemberService memberService
    TokenCheckerService tokenCheckerService


    private static final String AUTHORIZED = "AUTHORIZED"


    @WithoutTenant
    def index() {
        redirect(controller: "dashboard", action: "index")
    }

    @WithoutTenant
    def login() {
        if (memberService.isAuthenticated()) {
            redirect(controller: "dashboard", action: "index")
        }
    }

    @WithoutTenant
    def doChangePassword() {
        if (memberService.isAuthenticated()) {
            def response = memberService.changePassword(params.password, params.newPassword, params.renewPassword)
            flash.message = response
            if (response.success){
                redirect(controller: "dashboard", action: "index")
            }else{
                redirect(controller: "authentication", action: "changePassword")
            }
        } else {
            redirect(controller: "authentication", action: "login")
        }
    }

    @WithoutTenant
    def changePassword() {
        if (!memberService.isAuthenticated()) {
            redirect(controller: "dashboard", action: "index")
        }
    }

    @WithoutTenant
    def forgotPassword() {
        if (memberService.isAuthenticated()) {
            redirect(controller: "dashboard", action: "index")
        }
    }

    @WithoutTenant
    def logout() {
        tokenCheckerService.deleteToken(session.token)
        session.invalidate()
        redirect(url: "http://localhost:8080/authentication/login")
    }

    @WithoutTenant
    def registration() {
        [member: flash.redirectParams]
    }

    @WithoutTenant
    def doLogin() {
        def response = memberService.doLogin(params.email, params.password)
        if (response.isSuccess) {
            String token = tokenCheckerService.genToken(response.model)
            redirectToTenant(params.email,token)
        } else {
            redirect(controller: "authentication", action: "login")
        }
    }

    @WithoutTenant
    def doRegistration() {
        def response = memberService.registerMember(params)
        if (response.isSuccess) {
            String token = tokenCheckerService.genToken(response.model)
            redirectToTenant(params.email,token)
        } else {
            flash.redirectParams = response.model
            redirect(controller: "authentication", action: "registration")
        }
    }

    @WithoutTenant
    def redirectToTenant(String email, String token){
        String tenantName = memberService.getTenantName(email)
        if ( tenantName != null ) {
            redirect (url: "http://"+tenantName+".localhost:8080/checker/check?token="+token) // <3>
        }
        else {
            render status: 404
        }
    }
}
