/*
 * This code has a Hard-Coded Incoming Password CWE-259 vulnerability.
 * http://cwe.mitre.org
 * The password to know if the user is authorized to do high-level work 
 * ("admin"), is built into the code. 
 * The problems are that the password can be read from a copy of the
 * byte code, the password cannot be easily changed, and every copy
 * of the code uses the same password.
 */


import java.io.*;
import java.util.logging.Logger;


public class HardCodedPassword_259
{
	public  HardCodedPassword_259()
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
		    
			// BUG 
			// The password to grant access is here in the code: admin
		    if( ( s.equals( "admin" ) ) == true )
		    {
		    	highlevel_authorized( s );
		    }
		}
		catch ( IOException e )
		{
			final Logger logger = Logger.getAnonymousLogger();
        	String exception = "Exception " + e;
        	logger.warning( exception );
		}
	}
	   
	static    int  highlevel_authorized( String parm )
	{
	    // This user is authorized to do high level work
	    return 1;
	}
	
	public static void main( String[] argv )
	{
		new HardCodedPassword_259();
	}
}

// end of HardCodedPassword_259.java
