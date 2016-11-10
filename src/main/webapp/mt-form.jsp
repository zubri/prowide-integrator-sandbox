<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MtServlet"%>
<%@page import="com.prowidesoftware.swift.gui.MtFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mt.MtType"%>
<html>
	<head>
	<%=ResourceServlet.includeHeaders(request)%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/xsd-gui.css"/>
	</head>
    <body>
<%
	MtType type = (MtType) request.getAttribute(MtServlet.TYPE);
	MtFormBuilder builder = (MtFormBuilder) request.getAttribute(MtServlet.BUILDER);
%>
        <h1><%=type%></h1>
        <a href="mt">back</a>
        <% 
        	builder.writeMTForm(type, out, null);
        %>
    </body>
</html>