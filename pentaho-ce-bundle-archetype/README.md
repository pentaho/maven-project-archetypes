## pentaho-ce-bundle-archetype

A project archetype for creating the structure of a new osgi bundle project within the Pentaho ecosystem.

### Example usage:

```
$ mvn archetype:generate                                \
     -DarchetypeGroupId=org.pentaho                     \
     -DarchetypeArtifactId=pentaho-ce-bundle-archetype  \
     -DarchetypeVersion=1.0.0                           \
     -DgroupId=org.pentaho                              \
     -DartifactId=my-pentaho-project                    \
     -Dversion=1.0.0-SNAPSHOT                           \
     -Dpackage=org.pentaho.my.project

$ cd my-pentaho-project

$ mvn clean install
