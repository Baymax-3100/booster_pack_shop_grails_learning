<meta name="layout" content="public"/>

<div class="card">
    <div class="card-header">
        Member Registration
    </div>
    <div class="card-body">
        <g:form controller="authentication" action="doRegistration">
            <div class="form-group">
                <label>First Name *</label>
                <g:textField name="firstName" class="form-control" value="${member?.firstName}" placeholder="Please Enter First Name"/>
                <UIHelper:renderErrorMessage fieldName="firstName" model="${member}" errorMessage="please.enter.first.name"/>
            </div>
            <div class="form-group">
                <label>Last Name</label>
                <g:textField name="lastName" class="form-control" value="${member?.lastName}" placeholder="Please Last Name"/>
                <UIHelper:renderErrorMessage fieldName="lastName" model="${member}"/>
            </div>
            <div class="form-group">
                <label>Subdomain Name</label>
                <g:textField name="tenantName" class="form-control" value="${member?.tenantName}" placeholder="Please Enter Subdomain Name"/>
                <UIHelper:renderErrorMessage fieldName="tenantName" model="${member}" errorMessage="please.enter.subdomain.name"/>
            </div>
            <div class="form-group">
                <label>Email address *</label>
                <g:textField name="email" class="form-control" value="${member?.email}" placeholder="Please Enter Email"/>
                <UIHelper:renderErrorMessage fieldName="email" model="${member}"/>
            </div>
            <div class="form-group">
                <label>Password *</label>
                <g:passwordField name="password" class="form-control" value="${member?.password}" placeholder="Please Enter Password"/>
                <UIHelper:renderErrorMessage fieldName="password" model="${member}"/>
            </div>
            <g:submitButton name="registration" value="Registration" class="btn btn-primary"/>
            <g:link controller="authentication" action="login" class="btn btn-primary"><g:message code="back.to.login"/></g:link>
        </g:form>
    </div>
</div>