/** see license for release terms */
package ws.nzen.runtime.docker.jgj.container;

import java.security.GeneralSecurityException;

import ws.nzen.runtime.docker.container.EnvironmentVariable;
import ws.nzen.runtime.docker.container.NetworkPort;
import ws.nzen.runtime.docker.container.PasswordCredential;
import ws.nzen.runtime.docker.jgj.BaseContainer;
import ws.nzen.runtime.docker.jgj.ContainerType;

/**  */
public class RabbitMq extends BaseContainer
{
	public static final NetworkPort CANON_PORT = new NetworkPort( 5_672 );
	public static final String CANON_IMAGE = "rabbitmq:3.7-alpine";
	private PasswordCredential login;


	public RabbitMq(
			String container,
			String username )
				throws GeneralSecurityException
	{
		dockerImage = CANON_IMAGE;
		ownType = ContainerType.RABBIT_MQ;
		if ( container == null || container.isEmpty() )
			throw new NullPointerException( "name must be chars" );
		else
			name = container;
		hostName = container;
		login = new PasswordCredential( username );
		addEnviroVar( new EnvironmentVariable(
				"RABBITMQ_DEFAULT_USER",
				login.getUsername() ) );
		addEnviroVar( new EnvironmentVariable(
				"RABBITMQ_DEFAULT_PASS",
				login.getUsername() ) );
	}


	public RabbitMq(
			String container,
			String username,
			String image )
				throws GeneralSecurityException
	{
		this( container, username );
		if ( image == null || image.isEmpty() )
			throw new NullPointerException( "image must not be blank" );
		dockerImage = image;
	}


	public PasswordCredential getLogin()
	{
		return login;
	}


}
