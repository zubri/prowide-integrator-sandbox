<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.prowidesoftware.swift.model.MxSwiftMessage"%>
<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MxServlet"%>
<%@page import="com.prowidesoftware.swift.guitools.MxFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mx.MxType"%>
<html>
	<head>
		<%=ResourceServlet.includeHeaders(request)%>
		<link rel="stylesheet" href="/js/jquery-ui-1.12.1/jquery-ui.min.css">
		<script src="/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	    <script src="/js/forms.js"></script>
	    <script src="/js/mx-form.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	    <link rel="stylesheet" type="text/css" href="/css/xsd-gui.css"/>	
    </head>
    <body>
<%
	MxType type = (MxType) request.getAttribute(MxServlet.TYPE);
	MxSwiftMessage msg = (MxSwiftMessage) request.getSession().getAttribute(type.name());
	final String title = msg != null? "Edit "+type :"New "+type;
%>
        <h1><%=StringUtils.replace(title, "_", ".")%></h1>
        <div class="form-action">
        	<a href="mx
        	
        	
        	" class="boton-link">cancel</a>
            <button id="save">save</button>
        </div>
        
        <form method="POST" class="message-form">
        <% 
        	/*
        	 * create the builder and optionally customize the renderer
        	 */
        	MxFormBuilder builder = new MxFormBuilder();
        
        	/*
        	 * Create the form passing the existign message (if any) as parameter
        	 * so the form is pre filled with content (emulatting message modification)
        	 */
        	builder.writeMXForm(type, out, msg);
        %>
        </form>
    </body>
</html>