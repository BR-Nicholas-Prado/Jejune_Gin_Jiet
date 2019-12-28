/** see license for release terms */
package ws.nzen.runtime.docker.jgj_legacy;

import ws.nzen.runtime.docker.Mapping;
import ws.nzen.runtime.docker.jgj.BaseContainer;
import ws.nzen.runtime.docker.jgj.ContainerType;

/**  */
public class BrSpringBootContainer
		extends BaseContainer
{
	private String executableName;


	@Override
	public BaseContainer adoptDependency(
			BaseContainer another )
	{
		if ( another.getOwnType() == ContainerType.RABBIT_MQ )
		{
			for ( Mapping portPair : another.getPorts() )
			{
				if ( (Integer)portPair.inside == 5672 )
				{
					Mapping rmqPort = new Mapping();
					rmqPort.outside = "SPRING_RABBITMQ_PORT";
					rmqPort.inside = portPair.outside;
					break;
				}
			}
		}
		else if ( another.getOwnType() == ContainerType.MONGO_DB )
		{
			
		}
		// else if access registry

		return this;
	}

}
