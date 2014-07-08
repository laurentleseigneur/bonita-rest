##install restx:
http://restx.io/docs/install.html

##fixing missing dependencies in private repos with restx app run:
mvn dependency:copy-dependencies

install a 6.3.1 bundle

##before run:

change bonita home path in PlateformResource 

change bonita-client.properties (for testing)
 

	# LOCAL
	#org.bonitasoft.engine.api-type = LOCAL
	
	# HTTP
	org.bonitasoft.engine.api-type = HTTP
	server.url = http://localhost:8080
	application.name = bonita
	
	# Remote: EJB3 / JBoss 5
	#org.bonitasoft.engine.api-type = EJB3
	#java.naming.factory.initial = org.jnp.interfaces.NamingContextFactory
	#java.naming.provider.url = jnp://localhost:1099

