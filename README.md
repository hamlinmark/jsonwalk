# Traverse JSON using JSONPath queries.

## Examples

```bash
# Get list off all Pikachu's move from internet and sort alphabetically
curl "https://pokeapi.co/api/v2/pokemon/25/" 2>/dev/null | java -jar target/jsonwalk-1.0.0-SNAPSHOT.jar '$..move.name' | sort
# Get list of books from json stored locally
cat src/test/sample.json | java -jar target/jsonwalk-1.0.0-SNAPSHOT.jar   "$..book"
```


# Development

## Build and Execute

```bash
# Build
mvn clean package
# Get list off all Pikachu's move from internet and sort alphabetically
curl "https://pokeapi.co/api/v2/pokemon/25/" 2>/dev/null | java -jar target/jsonwalk-1.0.0-SNAPSHOT.jar '$..move.name' | sort
# Get list of books from json stored locally
cat src/test/sample.json | java -jar target/jsonwalk-1.0.0-SNAPSHOT.jar   "$..book"
```

## Libraries

[JsonPath](https://github.com/json-path/JsonPath)
[Picocli](https://picocli.info/#_compact_example)


[datasets](https://github.com/jdorfman/awesome-json-datasets)
