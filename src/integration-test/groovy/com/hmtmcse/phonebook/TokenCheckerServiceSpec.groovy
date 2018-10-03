package com.hmtmcse.phonebook

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TokenCheckerServiceSpec extends Specification {

    TokenCheckerService signInTokenService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SignInToken(...).save(flush: true, failOnError: true)
        //new SignInToken(...).save(flush: true, failOnError: true)
        //SignInToken signInToken = new SignInToken(...).save(flush: true, failOnError: true)
        //new SignInToken(...).save(flush: true, failOnError: true)
        //new SignInToken(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //signInToken.id
    }

    void "test get"() {
        setupData()

        expect:
        signInTokenService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SignInToken> signInTokenList = signInTokenService.list(max: 2, offset: 2)

        then:
        signInTokenList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        signInTokenService.count() == 5
    }

    void "test delete"() {
        Long signInTokenId = setupData()

        expect:
        signInTokenService.count() == 5

        when:
        signInTokenService.delete(signInTokenId)
        sessionFactory.currentSession.flush()

        then:
        signInTokenService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SignInToken signInToken = new SignInToken()
        signInTokenService.save(signInToken)

        then:
        signInToken.id != null
    }
}
