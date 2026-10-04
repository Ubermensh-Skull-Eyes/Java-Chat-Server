## Getting Started

Hi everyone this is my first java project. I was trying to create a two way pager like chat server where clients would connect to a server and share information over the server to everyone present there.

## Folder Structure
This is the basic structure of project.
<img width="450" height="500" alt="image" src="https://github.com/user-attachments/assets/78aa32ae-8738-4d17-a534-0f37a6356d78" />

`src` :- 'Contains code for the project.

`bin` :- 'Contains compiled bytecode.

## Technologies Used
- Java
- Java Sockets
- Java ServerSocket

## Installation

```bash
# Clone the repository
git clone https://github.com/Ubermensh-Skull-Eyes/Java-Chat-Server.git)
```

# Open cmd and compile both the java packages together using the following command
```bash
cd Server\src
javac Server\*.java Client\Client.java
```
# Run server first using
```bash
java -cp ../bin Server.ChatServer 5000 [-any port]
```
#Open another terminal and run the following command to open client
```bash
cd Server\bin
java -cp ../bin Client.Client localhost 5000
```
A GUI will be seen you can type anything and everyone connected to that port will see your message.

## DEMO and USAGE

Server side :-

<img width="1917" height="1001" alt="image" src="https://github.com/user-attachments/assets/e2cf4b14-4513-4be2-9c76-75c344564eb4" />

Client side :-

<img width="1920" height="1080" alt="Video Project 1 (1)" src="https://github.com/user-attachments/assets/b7acd31c-53c9-41ed-b687-236aa56a9565" />

## 🤝 Contributing
Contributions are welcome! Please open an issue or submit a pull request for any changes.
