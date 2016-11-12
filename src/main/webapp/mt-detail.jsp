<%@page import="com.prowidesoftware.swift.model.MtSwiftMessage"%>
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
	MtSwiftMessage msg = (MtSwiftMessage) request.getSession().getAttribute(type.name());
%>
        <h1><%=type%></h1>
        <a href="mt?type=<%=type%>">edit</a>
        <a href="mt">close</a>
        <% 
        	MtFormBuilder builder = new MtFormBuilder();
        	builder.writeMTDetail(out, msg);
        %>
        <h2>SWIFT</h2>
        <pre><%=msg.message()%></pre>
    </body>
</html>