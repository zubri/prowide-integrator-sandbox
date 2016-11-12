<%@page import="com.prowidesoftware.swift.model.MtSwiftMessage"%>
<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MtServlet"%>
<%@page import="com.prowidesoftware.swift.gui.MtFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mt.MtType"%>
<html>
	<head>
	<%=ResourceServlet.includeHeaders(request)%>
	<link rel="stylesheet" href="/js/jquery-ui-1.12.1/jquery-ui.min.css">
	<script src="/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script src="/js/forms.js"></script>
	<script src="/js/mt-form.js"></script>
	<link rel="stylesheet" type="text/css" href="/xsd-gui.css"/>
	</head>
    <body>
<%
	MtType type = (MtType) request.getAttribute(MtServlet.TYPE);
	MtSwiftMessage msg = (MtSwiftMessage) request.getSession().getAttribute(type.name());
	final String title = msg != null? "Edit "+type :"New "+type;
%>
        <h1><%=title%></h1>
        <a href="mt">back</a>
        <form method="POST">
        <% 
        	/*
        	 * create the builder and optionally customize the renderer
        	 */
        	MtFormBuilder builder = new MtFormBuilder();
        
        	/*
        	 * Create the form passing the existign message (if any) as parameter
        	 * so the form is pre filled with content (emulatting message modification)
        	 */
        	builder.writeMTForm(type, out, msg);
        %>
        <button id="validate">validate</button>
        <button formnovalidate="formnovalidate" id="save">save</button>
        </form>
    </body>
</html>