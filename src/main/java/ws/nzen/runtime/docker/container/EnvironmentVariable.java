/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker.container;

/**  */
public class EnvironmentVariable
{
	private String name;
	private Object referent;

	/**  */
	public EnvironmentVariable( String handle, Object value )
	{
		if ( handle == null || handle.isEmpty() )
			throw new NullPointerException( "name must be chars" );
		name = handle;
		referent = value;
	}


	@Override
	public String toString()
	{
		return name +"="+ referent;
	}


	public String getName()
	{
		return name;
	}

	public Object getReferent()
	{
		return referent;
	}

}


















