package com.hmtmcse.phonebook

class SignInTokenController {
    GlobalConfigService globalConfigService

    def index(){
        render(globalConfigService.getUID())
    }
}
