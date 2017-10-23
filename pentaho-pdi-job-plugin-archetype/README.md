##pdi-job-plugin-archetype

To use this to create a new PDI job plugin project you could then do something like this:

```
mvn archetype:generate \
 -DarchetypeGroupId=org.pentaho \
 -DarchetypeArtifactId=pdi-job-plugin-archetype \
 -DgroupId=com.my.company \
 -DartifactId=my-job-plugin \
 -Dversion=2.1-SNAPSHOT \
 -Dplugin_class_name=MyJob \
 -Dplugin_name="My Job" \
 -Dplugin_category=General \
 -Dplugin_description="This is what my job does."

$ cd my-job-plugin

$ mvn clean install

To deploy copy the generated "my-job-plugin-2.0-SNAPSHOT.kar" into "data-integration\system\karaf\deploy"
