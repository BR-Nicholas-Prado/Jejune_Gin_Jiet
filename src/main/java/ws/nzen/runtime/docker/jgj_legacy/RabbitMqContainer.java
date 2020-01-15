/** see license for release terms */
package ws.nzen.runtime.docker.jgj_legacy;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ws.nzen.runtime.docker.container.EnvironmentVariable;
import ws.nzen.runtime.docker.container.NetworkPort;
import ws.nzen.runtime.docker.container.PasswordCredential;
import ws.nzen.runtime.docker.jgj.BaseContainer;
import ws.nzen.runtime.docker.jgj.ContainerType;

/**  */
public class RabbitMqContainer extends BaseContainer
{
	public static final NetworkPort CANON_PORT = new NetworkPort( 5_672 );
	public static final String CANON_IMAGE = "rabbitmq:3.7-alpine";
	private final Logger outChannel = LoggerFactory.getLogger( RabbitMqContainer.class );
	private PasswordCredential login;


	public RabbitMqContainer(
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
		login = new PasswordCredential( username );
		addEnviroVar( new EnvironmentVariable(
				"RABBITMQ_DEFAULT_USER",
				login.getUsername() ) );
		addEnviroVar( new EnvironmentVariable(
				"RABBITMQ_DEFAULT_PASS",
				login.getUsername() ) );
	}


	public PasswordCredential getLogin()
	{
		return login;
	}


}
