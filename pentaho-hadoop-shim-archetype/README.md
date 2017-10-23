# pentaho-hadoop-shim-archetype #
A project archetype for creating the structure of a new shim project within the Pentaho ecosystem.




#### Creation of Community Edition (ce) shims from archetypes

To create ce shims from archetypes please use the following commands:

 - CDH Shim:
```
mvn archetype:generate -B -DarchetypeGroupId=org.pentaho.hadoop.shim -DarchetypeArtifactId=cdh-archetype -DarchetypeVersion=1.0 -Dversion=8.0-SNAPSHOT -DshimName=cdh512 -DshimVersion=5.12.x
```

 - HDP Shim:
```
mvn archetype:generate -B -DarchetypeGroupId=org.pentaho.hadoop.shim -DarchetypeArtifactId=hdp-archetype -DarchetypeVersion=1.0 -Dversion=8.0-SNAPSHOT -DshimName=hdp270 -DshimVersion=2.7.x
```

 - HDI Shim:
```
mvn archetype:generate -B -DarchetypeGroupId=org.pentaho.hadoop.shim -DarchetypeArtifactId=hdi-archetype -DarchetypeVersion=1.0 -Dversion=8.0-SNAPSHOT -DshimName=hdi36 -DshimVersion=3.6.x
```

 - EMR Shim:
```
mvn archetype:generate -B -DarchetypeGroupId=org.pentaho.hadoop.shim -DarchetypeArtifactId=emr-archetype -DarchetypeVersion=1.0 -Dversion=8.0-SNAPSHOT -DshimName=emr56 -DshimVersion=5.6.x
```

 - MapR Shim:
```
mvn archetype:generate -B -DarchetypeGroupId=org.pentaho.hadoop.shim -DarchetypeArtifactId=mapr-archetype -DarchetypeVersion=1.0 -Dversion=8.0-SNAPSHOT -DshimName=mapr530 -DshimVersion=5.3.x
```
*where emr56 etc - new name for shim, 5.6.x etc - human readable version used in config.properties*



__IntelliJ__

* Don't use IntelliJ's built-in maven. Make it use the same one you use from the commandline.
  * Project Preferences -> Build, Execution, Deployment -> Build Tools -> Maven ==> Maven home directory
````
