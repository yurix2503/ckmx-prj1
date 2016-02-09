/*
 * This code has a NULL Pointer Dereference CWE-476 vulnerability.
 * http://cwe.mitre.org
 * Here we try to get the system property from a specified key and
 * then remove whitespace. If the key is unknown, calling cmd gives
 * a NULL dereference exception.
 */


import java.util.logging.Logger;

public class NullPointerDereference_476
{
	public static void main( String[] argv )
	{
		try
		{
			// Gets the system property indicated by the specified key
			String cmd = System.getProperty( "java.class.path" );
			
			// BUG
			// Returns the string, with leading and trailing whitespace omitted
			cmd = cmd.trim();
			
			// If java.class.path is undefined, then return value of
			// systemProperty is undefined. So cmd is not defined.
			// Thus, calling cmd.trim() causes a NULL dereference exception.
		}
		catch ( RuntimeException re )
		{
			final Logger logger = Logger.getAnonymousLogger();
			String exception = "Exception " + re;
			logger.warning( exception );
		}
	}
}

// end of NullPointerDereference_476.java
