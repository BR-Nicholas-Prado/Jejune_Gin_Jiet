/** see license for release terms */
package ws.nzen.runtime.docker.jgj.container;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.nio.file.Path;
import java.security.GeneralSecurityException;

import ws.nzen.runtime.docker.container.EnvironmentVariable;
import ws.nzen.runtime.docker.container.NetworkPort;
import ws.nzen.runtime.docker.container.PasswordCredential;
import ws.nzen.runtime.docker.container.PortMapping;
import ws.nzen.runtime.docker.jgj.BaseContainer;
import ws.nzen.runtime.docker.jgj.ContainerType;

/**  */
public class BrSpringBoot
		extends BaseContainer
{
	public static final NetworkPort CANON_PORT = new NetworkPort( 8_080 );
	public static final String CANON_IMAGE = "openjdk:8-jre-alpine";
	private Path executableName;
	private PasswordCredential login;


	public BrSpringBoot(
			String container,
			PasswordCredential sharedSecret,
			NetworkPort externalPort,
			Inet4Address hostAsIp4,
			Inet6Address hostAsIp6,
			Path toJar )
				throws GeneralSecurityException
	{
		dockerImage = CANON_IMAGE;
		ownType = ContainerType.SPRING_BOOT;
		if ( container == null || container.isEmpty() )
			throw new NullPointerException( "name must be chars" );
		else
			name = container;
		if ( sharedSecret == null )
			login = new PasswordCredential( "" );
		else
			login = sharedSecret;
		hostName = container;
		addEnviroVar( new EnvironmentVariable(
				"MICROWORX_SECURITY_SHARED_HASH",
				login.getPassword() ) );
		addEnviroVar( new EnvironmentVariable( "SERVER_PORT", CANON_PORT ) );
		addPortMapping( new PortMapping( externalPort, CANON_PORT ) );
		addEnviroVar( new EnvironmentVariable(
				"MICROWORX_NETWORKING_LOCAL_EXTERNAL_PORT",
				externalPort ) );
		addEnviroVar( new EnvironmentVariable(
				"MICROWORX_NETWORKING_LOCAL_EXTERNAL_IPV4", hostAsIp4 ) );
		if ( hostAsIp6 != null )
			addEnviroVar( new EnvironmentVariable(
					"MICROWORX_NETWORKING_LOCAL_EXTERNAL_IPV6", hostAsIp6 ) );
		executableName = toJar;
	}


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
