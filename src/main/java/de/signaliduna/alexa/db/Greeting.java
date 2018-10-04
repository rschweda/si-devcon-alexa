package de.signaliduna.alexa.db;

import javax.persistence.*;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(
				name = "somename",
				query =  "SELECT :greeting"
		)
})
public class Greeting {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

}
