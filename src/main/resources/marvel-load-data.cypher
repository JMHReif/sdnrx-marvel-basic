//Marvel API: https://developer.marvel.com/

//Queries:
//1) Create indexes for improving performance (also adds constraints for unique properties).
:params "marvel_public": "<your public API key here>", "marvel_private": "<your private API key here>";

CREATE CONSTRAINT ON (char:Character) ASSERT char.id IS UNIQUE;
CREATE CONSTRAINT ON (issue:ComicIssue) ASSERT issue.id IS UNIQUE;

CREATE INDEX ON :Character(name);
CREATE INDEX ON :Character(resourceURI);
CREATE INDEX ON :ComicIssue(resourceURI);

//2) Load chunks of Characters by name starting with each letter of the alphabet.
WITH apoc.date.format(timestamp(), "ms", 'yyyyMMddHHmmss') AS ts
WITH "&ts=" + ts + "&apikey=" + $marvel_public + "&hash=" + apoc.util.md5([ts,$marvel_private,$marvel_public]) as suffix
CALL apoc.periodic.iterate('UNWIND split("ABCDEFGHIJKLMNOPQRSTUVWXYZ","") AS letter RETURN letter',
'CALL apoc.load.json("http://gateway.marvel.com/v1/public/characters?nameStartsWith="+letter+"&orderBy=name&limit=100"+$suffix) YIELD value
UNWIND value.data.results as results
WITH results, results.comics.available AS comics
WHERE comics > 0
MERGE (char:Character {id: results.id})
  ON CREATE SET char.name = results.name, char.description = results.description, char.thumbnail = results.thumbnail.path+"."+results.thumbnail.extension,
      char.resourceURI = results.resourceURI
',{batchSize: 1, iterateList:false, params:{suffix:suffix}})
YIELD batches, total, timeTaken, committedOperations, failedOperations, failedBatches , retries, errorMessages , batch , operations, wasTerminated
RETURN batches, total, timeTaken, committedOperations, failedOperations, failedBatches , retries, errorMessages , batch , operations, wasTerminated;


//3) For all of the Characters we just loaded, load all of the related ComicIssue objects. I am just populating basic info on each of the nodes here.
WITH apoc.date.format(timestamp(), "ms", 'yyyyMMddHHmmss') AS ts
WITH "&ts=" + ts + "&apikey=" + $marvel_public + "&hash=" + apoc.util.md5([ts,$marvel_private,$marvel_public]) as suffix
CALL apoc.periodic.iterate('MATCH (c:Character) WHERE c.resourceURI IS NOT NULL AND NOT exists((c)<-[:INCLUDES]-()) RETURN c LIMIT 100',
'CALL apoc.util.sleep(2000)
CALL apoc.load.json(c.resourceURI+"/comics?format=comic&formatType=comic&limit=100"+$suffix)
YIELD value WITH c, value.data.results as results WHERE results IS NOT NULL
UNWIND results as result MERGE (comic:ComicIssue {id: result.id})
  ON CREATE SET comic.name = result.title, comic.issueNumber = result.issueNumber, comic.pageCount = result.pageCount, comic.resourceURI = result.resourceURI, comic.thumbnail = result.thumbnail.path+"."+result.thumbnail.extension
WITH c, comic, result
MERGE (comic)-[r:INCLUDES]->(c)',
{batchSize: 20, iterateList:false, retries:2, params:{suffix:suffix}})
YIELD batches, total, timeTaken, committedOperations, failedOperations, failedBatches , retries, errorMessages , batch , operations, wasTerminated
RETURN batches, total, timeTaken, committedOperations, failedOperations, failedBatches , retries, errorMessages , batch , operations, wasTerminated;