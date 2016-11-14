<%@page import="com.prowidesoftware.swift.model.mx.MxType"%>
<html>
    <body>
        <h1>New MX</h1>
        <a href="/">back</a>
        <p>Select an MX from the list below to generate its web entry form</p>
        <% for (MxType type : MxType.values()) { %>
			<a href="/mx?type=<%=type.name()%>"><%=type.name()%></a><br />		
        <% } %>
    </body>
</html>