<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    	<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	</head>
    <body>
        <div class="container">
            <h1>Create ${standard}</h1>
            <a href="/" class="btn btn-outline-secondary">Back</a>

            <p>Select an ${standard} from the list below to generate its web entry form</p>

            <ul class="messages-types">
                <c:forEach var="pair" items="${messages}">
                    <li><a href="${pair.key}">${pair.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>