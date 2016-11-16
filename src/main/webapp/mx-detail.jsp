<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="servlet.SessionHelper"%>
<%@page import="com.prowidesoftware.swift.model.MxSwiftMessage"%>
<%@page import="com.prowidesoftware.swift.guitools.servlet.ResourceServlet"%>
<%@page import="servlet.MxServlet"%>
<%@page import="com.prowidesoftware.swift.guitools.MxFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mx.MxType"%>
<html>
	<head>
	<%=ResourceServlet.includeHeaders(request)%>
	<script src="/js/mt-form.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="/css/xsd-gui.css"/>
	</head>
    <body>
<%
	MxType type = (MxType) request.getAttribute(MxServlet.TYPE);
	MxSwiftMessage msg = SessionHelper.load(request, type);
%>
        <h1><%=StringUtils.replace(type.name(), "_", ".")%></h1>
        <a href="mx=<%=type%>" class="boton-link">close</a>
        <a href="mx?type=<%=type%>" class="boton-link">edit</a>
        <% 
        	if (msg != null) {
            	MxFormBuilder builder = new MxFormBuilder();
        		builder.writeMXDetail(out, msg);
        		out.write("<h2>SWIFT</h2>");
                out.write("<pre>"+ StringEscapeUtils.escapeHtml(msg.message()) +"</pre>");
        	} else {
        		out.write("Internal error rendering message");
        	}
        %>
    </body>
</html>