/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  */
public class JgJiet
{
	private final Logger outChannel = LoggerFactory.getLogger( JgJiet.class );
	private String siteName = "";
	private List<BaseContainer> containers = new ArrayList<>();
	private List<ContainerDependence> dependencies = new ArrayList<>();
	private List<JgjTask> recipe = new LinkedList<>();


	/** resolves or throws RuntimeException if dependee
	* of the specified name isn't defined. */
	public void resolveDependencies()
	{
		for ( ContainerDependence currPair : dependencies )
		{
			if ( currPair.getDepender() != null && currPair.getDependee() != null )
				continue; // NOTE already resolved
			if ( currPair.getDependee() == null )
			{
				boolean found = false;
				for ( BaseContainer dependeeCandidate : containers )
				{
					if ( dependeeCandidate.getName().equals( currPair.getDependeeName() ) )
					{
						currPair.setDependee( dependeeCandidate );
						found = true;
						break;
					}
				}
				if ( ! found )
					throw new RuntimeException(
							"Dependee with name "
							+ currPair.getDependeeName()
							+" for depender "
							+ currPair.getDependerName() );
			}
			if ( currPair.getDepender() == null )
			{
				boolean found = false;
				for ( BaseContainer dependerCandidate : containers )
				{
					if ( dependerCandidate.getName().equals( currPair.getDependerName() ) )
					{
						currPair.setDepender( dependerCandidate );
						found = true;
						break;
					}
				}
				if ( ! found )
					throw new RuntimeException(
							"Depender with name "
							+ currPair.getDependerName()
							+" for dependee "
							+ currPair.getDependeeName() );
			}
		}
	}


	public void resolveTasks()
	{
		for ( JgjTask task : recipe )
		{
			task.resolve();
		}
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


	public void addTask(
			JgjTask step, Collection<String> namesOfRelevantContainers )
	{
		if ( step == null || step.getTaskType() == null )
			return;
		for ( JgjTask existing : recipe )
		{
			if ( existing.getTaskType() == step.getTaskType() )
				return;
		}
		for ( String desired : namesOfRelevantContainers )
		{
			for ( BaseContainer candidate : containers )
			{
				if ( candidate.getName().equals( desired ) )
				{
					step.addRelevantContainer( candidate );
					break;
				}
			}
		}
		recipe.add( step );
	}


	public String getSiteName()
	{
		return siteName;
	}
	public void setSiteName( String siteName )
	{
		this.siteName = siteName;
	}

}














































