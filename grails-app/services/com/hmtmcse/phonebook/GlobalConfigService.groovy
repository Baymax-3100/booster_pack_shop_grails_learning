package com.hmtmcse.phonebook

import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
class GlobalConfigService {

    MemberService memberService
    ContactGroupService contactGroupService

    public static boolean isBuilding = false;
    public static boolean isGeneratingSchema = false;

    def itemsPerPage() {
        return 5
    }

    public static String getUID(){
        return UUID.randomUUID().toString().toUpperCase();
    }



}
