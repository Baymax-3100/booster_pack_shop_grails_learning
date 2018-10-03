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
        <a class="navbar-brand" href="#">Grails Phone Book Tutorial</a>
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
    <div class="card-body">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <g:sortableColumn property="name" title="${g.message(code: "name")}"/>
                <th class="action-row"><g:message code="action"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${tenantList}" var="group">
                <tr>
                    <td>${group?.tenantName}</td>
                    <td>
                        <div class="btn-group">
                            <g:link controller="tenant" action="show" class="btn btn-secondary" id="${group.id}"><i class="fa fa-eye fa-lg"></i></g:link>
                            <g:link controller="tenant" action="delete" id="${group.id}" class="btn btn-secondary delete-confirmation"><i class="fa fa-remove fa-lg"></i></g:link>
                        </div>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="paginate">
            <g:paginate total="${tenantCount ?: 0}" />
        </div>
    </div>

</div>
</body>
</html>