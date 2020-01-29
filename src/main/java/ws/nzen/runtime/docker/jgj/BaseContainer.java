
package ws.nzen.runtime.docker.jgj;

import java.util.LinkedList;
import java.util.List;

import ws.nzen.runtime.docker.RestartPolicy;
import ws.nzen.runtime.docker.container.BindMount;
import ws.nzen.runtime.docker.container.EnvironmentVariable;
import ws.nzen.runtime.docker.container.PortMapping;
import ws.nzen.runtime.docker.container.VolumeMapping;

/** Represents configuration of,for a Docker container */
public class BaseContainer
{
	protected String name;
	protected String dockerImage;
	protected String hostName;
	protected RestartPolicy restart = RestartPolicy.NO;
	protected ContainerType ownType;
	protected List<PortMapping> ports = new LinkedList<>();
	protected List<VolumeMapping> volumes = new LinkedList<>();
	protected List<BindMount> hostMounts = new LinkedList<>();
	protected List<EnvironmentVariable> environmentVariables = new LinkedList<>();


	// improve model ports so it's not trash
	public BaseContainer addPortMapping( PortMapping portCombo )
	{
		for ( PortMapping existing : ports )
		{
			if ( existing.getOutside().equals( portCombo.getOutside() ) )
				return this; // NOTE assuming it's already defined
		}
		ports.add( portCombo );
		return this;
	}
	// Improve removePortMapping()


	// improve a path version ?
	public BaseContainer addVolumeMapping( VolumeMapping volumeCombo )
	{
		for ( VolumeMapping existing : volumes )
		{
			if ( existing.getInside().equals( volumeCombo.getInside() ) )
				return this; // NOTE assuming it's already defined
		}
		volumes.add( volumeCombo );
		return this;
	}


	public BaseContainer addEnviroVar( EnvironmentVariable something )
	{
		environmentVariables.add( something );
		return this;
	}


	public BaseContainer addBindMount( BindMount mapping )
	{
		hostMounts.add( mapping );
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

	public String getHostName()
	{
		return hostName;
	}
	public void setHostName( String hostName )
	{
		this.hostName = hostName;
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


	public List<PortMapping> getPorts()
	{
		return ports;
	}


	public List<VolumeMapping> getVolumes()
	{
		return volumes;
	}


	public List<BindMount> getBindMounts()
	{
		return hostMounts;
	}


	public List<EnvironmentVariable> getEnvironmentVariables()
	{
		return environmentVariables;
	}




}



















