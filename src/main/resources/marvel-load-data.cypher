//Marvel API: https://developer.marvel.com/

//Setup (whether loading JSON or CSV)
CREATE CONSTRAINT ON (char:Character) ASSERT char.id IS UNIQUE;
CREATE CONSTRAINT ON (issue:ComicIssue) ASSERT issue.id IS UNIQUE;

CREATE INDEX ON :Character(name);
CREATE INDEX ON :Character(resourceURI);
CREATE INDEX ON :ComicIssue(resourceURI);

//NOTE: In the below section, choose only 1 of the 2 offered load methods!

//1. CSV load queries:
LOAD CSV WITH HEADERS FROM "https://raw.githubusercontent.com/JMHReif/sdnrx-marvel-basic/master/src/main/resources/marvel-data-small.csv" as file
WITH file
  WHERE file.id IS NOT NULL
CALL apoc.merge.node([file._labels],{id: file._id},{marvelId: file.id, description: file.description, issueNumber: file.issueNumber, name: file.name, pageCount: file.pageCount, resourceURI: file.resourceURI, thumbnail: file.thumbnail})
YIELD node
RETURN count(node);

LOAD CSV WITH HEADERS FROM "https://raw.githubusercontent.com/JMHReif/sdnrx-marvel-basic/master/src/main/resources/marvel-data-small.csv" as file
WITH file
  WHERE file._type IS NOT NULL
MATCH (start)
  WHERE start.id = file._start
WITH file, start
MATCH (end)
  WHERE end.id = file._end
WITH file, start, end
CALL apoc.merge.relationship(start, file._type, {}, {}, end, {}) YIELD rel
RETURN count(rel);
//Results:
//Nodes: 4620
//Relationships: 4399

//2. JSON load queries:
WITH "https://raw.githubusercontent.com/JMHReif/sdnrx-marvel-basic/master/src/main/resources/marvel-data-small.json" as file
CALL apoc.load.json(file) YIELD value
WITH value
WHERE value.type = "node"
CALL apoc.merge.node(value.labels,{id: value.id},{marvelId: value.properties.id, description: value.properties.description, issueNumber: value.properties.issueNumber, name: value.properties.name, pageCount: value.properties.pageCount, resourceURI: value.properties.resourceURI, thumbnail: value.properties.thumbnail})
YIELD node
RETURN count(node);

WITH "https://raw.githubusercontent.com/JMHReif/sdnrx-marvel-basic/master/src/main/resources/marvel-data-small.json" as file
CALL apoc.load.json(file) YIELD value
WITH value
WHERE value.type = "relationship"
MATCH (start)
  WHERE start.id = value.start.id
WITH value, start
MATCH (end)
  WHERE end.id = value.end.id
WITH value, start, end
CALL apoc.merge.relationship(start, value.label, {}, {}, end, {}) YIELD rel
RETURN count(rel);
//Results:
//Nodes: 4620
//Relationships: 4399