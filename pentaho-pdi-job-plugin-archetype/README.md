##pdi-job-plugin-archetype

To use this to create a new PDI job plugin project you could then do something like this:

```
mvn archetype:generate \
 -DarchetypeGroupId=org.pentaho \
 -DarchetypeArtifactId=pdi-job-plugin-archetype \
 -DarchetypeVersion=2.21-SNAPSHOT \
 -DgroupId=org.pentaho.di.plugins \
 -DartifactId=myjob \
 -Dversion=1.0-SNAPSHOT \
 -Dplugin_class_name=MyJob \
 -Dplugin_name="My Job Plugin" \
 -Dplugin_category=General \
 -Dplugin_description="This is what my job does."
```

```
$ cd myjob
$ mvn clean install
```

To deploy copy the generated "assembly/target/myjob-plugin-1.0-SNAPSHOT.kar" into "data-integration\system\karaf\deploy"
