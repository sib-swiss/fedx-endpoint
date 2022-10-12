package swiss.sib.swissprot.readonly;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@ConstructorBinding
@ConfigurationProperties("sail.readonly")
@Validated
public class FedXConfig {

	@NonNull
	public final String datadir;

	public FedXConfig(String datadir) {
		super();
		this.datadir = datadir;
	}

}
