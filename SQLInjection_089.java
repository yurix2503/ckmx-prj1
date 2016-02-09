/*
 * This servlet implements an SQL injection vulnerability
 * Parameters:
 *   - name: source of the vulnerability
 * Example:
 *   - url: http://server_address/path_to_servlet/SQLInjection_089?name=' OR ''='
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class SQLInjection_089 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public SQLInjection_089()
    {
        super();
    }
	
	// Method which will be called to handle HTTP GET requests
	protected void doGet ( HttpServletRequest req, HttpServletResponse resp )
    	throws ServletException, IOException
	{
		Connection conn = null;

		// Get the parameter "name" from the data provided by the user
		String name = req.getParameter ("name");

		// Initialize the output stream
		resp.setContentType ("text/html");
		ServletOutputStream out = resp.getOutputStream ();
		out.println ("<HTML><BODY><blockquote><pre>");

		try
		{
			// Set the context factory to use to create the initial context
			System.setProperty (Context.INITIAL_CONTEXT_FACTORY, "your.ContextFactory");

			// Create the initial context and use it to lookup the data source
			InitialContext ic = new InitialContext ();
			DataSource dataSrc = (DataSource) ic.lookup ("java:comp/env/jdbc:/mydb");

			// Create a connection to the SQL database from the data source
			conn = dataSrc.getConnection ();

			// Send an unsanitized SQL request to the database, which may result in SQL injection
			conn.prepareStatement ("SELECT * FROM users WHERE firstname LIKE '" +
		       name + "'").executeQuery ();
		}
		catch (NamingException e)
	    {
			out.println ("Naming exception");
	    }
	    catch (SQLException e)
	    {
	    	out.println ("SQL exception");
	    }
	    finally
	    {
	    	try
	    	{
	    		if (conn != null)
	    			conn.close ();
	    	}
	    	catch (SQLException se)
	    	{
	    		out.println("SQL Exception");
	    	}
	    }

	    out.println ("</pre></blockquote></body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
	}
}
