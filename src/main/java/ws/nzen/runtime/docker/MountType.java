/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker;

/**  */
public enum MountType
{
	/** Host folder shared with container */
	BIND,
	/** Docker managed folder */
	VOLUME,
	/** RAM serving as a folder */
	TMPFS;
}


















