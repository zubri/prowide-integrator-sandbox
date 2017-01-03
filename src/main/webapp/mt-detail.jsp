<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="servlet.SessionHelper"%>
<%@page import="com.prowidesoftware.swift.model.MtSwiftMessage"%>
<%@page import="com.prowidesoftware.swift.guitools.servlet.ResourceServlet"%>
<%@page import="servlet.MtServlet"%>
<%@page import="com.prowidesoftware.swift.guitools.MtFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mt.MtType"%>
<html>
	<head>
	<%=ResourceServlet.includeHeaders(request)%>
	<script src="/js/mt-form.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	</head>
    <body>
<%
	MtType type = (MtType) request.getAttribute(MtServlet.TYPE);
	MtSwiftMessage msg = SessionHelper.load(request, type);
%>
        <h1><%=StringUtils.replace(type.name(), "_", " ")%></h1>
        <a href="mt" class="boton-link">close</a>
        <a href="mt?type=<%=type%>" class="boton-link">edit</a>
        
        <div class="message-detail">
            <% 
	            if (msg != null) {
	            	MtFormBuilder builder = new MtFormBuilder();
	        		builder.writeMTDetail(out, msg);
	        		out.write("<h2>SWIFT</h2>");
	                out.write("<pre>"+ msg.message() +"</pre>");
	        	} else {
	        		out.write("Internal error rendering message");
	        	}            
	        %>
        </div>
    </body>
</html>