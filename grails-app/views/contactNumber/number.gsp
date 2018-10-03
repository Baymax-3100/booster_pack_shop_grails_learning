<g:each in="${numbers}" var="number">
    <g:render template="form" model="[number:number]"/>
</g:each>
<g:render template="form"/>