package com.hmtmcse.phonebook

import grails.gorm.services.Service

class TokenCheckerService {

    GlobalConfigService globalConfigService
    MemberService memberService

    def genToken(Member member) {
        String token = globalConfigService.getUID()
        SignInToken signInToken = new SignInToken()
        signInToken.token = token
        signInToken.member = member
        signInToken.loggedIn = false
        if (signInToken.validate()) {
            signInToken.save()
            return token
        }
    }

    def getToken(String token) {
        SignInToken signInToken = SignInToken.findByToken(token)
        return signInToken
    }

    def getMember(String token) {
        return SignInToken.findByToken(token)
    }

    def setLoginStatus(String token){
        SignInToken signInToken = SignInToken.where{
            "token" == token
        }.first()
        if (signInToken) {
            try {
                signInToken.loggedIn = true
                signInToken.save(flush: true)
            } catch (Exception e) {
                println(e.getMessage())
                return false
            }
        }
    }

    def deleteToken(String token) {
        SignInToken signInToken = SignInToken.where{
            "token" == token
        }.first()
        if (signInToken) {
            try {
                signInToken.delete(flush: true)
            } catch (Exception e) {
                println(e.getMessage())
                return false
            }
        }
    }
}