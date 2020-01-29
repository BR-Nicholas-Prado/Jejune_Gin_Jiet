/** see license for release terms */
package ws.nzen.runtime.docker.jgj.container;

import ws.nzen.runtime.docker.container.EnvironmentVariable;
import ws.nzen.runtime.docker.container.PortMapping;
import ws.nzen.runtime.docker.jgj.BaseContainer;
import ws.nzen.runtime.docker.jgj.ContainerType;

/**  */
public class BrSpringBoot
		extends BaseContainer
{
	private String executableName;


	@Override
	public BaseContainer adoptDependency(
			BaseContainer another )
	{
		if ( another.getOwnType() == ContainerType.RABBIT_MQ )
		{
			for ( PortMapping portPair : another.getPorts() )
			{
				if ( portPair.getInside().equals( RabbitMq.CANON_PORT ) )
				{
					EnvironmentVariable rmqPort = new EnvironmentVariable(
							"SPRING_RABBITMQ_PORT",
							portPair.getOutside() );
					addEnviroVar( rmqPort );
					break;
				}
			}
			// FIX add the credentials
		}
		else if ( another.getOwnType() == ContainerType.MONGO_DB )
		{
			
		}
		// else if access registry

		return this;
	}

}
