<%@page import="servlet.SessionHelper"%>
<%@page import="com.prowidesoftware.swift.model.MtSwiftMessage"%>
<%@page import="com.prowidesoftware.xsd.html.gui.web.ResourceServlet"%>
<%@page import="servlet.MtServlet"%>
<%@page import="com.prowidesoftware.swift.guitools.MtFormBuilder"%>
<%@page import="com.prowidesoftware.swift.model.mt.MtType"%>
<html>
	<head>
		<%=ResourceServlet.includeHeaders(request)%>
		<link rel="stylesheet" href="/js/jquery-ui-1.12.1/jquery-ui.min.css">
		<script src="/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	    <script src="/js/forms.js"></script>
	    <script src="/js/mt-form.js"></script>
        <script src="/js/vendor/float-panel.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	    <link rel="stylesheet" type="text/css" href="/css/xsd-gui.css"/>
    </head>
    <body>
<%
	MtType type = (MtType) request.getAttribute(MtServlet.TYPE);
	MtSwiftMessage msg = SessionHelper.load(request, type);
	final String title = msg != null? "Edit "+type :"New "+type;
%>
        <h1><%=title%></h1>
        <div class="form-action float-panel">
            <div class="left-side">
        	   <a href="mt" class="boton-link">cancel</a>
            </div>
            <div class="right-side">
                <button id="validate" class="btn btn-default">validate</button>
                <button formnovalidate="formnovalidate" class="btn" id="save">save</button>
            </div>
        </div>
        
        <form method="POST" class="message-form">
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
        </form>
    </body>
</html>