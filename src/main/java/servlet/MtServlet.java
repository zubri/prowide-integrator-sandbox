package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prowidesoftware.swift.gui.MtFormBuilder;
import com.prowidesoftware.swift.model.mt.MtType;
import com.prowidesoftware.swift.model.mt.SRU2016MtType;

@WebServlet(
        name = "MtServlet",
        urlPatterns = {"/mt"}
    )
public class MtServlet extends AbstractServlet {
	private static final long serialVersionUID = 3151991653694521562L;

	public static String TYPE = "type";
	public static String BUILDER = "builder";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	final String typeParam = req.getParameter("type");
    	
    	if (typeParam == null) {
    		forward(req, resp, "mt-list.jsp");
    		
    	} else {
	        final MtType type = SRU2016MtType.valueOf(typeParam);
	    	final MtFormBuilder builder = new MtFormBuilder();
	        
	        req.setAttribute(TYPE, type);
	        req.setAttribute(BUILDER, builder);
	        
    		forward(req, resp, "mt-form.jsp");
        }
    }

}
