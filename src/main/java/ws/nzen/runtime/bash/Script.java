/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.bash;

import java.util.ArrayList;
import java.util.List;

/** Inended to build up a bash script. Only rudimentary modelling. */
public class Script
{
	private List<String> lines = new ArrayList<>();

	/**  */
	public Script()
	{
		lines.add( "#!/usr/bin/env bash" );
	}


	public Script addCommand( String command )
	{
		lines.add( command );
		return this;
	}


	public Script addCommentedCommand( String command )
	{
		lines.add( "# " );
		lines.add( command );
		return this;
	}	


	public Script echoPreviousCommand()
	{
		for ( int ind = lines.size() -1; ind >= 0; ind-- )
		{
			if ( ! System.lineSeparator().equals( lines.get( ind ) ) )
			{
				lines.add( "echo "+ lines.get( ind ) );
				// NOTE possibly some escaping issues here
				return this;
			}
		}
		// else echo blank
		lines.add( "echo" );
		return this;
	}


	public Script pause()
	{
		lines.add( "sleep 1" );
		return this;
	}


	public Script newline()
	{
		lines.add( System.lineSeparator() );
		// NOTE I'm not modelling the earth, it's fine that this may not match the target
		return this;
	}


	public String toString()
	{
		StringBuilder result = new StringBuilder( lines.size() * 20 );
		for ( String line : lines )
		{
			result.append( line );
			result.append( System.lineSeparator() );
		}
		return result.toString();
	}

}


















