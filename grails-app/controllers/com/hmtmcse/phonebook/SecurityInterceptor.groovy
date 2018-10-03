package com.hmtmcse.phonebook


class SecurityInterceptor {

    MemberService memberService

    SecurityInterceptor() {
        matchAll().excludes(controller: "authentication").excludes(controller: "checker")
    }

    boolean before() {
        if (!memberService.isAuthenticated()) {
            redirect(controller: "authentication", action: "login")
            return false
        }
        return true
    }

}
