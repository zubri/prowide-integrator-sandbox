<%@page import="com.prowidesoftware.swift.model.mt.SRU2016MtType"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css"/>
</head>
    <body>
        <h1>New MT</h1>
        <a href="/" class="boton-link">back</a>
        <p>Select an MT from the list below to generate its web entry form</p>
        <% for (SRU2016MtType type : SRU2016MtType.values()) { %>
			<a href="/mt?type=<%=type.name()%>"><%=type.name()%></a><br />		
        <% } %>
    </body>
</html>