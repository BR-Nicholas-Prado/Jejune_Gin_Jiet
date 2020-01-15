/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker.container;

/**  */
public class PortMapping
{
	NetworkPort outside;
	NetworkPort inside;

	/**  */
	public PortMapping(
			NetworkPort outer, NetworkPort inner )
	{
		outside = outer;
		inside = inner;
	}

	public PortMapping( int outer, int inner )
	{
		this(
				new NetworkPort( outer ),
				new NetworkPort( inner ) );
	}


	@Override
	public String toString()
	{
		return outside +":"+ inside;
	}


	public NetworkPort getOutside()
	{
		return outside;
	}

	public NetworkPort getInside()
	{
		return inside;
	}

}


















