/* see ../../../../../LICENSE for release details */
package ws.nzen.runtime.docker.container;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;

/**  */
public class PasswordCredential
{
	private final Logger outChannel = LoggerFactory.getLogger( PasswordCredential.class );
	private String username;
	private String password;

	/** To accommodate passwords made elsewhere */
	public PasswordCredential( String user, String phrase )
	{
		if ( user == null || phrase == null )
		{
			throw new NullPointerException();
		}
		else
		{
			username = user;
			password = phrase;
		}
	}

	/** Creates password 
	 * @throws GeneralSecurityException */
	public PasswordCredential( String user ) throws GeneralSecurityException
	{
		if ( user == null )
		{
			throw new NullPointerException();
		}
		else
		{
			username = user;
			genPassword();
		}
	}


	/** Replaces current password with a new one 
	 * @throws GeneralSecurityException */
	private void genPassword() throws GeneralSecurityException
	{
		TinkConfig.register();
		KeysetHandle keyFactory = KeysetHandle.generateNew(
				AeadKeyTemplates.AES256_GCM );
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try
		{
			CleartextKeysetHandle.write(
					keyFactory,
					JsonKeysetWriter.withOutputStream( buffer ) );
			String jsonKeyStr = new String(
					buffer.toByteArray(),
					StandardCharsets.UTF_8 )
					.replace( "INFO HandTests - ", "" );
			JSONObject jEntireKey = new JSONObject( jsonKeyStr );
			JSONArray keysOf = jEntireKey.getJSONArray( "key" );
			JSONObject keyData = keysOf.getJSONObject( 0 )
					.getJSONObject( "keyData" );
			password = keyData.getString( "value" );
			/*
			example
			INFO HandTests - {
			    "primaryKeyId": 1660140934,
			    "key": [{
			        "keyData": {
			            "typeUrl": "type.googleapis.com/google.crypto.tink.AesGcmKey",
			            "keyMaterialType": "SYMMETRIC",
			            "value": "GiAfzn7ReQSucDxWM63Qw65+ja0brEbaWbb7FnyHNr+DZA=="
			        },
			        "outputPrefixType": "TINK",
			        "keyId": 1660140934,
			        "status": "ENABLED"
			    }]
			}
			*/
		}
		catch ( IOException ie )
		{
			outChannel.error( ie.toString() +" while gen password" );
			// FIX use better random bytes; probably Apache.text or whatever
			password = "";
			Random oracle = new Random();
			for ( int ind = 16; ind > 0; ind-- )
			{
				password += Integer.toString( oracle.nextInt( 100 ) );
			}
		}
	}


	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}


}


















