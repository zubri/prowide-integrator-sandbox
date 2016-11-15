<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.prowidesoftware.swift.model.mx.MxType"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	</head>
    <body>
        <h1>New MX</h1>
        <a href="/" class="boton-link">back</a>
        <p>Select an MX from the list below to generate its web entry form</p>
        <ul class="messages-types">
        <% for (MxType type : MxType.values()) { %>
			<li><a href="/mx?type=<%=type.name()%>"><%=StringUtils.replace(type.name(), "_", ".")%></a></li>		
        <% } %>
        </ul>
    </body>
</html>