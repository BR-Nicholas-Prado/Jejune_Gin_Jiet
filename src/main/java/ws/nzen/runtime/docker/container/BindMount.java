/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker.container;

import java.nio.file.Path;

import ws.nzen.runtime.docker.BindPropagation;
import ws.nzen.runtime.docker.MountType;

/**  */
public class BindMount
{
	private MountType style = MountType.BIND;
	private Path source;
	private boolean sourceIsRelativeToWorkingDir = false;
	private Path destination;
	private boolean writable = true; // aka read only
	private BindPropagation propagation = BindPropagation.R_PRIVATE;

	/**  */
	public BindMount(
			Path outsideFolder,
			Path insidFolder )
	{
		if ( outsideFolder == null )
			throw new NullPointerException( "outside must not be null" );
		source = outsideFolder;
		if ( insidFolder == null )
			throw new NullPointerException( "inside must not be null" );
		destination = insidFolder;
	}


	public BindMount(
			Path outsideFolder,
			boolean outsideUsesPwd,
			Path insidFolder )
	{
		this( outsideFolder, insidFolder );
		sourceIsRelativeToWorkingDir = outsideUsesPwd;
	}
	// and other variations overriding the other defaults


	public String toString()
	{ 
		StringBuilder fullInfomation = new StringBuilder();
		fullInfomation.append( "type=" );
		fullInfomation.append( style.name().toLowerCase() );
		fullInfomation.append( ",source=" );
		if ( sourceIsRelativeToWorkingDir )
			fullInfomation.append( "\"$(pwd)\"" );
		fullInfomation.append( source );
		fullInfomation.append( ",target=" );
		fullInfomation.append( destination );
		if ( ! writable )
			fullInfomation.append( ",readonly" );
		return fullInfomation.toString();
	}


	public Path getSource()
	{
		return source;
	}
	public void setSource( Path source )
	{
		this.source = source;
	}


	public boolean isSourceIsRelativeToWorkingDir()
	{
		return sourceIsRelativeToWorkingDir;
	}
	public void setSourceIsRelativeToWorkingDir(
			boolean sourceIsRelativeToWorkingDir )
	{
		this.sourceIsRelativeToWorkingDir = sourceIsRelativeToWorkingDir;
	}


	public Path getDestination()
	{
		return destination;
	}
	public void setDestination( Path destination )
	{
		this.destination = destination;
	}


}


















