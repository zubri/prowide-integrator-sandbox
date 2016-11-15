<%@page import="com.prowidesoftware.swift.model.MxSwiftMessage"%>
<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MxServlet"%>
<%@page import="com.prowidesoftware.swift.guitools.MxFormBuilder"%>
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
        	if (msg != null) {
        		builder.writeMXDetail(out, msg);
        		out.write("<h2>SWIFT</h2>");
                out.write("<pre>"+ msg.message() +"</pre>");
        	} else {
        		out.write("Internal error rendering message")
        	}
        %>
    </body>
</html>