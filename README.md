# Traverse JSON using JSONPath queries.

## Examples

```bash
$ curl  "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json" | ./jsonwalk  '$.pokemon[?( @.name == "Pikachu" )]'
[{"id":25,"num":"025","name":"Pikachu","img":"http:\/\/www.serebii.net\/pokemongo\/pokemon\/025.png","type":["Electric"],"height":"0.41 m","weight":"6.0 kg","candy":"Pikachu Candy","candy_count":50,"egg":"2 km","spawn_chance":0.21,"avg_spawns":21,"spawn_time":"04:00","multipliers":[2.34],"weaknesses":["Ground"],"next_evolution":[{"num":"026","name":"Raichu"}]}]

# Get list off all Pikachu's move from internet and sort alphabetically
$ curl "https://pokeapi.co/api/v2/pokemon/25/" 2>/dev/null | ./jsonwalk '$..move.name' -o plain | sort
agility
attract
bide
body-slam
# Get list of books from json stored locally
$ cat ../src/test/resources/sample.json | ./jsonwalk "$..book"
[[{"category":"reference","author":"Nigel Rees","title":"Sayings of the Century","price":8.95},{"category":"fiction","author":"Evelyn Waugh","title":"Sword of Honour","price":12.99},{"category":"fiction","author":"Herman Melville","title":"Moby Dick","isbn":"0-553-21311-3","price":8.99},{"category":"fiction","author":"J. R. R. Tolkien","title":"The Lord of the Rings","isbn":"0-395-19395-8","price":22.99}]]
```


# Development

To build the native image install graalvm and ensure its configured properly for your project toolchain.

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
