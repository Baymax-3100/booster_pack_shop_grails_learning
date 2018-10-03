<meta name="layout" content="main"/>

<div class="card">
    <div class="card-header">
        <g:message code="contact" args="['List Of']"/>
        <span class="float-right">

        <div class="btn-group">
            <g:form controller="contact" action="index" method="GET">
                <div class="input-group" id="search-area">
                    <g:select name="colName" class="form-control" from="[name:'Name']" value="${params?.colName}" optionKey="key" optionValue="value"/>
                    <g:textField name="colValue" class="form-control" value="${params?.colValue}"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Search</button>
                    </span>
                </div>
            </g:form>
        </div>

            <div class="btn-group">
                <g:link controller="contact" action="create" class="btn btn-success"><g:message code="create"/></g:link>
                <g:link controller="contact" action="index" class="btn btn-primary"><g:message code="reload"/></g:link>
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
                <g:each in="${contact}" var="info">
                    <tr>
                        <td>${info?.name}</td>
                        <td>
                            <div class="btn-group">
                                <g:link controller="contact" action="show" class="btn btn-secondary" id="${info.id}"><i class="fa fa-eye fa-lg"></i></g:link>
                                <g:link controller="contact" action="edit" class="btn btn-secondary" id="${info.id}"><i class="fa fa-pencil fa-lg"></i></g:link>
                                <g:link controller="contact" action="delete" id="${info.id}" class="btn btn-secondary delete-confirmation"><i class="fa fa-remove fa-lg"></i></g:link>
                            </div>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <div class="paginate">
            <g:paginate total="${total ?: 0}" />
        </div>
    </div>
</div>