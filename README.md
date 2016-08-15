## SpringRESTPrototype02
A simple Spring REST project.

## Features
* Does not use Spring Boot
* Uses Spring Java Configuration (@Configuration)

## JSON or XML Format
Note that the following elements enable the "retrieval by id" to work in xml as well as json format:
* Including MediaType.APPLICATION_XML_VALUE (in addition to MediaType.APPLICATION_JSON_VALUE) to the getBook method.
* Having JAXB annotations (e.g., @XmlRootElement) in the domain class (Book).
This allows data to be pulled in json format (*.../SpringRESTPrototype02/book/3.json*) or xml format (*.../SpringRESTPrototype02/book/3.xml*)
