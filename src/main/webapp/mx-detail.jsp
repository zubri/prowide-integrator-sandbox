<%@page import="com.prowidesoftware.swift.model.MxSwiftMessage"%>
<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MxServlet"%>
<%@page import="com.prowidesoftware.swift.gui.MxFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mx.MxType"%>
<html>
	<head>
	<%=ResourceServlet.includeHeaders(request)%>
	<link rel="stylesheet" type="text/css" href="/xsd-gui.css"/>
	</head>
    <body>
<%
	MxType type = (MxType) request.getAttribute(MxServlet.TYPE);
	MxSwiftMessage msg = (MxSwiftMessage) request.getSession().getAttribute(type.name());
%>
        <h1><%=type%></h1>
        <a href="mx?type=<%=type%>">edit</a>
        <a href="Mx">close</a>
        <% 
        	MxFormBuilder builder = new MxFormBuilder();
        	builder.writeMXDetail(out, msg);
        %>
        <h2>SWIFT</h2>
        <pre><%=msg.message()%></pre>
    </body>
</html>