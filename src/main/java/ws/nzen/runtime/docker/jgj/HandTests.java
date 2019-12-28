/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ws.nzen.runtime.docker.jgj_legacy.RabbitMqContainer;

/**  */
public class HandTests
{
	public static void main( String[] args )
	{
		HandTests mock = new HandTests();
		mock.checkPasswordDeal();
	}


	private final Logger outChannel = LoggerFactory.getLogger( HandTests.class );

    public void checkPasswordDeal()
    {
		RabbitMqContainer rmq = new RabbitMqContainer();
		try
		{
			rmq.genPassword();
		}
		catch ( GeneralSecurityException e )
		{
			// ASK Auto-generated catch block
			e.printStackTrace();
		}
		outChannel.info( rmq.getPassword() );
    }

}






























