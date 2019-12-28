/** see license for release terms */
package ws.nzen.runtime.docker;

/**  */
public enum RestartPolicy
{
	ALWAYS,
	ON_FAILURE,
	UNLESS_STOPPED,
	NO;
}
