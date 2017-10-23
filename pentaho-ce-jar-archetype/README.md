## pentaho-ce-jar-archetype

A project archetype for creating the structure of a new java project within the Pentaho ecosystem.

### Example usage:

```
$ mvn archetype:generate                              \
     -DarchetypeGroupId=org.pentaho                   \
     -DarchetypeArtifactId=pentaho-ce-jar-archetype   \
     -DarchetypeVersion=1.0.4                         \
     -DgroupId=org.pentaho                            \
     -DartifactId=my-pentaho-project                  \
     -Dversion=1.0.0-SNAPSHOT                         \
     -Dpackage=org.pentaho.my.project

$ cd my-pentaho-project

$ mvn clean install
