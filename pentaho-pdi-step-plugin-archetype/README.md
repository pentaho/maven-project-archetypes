##pdi-step-plugin-archetype

To use this to create a new PDI step plugin project you could then do something like this:

```
mvn archetype:generate \
 -DarchetypeGroupId=org.pentaho \
 -DarchetypeArtifactId=pdi-step-plugin-archetype \
 -DarchetypeVersion=2.20-SNAPSHOT \
 -DgroupId=org.pentaho.di.plugins \
 -DartifactId=mystep \
 -Dversion=1.0-SNAPSHOT \
 -Dplugin_class_name=MyStep \
 -Dplugin_name="My Step Plugin" \
 -Dplugin_category=Transform \
 -Dplugin_description="This is what my step does."
```

```
$ cd mystep
$ mvn clean install
```

To deploy copy the generated "assembly/target/mystep-plugin-1.0-SNAPSHOT.kar" into "data-integration\system\karaf\deploy"
