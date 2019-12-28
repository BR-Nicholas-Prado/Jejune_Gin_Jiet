/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ws.nzen.runtime.docker.Constants;
import ws.nzen.runtime.docker.Mapping;

/**  */
public class JgjTask
{
	private final Logger outChannel = LoggerFactory.getLogger( JgjTask.class );
	private String name;
	private JgjTaskType taskType;
	private List<BaseContainer> relevant = new LinkedList<>();
	// private list task steps recipe // ex stop rm echo run cp


	public void resolve()
	{
		// IMPROVE extract to more specific version
		if ( taskType != JgjTaskType.BASH_SCRIPT )
			return;
		for ( BaseContainer currContainer : relevant )
		{
			outChannel.info( "#!/bin/sh" );
			outChannel.info( System.lineSeparator() );
			outChannel.info( "docker stop "+ currContainer.getName() );
			outChannel.info( "docker rm "+ currContainer.getName() );
			StringBuilder runArgs = new StringBuilder( 200 );
			if ( ! currContainer.getPorts().isEmpty() )
			{
				runArgs.append( " -" );
				runArgs.append( Constants.RUN_F_PORT );
				runArgs.append( " " );
				for ( Mapping port : currContainer.getPorts() )
				{
					runArgs.append( port.outside );
					runArgs.append( " " );
					runArgs.append( port.inside );
				}
			}
			outChannel.info( "docker run "+ runArgs.toString()
					+" "+ currContainer.getName() );
		}
	}


	public void addRelevantContainer( BaseContainer another )
	{
		if ( another == null )
			return;
		relevant.add( another );
	}


	public String getName()
	{
		return name;
	}
	public void setName( String name )
	{
		this.name = name;
	}
	public JgjTaskType getTaskType()
	{
		return taskType;
	}
	public void setTaskType( JgjTaskType taskType )
	{
		this.taskType = taskType;
	}
	public List<BaseContainer> getRelevant()
	{
		return relevant;
	}
	public void setRelevant( List<BaseContainer> relevant )
	{
		this.relevant = relevant;
	}

}
