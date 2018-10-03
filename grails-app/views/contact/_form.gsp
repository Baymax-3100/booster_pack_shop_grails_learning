<div class="form-group">
    <label><g:message code="contact.name"/> *</label>
    <g:textField name="name" class="form-control" value="${contact?.name}" placeholder="Please Enter Contact Name"/>
    <UIHelper:renderErrorMessage fieldName="firstName" model="${contact}" errorMessage="please.enter.name"/>
</div>
<div class="form-group">
    <label><g:message code="contact.group.name"/></label>
    <UIHelper:contactGroup value="${contact?.contactGroup*.id}"/>
</div>

<div class="number-panel">
    <g:include controller="contactNumber" action="number" id="${contact?.id}"/>
</div>


