<meta name="layout" content="public"/>

<div class="card">
    <div class="card-header">
        Change password
    </div>

    <div class="card-body">
        <g:form controller="authentication" action="doChangePassword">
            <div class="form-group">
                <g:passwordField name="password" class="form-control"
                                 placeholder="Please Enter old Password" required="required"/>

            </div>
            <div class="form-group">
                <g:passwordField name="newPassword" class="form-control"
                                 placeholder="Please Enter new Password" required="required"/>

            </div>

            <div class="form-group">
                <g:passwordField name="renewPassword" class="form-control"
                                 placeholder="Retype new  Password" required="required"/>
            </div>
            <g:submitButton name="registration" value="Change Password" class="btn btn-primary"/>
            <g:link controller="dashboard" action="index" class="btn btn-primary"><g:message code="back.to.dashboard"/></g:link>
        </g:form>
    </div>
</div>