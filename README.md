# A simple RDF4j http SPARQL endpoint based on

It depends on [FedX](https://rdf4j.org/documentation/programming/federation/) 
as well as the [spring-boot mini server component](https://github.com/eclipse/rdf4j/tree/main/spring-components/spring-boot-sparql-web).

# Running and building

This is a spring-boot application so all [goals](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#?) are provided.

e.g building a docker image
```
mvn spring-boot:build-image
```

or just running it locally
```
mvn spring-boot:run
```

# Using it

Any SPARQL query will do e.g. example 12 from [rhea](https://sparql.rhea-db.org/sparql) but without the service part

```
# Query 12
# Select all Rhea reactions used to annotate enzyme sequences in UniProtKB
# return the number of UniProtKB entries
# Federated query using a service to UniProt SPARQL endpoint
#
# This query corresponds to the Rhea web site query :
# https://www.rhea-db.org/rhea?query=uniprot:*
#
PREFIX up: <http://purl.uniprot.org/core/>
PREFIX rh: <http://rdf.rhea-db.org/>

SELECT (count(?uniprot) as ?uniprotCount)  ?rhea ?accession ?equation 
WHERE {
  ?uniprot up:annotation/up:catalyticActivity/up:catalyzedReaction ?rhea . 
  ?rhea rh:accession ?accession .
  ?rhea rh:equation ?equation .
} GROUP BY ?rhea ?accession ?equation 

```

```
port=8080
curl -v -H 'Accept:application/sparql-results+xml' "http://localhost:$port/sparql/?query=PREFIX+rh%3a+%3chttp%3a%2f%2frdf.rhea-db.org%2f%3e%0d%0aPREFIX+up%3a+%3chttp%3a%2f%2fpurl.uniprot.org%2fcore%2f%3e%0d%0a%0d%0aSELECT+(count(%3funiprot)+as+%3funiprotCount)++%3frhea+%3faccession+%3fequation+%0d%0aWHERE+%7b%0d%0a++%3funiprot+up%3aannotation%2fup%3acatalyticActivity%2fup%3acatalyzedReaction+%3frhea+.++++%0d%0a++%3frhea+rh%3aaccession+%3faccession+.%0d%0a++%3frhea+rh%3aequation+%3fequation+.%0d%0a%7d+GROUP+BY+%3frhea+%3faccession+%3fequation+%0d%0a"
```
