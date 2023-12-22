# Music Genre Catalog
## Overview
This project is a simple system that allows users to browse music genres and songs, as well as add new genres and associated songs to the catalog. It consists of a client-server architecture, where the client interacts with the server to perform various actions.

## Features
Search for Genres: Users can search for existing genres in the catalog and retrieve a list of songs under that genre.
Add New Genres: Users can add new genres along with three associated songs for each new entry.
Server and Client Communication: The system utilizes sockets for communication between the server and client.

## Usage
### Server
The SongListServer program serves as the server side of the system.
It listens for incoming connections from clients on port 4999.
Handles requests from clients to search for genres or add new genres/songs.
If an error occurs, it prints the error message and stack trace.

### Client
The User program is the client application that interacts with the server.

Users are presented with a menu to choose between searching for genres or adding new ones.

Searching for a genre displays available genres and retrieves songs for the chosen genre.

Adding a new genre prompts users to enter a new genre and associated songs.

## Output
![image](https://github.com/jesrene/Music-Genre-Catalogue/assets/86104103/19c00f2b-e759-418e-8925-733e9b4b408d)
![image](https://github.com/jesrene/Music-Genre-Catalogue/assets/86104103/66234010-3167-41ec-8dde-2ffcb977d869)
![image](https://github.com/jesrene/Music-Genre-Catalogue/assets/86104103/4d579fc5-d834-4160-b3d5-f1994ef0bb94)
![image](https://github.com/jesrene/Music-Genre-Catalogue/assets/86104103/85967603-6c3c-48e2-aaee-d62e1993eb69)



## How to Use
Start the Server: Run the SongListServer program.

Connect Clients: Run the User program to connect to the server.

Interact with the Menu: Choose options 1 or 2 to perform actions.

Follow Prompts: Follow the prompts to search for genres or add new ones.

Exit: To stop the system, enter 'N' when prompted to perform another action.
