# jsonSerializerFinal

Lightweight Java project that demonstrates custom JSON serialization and deserialization without external JSON libraries.

## Overview

The project provides a small reflection-based JSON service with two usage styles:

- object-level serialization and deserialization through `@SerializedBy`
- field-level serialization and deserialization through `@JsonValue`

The implementation scans configured source packages, discovers serializer and deserializer classes, and stores them for runtime use.

## Main Components

- `JsonService` defines the public API for converting objects and annotated fields to and from JSON
- `JsonServiceImpl` implements the runtime lookup and conversion logic
- `@SerializedBy` links a model class to its serializer and deserializer
- `@JsonValue` marks a field for the field-oriented JSON conversion flow
- `JsonStorage` loads serializer and deserializer implementations discovered in the configured scan directory
- `json.properties` contains the directory scanning configuration

## Example

The sample classes in `org.level.up.json.test` show how to:

- annotate a model class with `@SerializedBy`
- implement `JsonSerializer<T>` and `JsonDeserializer<T>`
- serialize a full object
- serialize and deserialize an annotated field

## Requirements

- Java 8 or newer
- Maven 3.9+

## Build

```bash
mvn clean test
```

The Maven build is configured with explicit plugin versions and UTF-8 encoding for reproducible builds.

## Run Demo

```bash
mvn clean package
java -jar target/newjsonup-1.0-SNAPSHOT.jar
```

## Configuration

The runtime scan directory is configured in `src/main/resources/json.properties`:

```properties
directory.scan=src\main\java\
```

The configuration loader reads this file from the application resources, so the project no longer depends on a machine-specific absolute path.

## Notes

- The repository currently has no automated tests.
- The implementation uses reflection and dynamic class loading, so it is best suited as a learning project or a lightweight internal utility.
