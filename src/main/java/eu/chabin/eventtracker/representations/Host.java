package eu.chabin.eventtracker.representations;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import java.util.UUID;

public class Host {
	private final int id;
	
	@NotBlank
	@Length(min=2, max=255)
	private final String hostName;
	
	@Length(min=2, max=255)
	private final String hostDescription;
	
	@NotBlank
	private final String hostkey;

	public Host() {
		this.id = 0;
		this.hostName = null;
		this.hostDescription = null;
		this.hostkey = UUID.randomUUID().toString();
	}

	public Host(int id, String hostName, String hostDescription, String hostKey) {
		this.id = id;
		this.hostName = hostName;
		this.hostDescription = hostDescription;
		this.hostkey = hostKey;
	}

	public int getId() {
		return id;
	}

	public String getHostName() {
		return hostName;
	}

	public String getHostDescription() {
		return hostDescription;
	}

	public String getHostKey() {
		return hostkey;
	}
}