/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ws.nzen.runtime.docker.jgj.DockerRunArg;

/**  */
public class Container
{
	private String name = "";
	private String containerType = "";
	private String image = "";
	private List<String> recipe = new ArrayList<>();
	private Map<String, DockerRunArg> runArguments = new TreeMap<>();
	// improve ipv4 , ipv6

	/**  */
	public Container()
	{
		// TODO Auto-generated constructor stub
	}


	public Container addVolume( String outside, String inside )
	{
		return addMapArg( Constants.RUN_F_VOLUME, outside, inside );
	}


	public Container addEnvVar( String id, String value )
	{
		return addMapArg( Constants.RUN_F_ENVIRONM_VAR, id, value );
	}


	// TODO restart policy ; ports ; detachedness


	private Container addMapArg( String dockerKey, String key, String value )
	{
		if ( ! runArguments.containsKey( dockerKey ) )
		{
			DockerRunArg draType = new DockerRunArg();
			runArguments.put( dockerKey, draType );
		}
		DockerRunArg draType = runArguments.get( dockerKey );
		// assert not null
		draType.addToMulti( key, value );
		return this;
	}


	public Container addToRecipe( String shellCommand )
	{
		recipe.add( shellCommand );
		return this;
	}


	// TODO get recipe decorates shell commands , ie fills ^image^ and assembles ^run args^ 

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getContainerType()
	{
		return containerType;
	}

	public void setContainerType( String containerType )
	{
		this.containerType = containerType;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage( String image )
	{
		this.image = image;
	}

}


















