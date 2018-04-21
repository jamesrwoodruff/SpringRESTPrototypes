## SpringRESTPrototype02
A simple Spring REST project.

## Features
* Does not use Spring Boot
* Uses Spring Java Configuration (@Configuration)

## JSON or XML Format
Note that the following enables the "retrieval by id" to work in xml as well as json format:

* Including MediaType.APPLICATION_XML_VALUE (in addition to MediaType.APPLICATION_JSON_VALUE) to controller method getBook.
* Having JAXB annotations (e.g., @XmlRootElement) in the domain class (Book).

If these are in place, the data can be pulled in either json or xml format by appending the extension to the url (*.../SpringRESTPrototype02/book/3.json*) or xml format (*.../SpringRESTPrototype02/book/3.xml*).
