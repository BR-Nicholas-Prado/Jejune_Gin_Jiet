/** see license for release terms */
package ws.nzen.runtime.docker.jgj;

import java.util.List;

/**  */
public class JgjTask
{
	private String name;
	private JgjTaskType taskType;
	private List<BaseContainer> relevant;


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
