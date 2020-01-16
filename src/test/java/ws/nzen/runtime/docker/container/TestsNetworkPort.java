package ws.nzen.runtime.docker.container;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestsNetworkPort
{

	@Test
	void testNetworkPort()
	{
		shouldRejectZero();
		shouldRejectBiggerThanInt();
	}


	private void shouldRejectZero()
	{
		assertThrows(
				ArrayIndexOutOfBoundsException.class,
				() -> { new NetworkPort( 0 ); },
				"reject port below 1" );
	}


	private void shouldRejectBiggerThanInt()
	{
		assertThrows(
				ArrayIndexOutOfBoundsException.class,
				() -> { new NetworkPort( 67_000 ); },
				"reject port above 65K" );
	}

}


















