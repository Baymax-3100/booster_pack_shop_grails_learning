package com.hmtmcse.phonebook.controllers

import com.hmtmcse.phonebook.PhoneBookService

class DashboardController {

    PhoneBookService phoneBookService

    def index() {
        [report: phoneBookService.getReport()]
    }

    def create() {

    }
}
