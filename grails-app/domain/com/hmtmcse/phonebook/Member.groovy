package com.hmtmcse.phonebook

class Member{
    Integer id
    String firstName
    String lastName
    String email
    String password
    String identityHash

    String tenantName

    Date dateCreated
    Date lastUpdated

    static constraints = {
        email(email: true, nullable: false, unique: true, blank: false)
        password(blank: false)
        firstName(blank: true)
        lastName(nullable: true, blank: true)
        identityHash(nullable: true, blank: true)
        tenantName(blank: false,unique: true)
    }

    static mapping = {
        version(false)
    }

    def beforeInsert (){
        this.password = this.password.encodeAsMD5()
    }

    def beforeUpdate(){
        this.password = this.password.encodeAsMD5()
    }

}
