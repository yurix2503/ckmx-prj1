/*
 * This code implements an OS Command Injection CWE-78 vulnerability.
 * http://cwe.mitre.org
 * It tries to execute a system command which is read in the inputBuffer
 * without validation.
 */

 
import java.io.*;
import java.util.logging.Logger;

public class OSCommandInjection_078
{
	public OSCommandInjection_078()
	{
		byte inputBuffer[] = new byte[ 128 ];
		try
		{
			// Read data from the standard input
			int byteCount = System.in.read( inputBuffer );

			// Check whether data has been read or not
			if( byteCount <= 0 )
			{
				return;
			}

			// Turn data into a String and try to execute it as
			// a system command
			String file = new String( inputBuffer );
			
			// BUG
			Process p = Runtime.getRuntime().exec( "ls " + file );
			// The string file is not validated before the execution

		}
		catch( IOException e )
		{
			final Logger logger = Logger.getAnonymousLogger();
			String exception = "Exception " + e;
			logger.warning( exception );
		}
	}
	
	public static void main( String[] argv )
	{
		new OSCommandInjection_078();
	}
}

// end of OSCommandInjection_078.java
