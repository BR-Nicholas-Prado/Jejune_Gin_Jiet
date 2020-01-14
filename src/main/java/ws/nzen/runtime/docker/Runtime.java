/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker;

import ws.nzen.runtime.docker.jgj.BaseContainer;

/** Represents Docker itself */
public class Runtime
{
	private static final String executableName = "docker";

	/**  */
	public Runtime()
	{
		// TODO Auto-generated constructor stub
	}


	public static String shRunContainer( BaseContainer target )
	{
		StringBuilder entireCommand = new StringBuilder();
		entireCommand.append( executableName +" run"+ target.getName() );
		if ( ! target.getPorts().isEmpty() )
		{
			entireCommand.append( " -" );
			entireCommand.append( Constants.RUN_F_PORT );
			for ( Mapping portPair : target.getPorts() )
			{
				entireCommand.append( portPair.outside );
				entireCommand.append( ":" );
				entireCommand.append( portPair.inside );
			}
		}
		return entireCommand.toString();
	}


	public static String shStopContainer( BaseContainer target )
	{
		return executableName +" stop"+ target.getName();
	}


	public static String shRemoveContainer( BaseContainer target )
	{
		return executableName +"remove"+ target.getName();
	}

}


















