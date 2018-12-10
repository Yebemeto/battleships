To build the app please execute "mvn package" inside battleships project folder.
Then inside "target" folder the .war file will appear.
Deploy the .war file in a container of your choice. I have tested it on Wildfly.
The application REST interface is available on path : server_path:server_port/battleships-1/game
The details of application and its interface:

﻿This is a simple REST application. It allows players play simplified version of "Battleships" game:
-Game board size of 10x10 adressed A-J;1-10
-Multiple games can be played concurrently
-Every player has four ships, one of each kind: one decker, two decker, three decker, four decker
-Players do not place their ships manually, the placement is predefined as follows:

Player one					
One Decker A1
Two Decker D1,D2
Three Decker G1,G2,G3
Four Decker J1,J2,J3,J4

Player two
One Decker J10
Two Decker G9,G10
Three Decker D8,D9,D10
Four Decker A1,B1,C1,D1

Standard game scenario is as follows:
1.Player one makes a POST request to /game url. Application generates id for the game. It replies with Set-Auth-Token header containing the token this users has to use in consequent request. The body of the response contains an invitation url: /game/{id}/join to send to another player
2.Player two joins the game by sending POST request to the invitation url recived from player one. He recives current game state in response body and his token in Set-Auth-Token header.
3.After joining the game, player two takes the first turn and starts shooting.
4.Players shoot on their respective turns by invoking PUT /game/{id} with their Auth-Token in a header and { “position”: “A5” } body. Response is a JSON with info if any ship was hit and if it was sunken. In case of a hit the shooting player takes another turn.
5.Players can check current game state by invoking GET /game/{id} with their Auth-Token in a header.

API details:
-Creating game
○ Request: POST /game 
○ Response: { “invitationUrl”: “/game/{id}/join”} and Set-Auth-Token header with token.
-Joining game
○ Request: POST /game/{id|/join 
○ Response: starting game state, as in  GET /game/{id}, so { “gameStatus:” “YOUR_TURN”, “yourScore”: 0, “opponentScore: 0 } alongside Set-Auth-Token header with token.
-Shooting
○ Request: PUT /game/{id} with Auth-Token header, body: { “position”: “A5” } 
○ Response :  { “result”: “MISS” }, player has to remember where he has shot before. Another shot in the same position results in a loss of a turn. { “result”: “HIT”, “shipType”: “FOUR_DECKER”, “sunken”: false }, ship types: FOUR_DECKER, THREE_DECKER, TWO_DECKER, ONE_DECKER 
-Game state
○ Request: GET /game/{id} with Auth-Token header
○ Response: { “gameStatus:” “AWAITING_PLAYERS”, “yourScore”: 4, “opponentScore: 12 }, score is a number of hits. Available game states: AWAITING_PLAYERS, YOUR_TURN, WAITING_FOR_OPPONENT_MOVE, YOU_WON, YOU_LOST. 
