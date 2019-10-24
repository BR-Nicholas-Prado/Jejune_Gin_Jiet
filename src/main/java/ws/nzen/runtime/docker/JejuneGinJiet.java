/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker;

import java.io.IOException;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**  */
@Command(
		description = "Run docker containers for BR",
		name = "Jejune Gin Jiet",
		version = "0.0-alpha.0.0+j8",
		mixinStandardHelpOptions = true )
public class JejuneGinJiet implements Runnable
{
	@Option(
			names = { "-c", "--config" },
			paramLabel = "config",
			description = "path to config file; ex ../etc/config.eno" )
	private String configFilePath = "config.eno";


	/** @param args */
	public static void main( String[] args )
	{
		int exitCode = new CommandLine( new JejuneGinJiet() ).execute( args );
		System.exit( exitCode );
	}

	/**  */
	public JejuneGinJiet()
	{
		// TODO Auto-generated constructor stub
	}


	public void run()
	{
		throw new RuntimeException( "JGJ not yet implemented" );
	}
	/*
	
	*/

}

















