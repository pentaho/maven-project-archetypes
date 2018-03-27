## Pentaho CE Bundle Archetype

A project archetype for creating the structure of a new osgi bundle project within the Pentaho ecosystem.

The pentaho-ce-bundle-archetype can be used as a basis for any project (or even submodule) that produces a community edition bundle. It will create the basic folder structure, provide you with a template README.md and a common .gitignore file.

### Example Usage
```
$ mvn archetype:generate                               \
     -DarchetypeGroupId=org.pentaho                    \
     -DarchetypeArtifactId=pentaho-ce-bundle-archetype \
     -DarchetypeVersion=8.1.0.0-SNAPSHOT                \
     -DgroupId=org.pentaho                             \
     -DartifactId=my-pentaho-project                   \
     -Dversion=1.0-SNAPSHOT                          \
     -Dpackage=org.pentaho.my.project
     
$ cd my-pentaho-project

$ mvn clean install
```
_Be sure to update the artifactId, version, and package arguments to reflect your particular project._

This will generate the following project structure:
```
my-pentaho-project/
├── README.md
├── pom.xml
└── src
    ├── it
    │   ├── java
    │   └── resources
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── pentaho
    │   │           └── my
    │   │               └── project
    │   │                   ├── MyService.java
    │   │                   └── MyServiceImpl.java
    │   └── resources
    │       └── OSGI-INF
    │           └── blueprint
    │               └── blueprint.xml
    └── test
        ├── java
        │   └── org
        │       └── pentaho
        │           └── my
        │               └── project
        │                   └── MyServiceImplTest.java
        └── resources

```
