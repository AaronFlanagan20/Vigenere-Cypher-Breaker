# Vigenere Cypher Breaker
**By Aaron Flanagan**

This project is a design of the followng diagram.
![Image of project](https://raw.github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/master/res/dsAssignment2015.gif)

It consists of two projects:
* Vigenere Cypher Breaker - RMI Service
* Cracker - Tomcat Web Server

###*Vigenere Cypher Enumerator*

Vigenere Cypher Breaker is built using RMI and can be used as a stand-alone project.

[**VigenereBreaker**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/VigenereBreaker.java) is the remote interface and [**VigenreBreakerImpl**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/VigenereBreakerImpl.java) is the remote object. [**VigenreBreakerImpl**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/VigenereBreakerImpl.java) creates a [**KeyEnumerator**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/KeyEnumerator.java) which has two jobs:

1. Create a quadgram map
2. Generate the best key and decrypt with it

The result is then returned to the remote obejct and passed back to the client.

The [**Servant**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/Servant.java) class does a lookUp for the remote object and invokes it's decrypt method, it passes in the encrypted text you want decrypted and the maximum size of the key to generate to encrypt and decrypt with.

To run this project either download the zip from the github repository or clone it down from github. You can then open it in eclipse or on the command line/terminal. In the command line/terminal change into the src directory of the project folder and issue the command **java ie.gmit.sw.[**VigenereBreaker**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/VigenereBreaker.java)**, this will run the service provider and bind the remote object to the rmi registry. Finally open a second command prompt/terminal and run the [**Servant**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/Servant.java) by issuing the command java ie.gmit.sw.[**Servant**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/Servant.java)


###*Tomcat Web Server*

*This project will require Apache Tomcat downloaded and/or Eclipse IDE Enterprise Edition*

The Tomcat Web Server cannot be run on it's own because it is built to use the RMI-Service. There is two methods to running this project

**Method 1**:
To generate the tomcat project in eclipse you can do the following: in eclipse click File -> Import -> WAR and select the cracker.war provided with this project. You can then run the index.jsp file in the Web-Content folder of the project.

**Method 2**:
Drag the cracker.war file into the webapps folder in your Tomcat directory and start Tomcat, this will automatically generate the cracker project and all the classes. You can then go to https://localhost:8080/cracker

**Note. [**VigenreBreakerImpl**](https://github.com/AaronFlanagan20/Vigenere-Cypher-Breaker/blob/master/src/ie/gmit/sw/VigenereBreakerImpl.java) will have to be running before the Crack Cypher button is pressed for either method.**

