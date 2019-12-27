/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  */
public class JgJiet
{
    private final Logger outChannel = LoggerFactory.getLogger( JgJiet.class );
	private List<BaseContainer> containers = new ArrayList<>();
	private List<ContainerDependence> dependencies = new ArrayList<>();
	private List<JgjTask> recipe = new LinkedList<>();


	/** resolves or throws  */
	public void resolveDependencies()
	{
		
		
	}


	public void addContainer( BaseContainer another )
	{
		if ( another == null )
			return;
		else if ( another.getName().isEmpty() )
		{
			// NOTE assign a name
			another.setName( LocalDateTime.now().toString() );
			outChannel.debug( "changed empty container name to "+ another.getName() );
			containers.add( another );
		}
		for ( BaseContainer existing : containers )
		{
			if ( existing.getName().equals( another.getName() ) )
			{
				existing = another;
				return;
			}
		}
		containers.add( another );
	}


	public void addDependence( ContainerDependence another )
	{
		if ( another == null
				|| ( another.getDependeeName().isEmpty() && another.getDependee() == null )
				|| ( another.getDependerName().isEmpty() && another.getDepender() == null ) )
		{
			outChannel.info( "rejected incomplete dependence" );
			return;
		}
		for ( ContainerDependence existing : dependencies )
		{
			if ( existing.getDependeeName().equals( another.getDependeeName() )
					&& existing.getDependerName().equals( another.getDependerName() ) )
				return;
		}
		dependencies.add( another );
	}


	public void addTask( JgjTask step )
	{
		if ( step == null || step.getTaskType() == null )
			return;
		for ( JgjTask existing : recipe )
		{
			if ( existing.getTaskType() == step.getTaskType() )
				return;
		}
		recipe.add( step );
	}
}














































