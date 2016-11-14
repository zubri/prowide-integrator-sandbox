<%@page import="com.prowidesoftware.swift.model.MtSwiftMessage"%>
<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MtServlet"%>
<%@page import="com.prowidesoftware.swift.gui.MtFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mt.MtType"%>
<html>
	<head>
	<%=ResourceServlet.includeHeaders(request)%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/xsd-gui.css"/>
	</head>
    <body>
<%
	MtType type = (MtType) request.getAttribute(MtServlet.TYPE);
	MtSwiftMessage msg = (MtSwiftMessage) request.getAttribute(type.name());
%>
        <h1><%=type%></h1>
        <a href="mt?type=<%=type%>" class="boton-link">back</a>
        <div class="message-detail">
            <% 
            	MtFormBuilder builder = new MtFormBuilder();
            	builder.writeMTDetail(out, msg);
            %>
        </div>
        <h2>SWIFT</h2>
        <pre><%=msg.message()%></pre>
    </body>
</html>