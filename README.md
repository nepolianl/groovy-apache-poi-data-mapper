# A Groovy DSL for Data Loading

A Groovy DSL for loading data from scripts into CSV, Excel and custom Java Types which is particularly suited for generating static and dynamic data at runtime. The purpose of this project is to demonstrate how we can define groovy scripts as virtual data table and transform it into files such as CSV, Excel and Java classes. We can also extend the data transformation to integrate with third-part API, database or utility to auto generate or merge specific data at runtime as this implemented within Java classes.

**About Groovy**

Groovy is a language that has been built to be concise and expressive. It lends itself well to the creation of a Domain Specific Languages (DSL), a DSL allows for a rich vocabulary that can be shared with other developers. Gradle and Spock (Unit-Testing library) are examples of good Groovy projects.

**Project Inspiration:**

Pedal-Loader is a Groovy DSL for loading data that is particularly suited for database unit-testing. Pedal-loader has similar ideas to DBUnit but uses Groovy to provide test-data instead of XML. This allows for a lot more dynamic data to be produced at runtime.

This led to the idea of using Pedal-loader which treats Groovy scripts as imaginary database tables.

**Purpose:**

The purpose of this project to experiment to show how we can create imaginary database tables in Groovy library and generate runtime data into custom file or data types

# Groovy table example

This is a sample of a Groovy script which is creating three MyClass instances and populated with data. You can see how this looks quite similar to an SQL INSERT statement.

	table (MyClass, ['id', 'name', 'dob']) {
		data1 = row value1, value2, value3
		data2 = row value4, value5, value5
		data3 = row value6, value7, value8 
	}

The Java equivalent would be:

	new MyClass("value1", "value2", "value3");
	new MyClass("value4", "value5", "value6");
	new MyClass("value7", "value8", "value9");


The SQL equivalent would be:

	INSERT INTO MyClass(id, name, dob) VALUES ('value1', 'value2', 'value3'), ('value4', 'value5', 'value6'), ('value7', 'value8', 'value9');

Based on the above discussion, the Groovy scripts acts as imaginary database tables and we can build relationships between them.

Data Relationship and Reference table:

	The Groovy equivalent describes the data references
	
	data = load('address': 'test.address.data.groovy')
	
	def theAddress = data.address.address1
	def landmark = 'Opp. Central Mall'
	
	table (MyContact, ['id', 'name', 'dob', 'address']) {
		contact1 = row 1, 'Nepolian', '1-1-1', theAddress
	}

### To run and setup this project locally

	$ git clone <>
	$ mvn clean install
	$ java -jar target/groovy-apache-poi-data-mapper.jar
	
Access the server using http://localhost:9321/service	

### Import this project into Eclipse IDE

	1. Import into eclipse using existing maven project into workspace
	2. Right click -> Maven Install
	3. Start Test classes
