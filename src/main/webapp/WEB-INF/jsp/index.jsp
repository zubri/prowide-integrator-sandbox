<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/main.css"/>
</head>
    <body>
        <div class="container">

            <h1>Home</h1>
            <p>Hello, this is the mock application to test and evaluate Prowide Integrator GUI Tools</p>

            <div class="btn-group mb-3">
                <a href="/selection/mt" class="btn btn-outline-primary">Create MT</a>
                <a href="/selection/mx" class="btn btn-outline-primary">Create MX</a>
            </div>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Type</th>
                        <th>Reference</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${messages}" var="m">
                    <tr>
                        <td>${m.id}</td>
                        <td>${m.identifier}</td>
                        <td>${m.reference}</td>
                        <td>
                            <a href="/detail/${m.id}" class="btn btn-outline-primary">Detail</a>
                            <a href="/repair/${m.id}" class="btn btn-outline-primary">Repair</a>

                            <!-- for a real copy you should process this in the backend,
                                 create a hard copy of the message, and forward the request
                                 to the form, preloaded with the copied message -->
                            <a href="/repair/${m.id}" class="btn btn-outline-primary">Copy</a>
                        </th>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>