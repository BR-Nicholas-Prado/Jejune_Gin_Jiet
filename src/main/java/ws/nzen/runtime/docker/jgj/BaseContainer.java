
package ws.nzen.runtime.docker.jgj;

/**  */
public class BaseContainer
{
	private String name;
	private String executableName;
	private String dockerImage;
	private int port;
	private ContainerType ownType;


	public String getName()
	{
		return name;
	}
	public void setName( String name )
	{
		this.name = name;
	}
	public String getExecutableName()
	{
		return executableName;
	}
	public void setExecutableName( String executableName )
	{
		this.executableName = executableName;
	}
	public String getDockerImage()
	{
		return dockerImage;
	}
	public void setDockerImage( String dockerImage )
	{
		this.dockerImage = dockerImage;
	}
	public int getPort()
	{
		return port;
	}
	public void setPort( int port )
	{
		this.port = port;
	}
	public ContainerType getOwnType()
	{
		return ownType;
	}
	public void setOwnType( ContainerType ownType )
	{
		this.ownType = ownType;
	}



}
