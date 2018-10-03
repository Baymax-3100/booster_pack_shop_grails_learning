<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Grails Phone Book Tutorial</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="login.css"/>
    <asset:javascript src="application.js"/>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">Grails Phone Book Tenant Creation</a>
        <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</header>


<div class="card">
    <div class="card-header">
        <span class="float-right">
            <div class="btn-group">
                <g:link action="create" controller="tenant" class="btn btn-success">New Tenant</g:link>
                <g:link action="index" controller="tenant" class="btn btn-primary">Reload</g:link>
            </div>
        </span>
    </div>
    <div class="card">
        <div class="card-header">
            Tenant Creation
        </div>
        <div class="card-body">
            <g:form controller="tenant" action="save">
                <div class="form-group">
                    <label>Tenant Name </label>
                    <g:textField name="tenantName" class="form-control" value="${tenant?.tenantName}" placeholder="Please Enter Tenant Name"/>
                    <UIHelper:renderErrorMessage fieldName="tenantName" model="${tenant}" errorMessage="please.enter.tenant.name"/>
                </div>
                <g:submitButton name="registration" value="Add New Tenant" class="btn btn-primary"/>
                <g:link controller="tenant" action="index" class="btn btn-primary"><g:message code="back.to.tenant"/></g:link>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>