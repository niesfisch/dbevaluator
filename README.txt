1. install maven
2. mkdir playground; cd playgroud
3. svn checkout http://couchdb4j.googlecode.com/svn/trunk/ couchdb4j-read-only
4. mvn clean install -DskipTests -f couchdb4j-read-only/pom.xml

5. svn checkout http://dbevaluator.googlecode.com/svn/trunk/ dbevaluator-read-only
6. mvn clean install -DskipTests -f dbevaluator-read-only/pom.xml
 
