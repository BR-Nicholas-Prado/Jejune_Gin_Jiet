/**
 * 
 */
package ws.nzen.runtime.docker.jgj;

/**  */
public class ContainerDependence
{
	private BaseContainer dependee;
	private String dependeeName;
	private BaseContainer depender;
	private String dependerName;


	public BaseContainer getDependee()
	{
		return dependee;
	}
	public void setDependee( BaseContainer dependee )
	{
		this.dependee = dependee;
	}
	public String getDependeeName()
	{
		return dependeeName;
	}
	public void setDependeeName( String dependeeName )
	{
		this.dependeeName = dependeeName;
	}
	public BaseContainer getDepender()
	{
		return depender;
	}
	public void setDepender( BaseContainer depender )
	{
		this.depender = depender;
	}
	public String getDependerName()
	{
		return dependerName;
	}
	public void setDependerName( String dependerName )
	{
		this.dependerName = dependerName;
	}

}
