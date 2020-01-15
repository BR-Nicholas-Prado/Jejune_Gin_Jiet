/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker.container;

/**  */
public class VolumeMapping
{

	String outside;
	String inside;

	/**  */
	public VolumeMapping( String outer, String inner )
	{
		if ( outer == null || outer.isEmpty() )
			throw new NullPointerException( "outside name must be chars" );
		else if ( inner == null || inner.isEmpty() )
			throw new NullPointerException( "inside name must be chars" );
		outside = outer;
		inside = inner;
	}


	@Override
	public String toString()
	{
		return outside +":"+ inside;
	}


	public String getOutside()
	{
		return outside;
	}

	public String getInside()
	{
		return inside;
	}

}


















