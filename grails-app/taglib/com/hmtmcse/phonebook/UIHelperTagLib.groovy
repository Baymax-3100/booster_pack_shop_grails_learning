package com.hmtmcse.phonebook


class UIHelperTagLib {

    static namespace = "UIHelper"

    MemberService memberService
    ContactGroupService contactGroupService

    def renderErrorMessage = { attrs, body ->
        def model = attrs.model
        String fieldName = attrs.fieldName
        String errorMessage = attrs.errorMessage? g.message(code: attrs.errorMessage): g.message(code: "invalid.input")
        if (model && model.errors && model.errors.getFieldError(fieldName)){
            out << "<small class='form-text text-danger''><strong>${errorMessage}</strong></small>"
        }
    }

    def memberActionMenu = { attrs, body ->
        out << '<li class="nav-item dropdown show">'
        out << g.link(class:"nav-link dropdown-toggle", "data-toggle":"dropdown"){memberService.getMemberName()}
        out << '<div class="dropdown-menu">'
        out << g.link(controller: "authentication", action: "changePassword", class: "dropdown-item"){g.message(code:"change.password")}
        out << g.link(controller: "authentication", action: "logout", class: "dropdown-item"){g.message(code:"logout")}
        out << "</div></li>"
    }

    def appMenu = { attrs, body ->
        [
                [controller: "dashboard", action: "index", name: "dashboard"],
                [controller: "contact", action: "index", name: "contact"],
                [controller: "contactGroup", action: "index", name: "contact.group"],
        ].each { menu ->
            out << '<li class="list-group-item">'
            out << g.link(controller: menu.controller, action: menu.action) { g.message(code: menu.name, args: ['']) }
            out << '</li>'
        }
    }

    def contactType = { attrs, body ->
        String name = attrs.name ?: "type"
        String value = attrs.value ?: ""
        def select = [:]
        select.HOME = "Home"
        select.PERSONAL = "Personal"
        select.OTHER = "Other"
        out << g.select(from: select, name: name, optionKey: "key", optionValue: "value", value: value, class:"form-control")
    }

    def contactGroup = { attrs, body ->
        String name = attrs.name ?: "contactGroup"
        out << g.select(class:"form-control", multiple: "multiple", optionValue: "name", optionKey: "id", value: attrs.value, name: name, from: contactGroupService.getGroupList())
    }

}
