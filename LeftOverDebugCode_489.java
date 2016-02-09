/*
 * This code has a Leftover Debug Code CWE-489 vulnerability.
 * http://cwe.mitre.org
 * Basically developer can debug his code and get the root promotion
 * for debugging just by typing "-debug :root".
 */


import java.io.IOException;
import java.util.logging.Logger;


public class LeftOverDebugCode_489
{
	// The debug code
	static boolean debug = false;

	// Debug entry points here
	static void promote_root()
	{
		if ( debug )
		{
			// set root privileges
			// you are able to do root's stuff
		}
	}
	
	public LeftOverDebugCode_489()
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

			// Turn data into a String
			String s = new String( inputBuffer );
			s = s.substring( 0, byteCount-2 );

			int nbArg = byteCount - 2;
			if ( nbArg > 1 )
			{
                if ( s.contains( "-debug" ) == true )
                {
                	// Move to debugging mode
                    debug = true;
                        
                    // for debugging code and process, you need root rights
                    if ( s.length() >= 11 && s.contains( ":root" ) == true )
                    {
                            promote_root();   
                    }
                }
			}
		}
		catch ( IOException e )
		{
			final Logger logger = Logger.getAnonymousLogger();
			String exception = "Exception " + e;
			logger.warning( exception );
		}
	}

	public static void main( String[] argv )
	{
		new LeftOverDebugCode_489();
	}
}

//end of leftOverDebugCode_489.java
