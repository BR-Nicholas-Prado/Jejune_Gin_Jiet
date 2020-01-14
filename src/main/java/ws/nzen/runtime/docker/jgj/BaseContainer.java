
package ws.nzen.runtime.docker.jgj;

import java.util.LinkedList;
import java.util.List;

import ws.nzen.runtime.docker.Mapping;
import ws.nzen.runtime.docker.RestartPolicy;

/** Represents configuration of,for a Docker container */
public class BaseContainer
{
	private String name;
	private String dockerImage;
	private RestartPolicy restart = RestartPolicy.NO;
	private ContainerType ownType;
	private List<Mapping> ports = new LinkedList<>();
	private List<Mapping> volumes = new LinkedList<>();
	private List<Mapping> environmentVariables = new LinkedList<>();


	// improve model ports so it's not trash
	public BaseContainer addPortMapping(
			int outside, int inside )
	{
		if ( outside < 1 )
			throw new RuntimeException( "outside port must be positive int" );
		else if ( inside < 1 )
			throw new RuntimeException( "inside port must be positive int" );
		Mapping portCombo = new Mapping();
		portCombo.outside = outside;
		portCombo.inside = inside;
		for ( Mapping existing : ports )
		{
			if ( existing.outside.equals( portCombo.outside ) )
				return this; // NOTE assuming it's already defined
		}
		ports.add( portCombo );
		return this;
	}
	// Improve removePortMapping()


	// improve a path version ?
	public BaseContainer addVolumeMapping(
			String outsideName, String insideName )
	{
		if ( outsideName == null || outsideName.isEmpty() )
			throw new RuntimeException( "outside name must be chars" );
		else if ( insideName == null || insideName.isEmpty() )
			throw new RuntimeException( "inside name must be chars" );
		Mapping volumeCombo = new Mapping();
		volumeCombo.outside = outsideName;
		volumeCombo.inside = insideName;
		for ( Mapping existing : volumes )
		{
			if ( existing.outside.equals( volumeCombo.outside ) )
				return this; // NOTE assuming it's already defined
		}
		volumes.add( volumeCombo );
		return this;
	}


	public BaseContainer addEnviroVar( Mapping something )
	{
		if ( something == null
				|| something.outside == null )
			throw new NullPointerException( "outside must not be null" );
		environmentVariables.add( something );
		return this;
	}


	/** Basically abstract method so container definitions can harvest
	 * each others ports and things. */
	public BaseContainer adoptDependency(
			BaseContainer another )
	{
		return this;
	}


	public String getName()
	{
		return name;
	}
	public void setName( String name )
	{
		this.name = name;
	}

	public String getDockerImage()
	{
		return dockerImage;
	}
	public void setDockerImage( String dockerImage )
	{
		this.dockerImage = dockerImage;
	}

	public ContainerType getOwnType()
	{
		return ownType;
	}
	public void setOwnType( ContainerType ownType )
	{
		this.ownType = ownType;
	}

	public RestartPolicy getRestart()
	{
		return restart;
	}
	public void setRestart( RestartPolicy restart )
	{
		this.restart = restart;
	}


	public List<Mapping> getPorts()
	{
		return ports;
	}


	public List<Mapping> getVolumes()
	{
		return volumes;
	}




}



















