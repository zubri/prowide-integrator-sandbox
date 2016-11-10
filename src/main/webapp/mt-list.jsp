<%@page import="com.prowidesoftware.swift.model.mt.SRU2016MtType"%>
<html>
    <body>
        <h1>New MT</h1>
        <a href="/">back</a>
        <p>Select an MT from the list below to generate its web entry form</p>
        <% for (SRU2016MtType type : SRU2016MtType.values()) { %>
			<a href="/mt?type=<%=type.name()%>"><%=type.name()%></a><br />		
        <% } %>
    </body>
</html>