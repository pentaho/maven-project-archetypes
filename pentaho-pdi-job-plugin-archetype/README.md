## PDI Job Plugin Archetype

The pentaho-pdi-job-plugin-archetype can be used as a basis for any Pentaho Data Integration Job that produces a community edition kar. It will create the basic folder structure, provide you with a template README.md and a common .gitignore file.

### Example Usage
```
mvn archetype:generate                            \
 -DarchetypeGroupId=org.hitachivantara.archetypes \
 -DarchetypeArtifactId=pdi-job-plugin-archetype   \
 -DarchetypeVersion=9.0.0.0-SNAPSHOT              \
 -DgroupId=com.my.company                         \
 -DartifactId=my-job-plugin                       \
 -Dversion=1.0-SNAPSHOT                           \
 -Dplugin_class_name=MyJob                        \
 -Dplugin_name="My Job"                           \
 -Dplugin_category=General                        \
 -Dplugin_description="This is what my job does."
 
$ cd my-job-plugin

$ mvn clean install
```
_Be sure to update the artifactId, version, and package arguments to reflect your particular project._

This will generate the following project structure:
```
my-job-plugin/
├── README.md
├── pom.xml
├── .gitignore
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── my
    │   │           └── company
    │   │               ├── Messages.java
    │   │               ├── MyJob.java
    │   │               ├── MyJobEntry.java
    │   │               ├── MyJobEntryAnalyzer.java
    │   │               └── MyJobEntryDialog.java
    │   └── resources
    │       ├── MyJob.svg
    │       ├── OSGI-INF
    │       │   └── blueprint
    │       │       └── blueprint.xml
    │       ├── com
    │       │   └── my
    │       │       └── company
    │       │           └── messages
    │       │               └── messages_en_US.properties
    │       └── my-job-plugin-feature.xml
    └── test
        └── java
            └── com
                └── my
                    └── company
```
To deploy copy the generated "my-job-plugin-1.0-SNAPSHOT.kar" into "data-integration\system\karaf\deploy"