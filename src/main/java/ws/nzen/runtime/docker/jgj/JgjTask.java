/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ws.nzen.runtime.bash.Script;
import ws.nzen.runtime.docker.Runtime;

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
			Script lignuxInstructions = new Script();
			lignuxInstructions.addCommand( Runtime.shStopContainer( currContainer ) );
			lignuxInstructions.echoPreviousCommand();
			lignuxInstructions.addCommand( Runtime.shRemoveContainer( currContainer ) );
			lignuxInstructions.echoPreviousCommand();
			lignuxInstructions.addCommand( Runtime.shRunContainer( currContainer ) );

			// FIX let user eat it or define where or something
			outChannel.info( lignuxInstructions.toString() );
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
