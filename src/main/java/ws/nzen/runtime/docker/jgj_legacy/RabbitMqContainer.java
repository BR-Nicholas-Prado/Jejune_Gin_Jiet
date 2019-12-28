/** see license for release terms */
package ws.nzen.runtime.docker.jgj_legacy;

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

import ws.nzen.runtime.docker.jgj.BaseContainer;

/**  */
public class RabbitMqContainer extends BaseContainer
{
	private final Logger outChannel = LoggerFactory.getLogger( RabbitMqContainer.class );
	private String username;
	private String password;


	/** Replaces current password with a new one 
	 * @throws GeneralSecurityException */
	public void genPassword() throws GeneralSecurityException
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
	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword( String password )
	{
		this.password = password;
	}


}
