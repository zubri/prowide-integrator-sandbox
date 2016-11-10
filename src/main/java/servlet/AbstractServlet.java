package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AbstractServlet extends HttpServlet {

	protected void forward(HttpServletRequest req, HttpServletResponse resp, final String jsp) throws ServletException, IOException {
		req.getRequestDispatcher(req.getContextPath()+ jsp).forward(req, resp);
	}
}
