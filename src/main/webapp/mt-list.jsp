<%@page import="com.prowidesoftware.swift.model.mt.SRU2016MtType"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	</head>
    <body>
        <h1>New MT</h1>
        <a href="/" class="boton-link">back</a>
        <p>Select an MT from the list below to generate its web entry form</p>
        <ul class="messages-types">
	        <% 
	        	for (SRU2016MtType type : SRU2016MtType.values()) { 
	        		if (!type.category().equals("0")) {
	        %>
				<li><a href="/mt?type=<%=type.name()%>"><%=type.name()%></a></li>
	        <%		
	        		} 
	        	} 
	        %>
        </ul>
    </body>
</html>