/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker.jgj.container;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Deprecated
/** Single value or map */
public class DockerRunArg
{
	private String singularValue = "";
	private Map<String, String> multiValues = new TreeMap<>();

	/**  */
	public DockerRunArg()
	{
		// TODO Auto-generated constructor stub
	}


	public Set<String> getMultiValKeys()
	{
		return multiValues.keySet();
	}


	public DockerRunArg addToMulti( String key, String value )
	{
		multiValues.put( key, value );
		return this;
	}


	public String getValOf( String key )
	{
		return multiValues.get( key );
	}


	public String getSingularValue()
	{
		return singularValue;
	}

	public void setSingularValue( String singularValue )
	{
		this.singularValue = singularValue;
	}

}


















