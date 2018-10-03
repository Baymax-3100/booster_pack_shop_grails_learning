<div class="form-group">
    <div class="form-inline phone-number-area">
        <g:if test="${number}">
            <g:hiddenField name="numberId" value="${number.id}"/>
        </g:if>
        <div class="form-group">
            <UIHelper:contactType value="${number?.type}"/>
        </div>
        <div class="form-group mx-sm-3">
            <g:textField name="number" class="form-control" placeholder="Phone Number" value="${number?.number}"/>
        </div>
        <g:if test="${number}">
            <button type="button" data-id="${number?.id}" class="btn btn-danger remove-number"><i class="fa  fa-remove fa-lg"></i></button>
        </g:if>
        <g:else>
            <button type="button" class="btn btn-primary add-new-number"><i class="fa  fa-plus-circle fa-lg"></i></button>
        </g:else>

    </div>
</div>
