/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker;

import ws.nzen.runtime.docker.container.EnvironmentVariable;
import ws.nzen.runtime.docker.container.PortMapping;
import ws.nzen.runtime.docker.container.VolumeMapping;
import ws.nzen.runtime.docker.jgj.BaseContainer;

/** Represents Docker itself */
public class Runtime
{
	private static final String executableName = "docker";

	/**  */
	public Runtime()
	{
	}


	public static String shRunContainer( BaseContainer target )
	{
		StringBuilder entireCommand = new StringBuilder();
		entireCommand.append( executableName +" run "+ target.getName() );
		if ( ! target.getPorts().isEmpty() )
		{
			entireCommand.append( " -" );
			entireCommand.append( Constants.RUN_F_PORT );
			entireCommand.append( " " );
			for ( PortMapping portPair : target.getPorts() )
			{
				entireCommand.append( portPair.toString() );
			}
		}
		if ( ! target.getVolumes().isEmpty() )
		{
			entireCommand.append( " -" );
			entireCommand.append( Constants.RUN_F_VOLUME );
			entireCommand.append( " " );
			for ( VolumeMapping folderPair : target.getVolumes() )
			{
				entireCommand.append( folderPair.toString() );
			}
		}
		if ( ! target.getEnvironmentVariables().isEmpty() )
		{
			entireCommand.append( " -" );
			entireCommand.append( Constants.RUN_F_ENVIRONM_VAR );
			entireCommand.append( " " );
			for ( EnvironmentVariable namedPair : target.getEnvironmentVariables() )
			{
				entireCommand.append( namedPair.toString() );
			}
		}
		return entireCommand.toString();
	}


	public static String shStopContainer( BaseContainer target )
	{
		return executableName +" stop "+ target.getName();
	}


	public static String shRemoveContainer( BaseContainer target )
	{
		return executableName +" remove "+ target.getName();
	}

}


















