##install restx:

	http://restx.io/docs/install.html
	
	install restx plug in
	
	restx>shell install
	
	choose all plugin

##fixing missing dependencies in private repos with restx app run:

	mvn dependency:copy-dependencies
	
and then  compile

	restx>app compile
	restx>app run
	 
	
install a 6.3.1 bundle

## run:

	mvn clean install 

and then add the war in tomcat's webapps directory

navigate to /bonita-rest/api/@/ui/ 
 

