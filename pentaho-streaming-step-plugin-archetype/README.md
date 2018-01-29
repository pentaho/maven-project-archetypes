## PDI Step Plugin Archetype

The streaming-step-plugin-archetype can be used as a basis for any Pentaho Data Integration Step that inputs unbounded data.  It will extend from the Base Streaming classes in kettle engine.  The created step will do batch creating at user defined intervals.  Each batch runs in a defined sub transformation.

### Example Usage
```
mvn archetype:generate \
 -DarchetypeGroupId=org.pentaho \
 -DarchetypeArtifactId=streaming-step-plugin-archetype \
 -DarchetypeVersion=2.20 \
 -DgroupId=com.my.company \
 -DartifactId=my-step-plugin \
 -Dversion=1.0-SNAPSHOT \
 -Dplugin_class_name=MyStep \
 -Dplugin_name="My Step" \
 -Dplugin_description="This is what my step does."
 
$ cd my-step-plugin

$ mvn clean install
```
_Be sure to update the artifactId, version, and package arguments to reflect your particular project._

This will generate the following project structure:
```
my-step-plugin/
├── .gitignore
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── my
    │   │           └── company
    │   │               ├── MyStep.java
    │   │               ├── MyStepData.java
    │   │               ├── MyStepDialog.java
    │   │               ├── MyStepMeta.java
    │   └── resources
    │       ├── MyStep.svg
    │       ├── OSGI-INF
    │       │   └── blueprint
    │       │       └── blueprint.xml
    │       ├── com
    │       │   └── my
    │       │       └── company
    │       │           └── messages
    │       │               └── messages_en_US.properties
```

To deploy copy the generated "my-step-plugin-1.0-SNAPSHOT.jar" into "data-integration\system\karaf\deploy"
