/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ws.nzen.runtime.docker.container.PasswordCredential;
import ws.nzen.runtime.docker.container.PortMapping;
import ws.nzen.runtime.docker.container.VolumeMapping;
import ws.nzen.runtime.docker.jgj_legacy.RabbitMqContainer;

/**  */
public class HandTests
{
	public static void main( String[] args )
	{
		HandTests mock = new HandTests();
		mock.checkJgjDeal();
		// mock.checkPasswordDeal();
	}


	private final Logger outChannel = LoggerFactory.getLogger( HandTests.class );


	public void checkJgjDeal()
	{
		RabbitMqContainer rmq = null;
		try
		{
			rmq = new RabbitMqContainer(
					"openjdk-rmq",
					"sudo" );
		}
		catch ( GeneralSecurityException e )
		{
			e.printStackTrace();
			return;
		}
		rmq.addPortMapping( new PortMapping(
				RabbitMqContainer.CANON_PORT,
				RabbitMqContainer.CANON_PORT ) );
		rmq.addVolumeMapping( new VolumeMapping( rmq.getName(), "/var/lib/rabbit" ) );
		Collection<BaseContainer> onlyOne = new LinkedList<>();
		onlyOne.add( rmq );
		Collection<String> names = new LinkedList<>();
		names.add( rmq.getName() );

		JgjTask bash = new JgjTask();
		bash.setTaskType( JgjTaskType.BASH_SCRIPT );

		JgJiet glue = new JgJiet();
		glue.addContainer( rmq );
		glue.addTask( bash, names );
		glue.resolveDependencies();
		glue.resolveTasks();
	}


	public void checkPasswordDeal()
    {
		PasswordCredential login;
		try
		{
			login = new PasswordCredential( "user" );
			outChannel.info( login.getPassword() );
		}
		catch ( GeneralSecurityException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}






























