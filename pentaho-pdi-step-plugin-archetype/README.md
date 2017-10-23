##pdi-step-plugin-archetype

To use this to create a new PDI step plugin project you could then do something like this:

```
mvn archetype:generate \
 -DarchetypeGroupId=org.pentaho \
 -DarchetypeArtifactId=pdi-step-plugin-archetype \
 -DgroupId=com.my.company \
 -DartifactId=my-step-plugin \
 -Dversion=2.1-SNAPSHOT \
 -Dplugin_class_name=MyStep \
 -Dplugin_name="My Step" \
 -Dplugin_category=Transform \
 -Dplugin_description="This is what my step does."

$ cd my-step-plugin

$ mvn clean install

To deploy copy the generated "my-step-plugin-2.0-SNAPSHOT.kar" into "data-integration\system\karaf\deploy"
