<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails Phone Book Tutorial"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="login.css"/>
    <asset:javascript src="application.js"/>
    <script type="text/javascript">
        GT.baseURL = "${session.getServletContext().getContextPath()}/";
        <g:if test="${flash?.message && flash?.message?.info}">
        jQuery(document).ready(function () {
            GT.messageBox.showMessage(Boolean(${flash.message?.success}), "${flash.message?.info}");
        });
        </g:if>
    </script>
    <g:layoutHead/>
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

<div class="container-fluid">
    <main role="main" class="col-sm-12 ml-sm-auto col-md-12 pt-3">
        <g:layoutBody/>
    </main>
</div>
</body>
</html>
