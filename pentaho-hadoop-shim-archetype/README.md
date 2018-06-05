## Hitachi Vantara Hadoop Shim Archetype

A project archetype for creating the structure of a new shim project within the Hitachi Vantara ecosystem.

### Creation of Community Edition (ce) shims from archetypes

To create ce shims from archetypes please use the following commands:

 - CDH Shim:
```
mvn archetype:generate -B \
-DarchetypeGroupId=org.hitachivantara.hadoop.shim \
-DarchetypeArtifactId=cdh-archetype \
-DarchetypeVersion=9.0.0.0-SNAPSHOT \
-Dversion=9.0.0.0-SNAPSHOT \
-DshimName=cdh513 \
-DshimVersion=5.13.x
```

 - HDP Shim:
```
mvn archetype:generate -B \
-DarchetypeGroupId=org.hitachivantara.hadoop.shim \
-DarchetypeArtifactId=hdp-archetype \
-DarchetypeVersion=9.0.0.0-SNAPSHOT \
-Dversion=9.0.0.0-SNAPSHOT \
-DshimName=hdp270 \
-DshimVersion=2.7.x
```

 - HDI Shim:
```
mvn archetype:generate -B \
-DarchetypeGroupId=org.hitachivantara.hadoop.shim \
-DarchetypeArtifactId=hdi-archetype \
-DarchetypeVersion=9.0.0.0-SNAPSHOT \
-Dversion=9.0.0.0-SNAPSHOT \
-DshimName=hdi36 \
-DshimVersion=3.6.x
```

 - EMR Shim:
```
mvn archetype:generate -B \
-DarchetypeGroupId=org.hitachivantara.hadoop.shim \
-DarchetypeArtifactId=emr-archetype \
-DarchetypeVersion=9.0.0.0-SNAPSHOT \
-Dversion=9.0.0.0-SNAPSHOT \
-DshimName=emr56 \
-DshimVersion=5.11.x
```

 - MapR Shim:
```
mvn archetype:generate -B \
-DarchetypeGroupId=org.hitachivantara.hadoop.shim \
-DarchetypeArtifactId=mapr-archetype \
-DarchetypeVersion=9.0.0.0-SNAPSHOT \
-Dversion=9.0.0.0-SNAPSHOT \
-DshimName=mapr60 \
-DshimVersion=6.0.x
```
*where emr511 etc - new name for shim, 5.11.x etc - human readable version used in config.properties*
