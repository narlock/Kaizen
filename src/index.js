/**
 * @author Anthony Narlock
 * index.js
 * 
 * The local webserver
 */

const express = require('express');           //for server
var bodyparser = require('body-parser');      //helps in extracting body
const url = require('url');                   //for splitting web addrs

/**
 * Connecting to OSLA database
 */
const sqlite3 = require('sqlite3').verbose();

const db = new sqlite3.Database("./osladb.db", sqlite3.OPEN_READWRITE, (err) => {
    if(err) return console.log("[OSLA] Database connection failure: " + err.message);
    console.log("[OSLA] Database connection success");
});

/**
 * Server declarations & setup
 */
const port = 8080;                            //The port for server
const app = express();                        //creating express app
app.use(bodyparser());                        //apply bodyparser to all reqs

// middle ware to serve static files
app.use('/client', express.static(__dirname + '/client'));
app.use(express.static('client'));
app.use(express.static('js'));
app.use('/static', express.static('client'))

// server listens on port set to value above for incoming connections
app.listen(port, () => console.log('Listening on port', port));

//Home Page - openlife.html
app.get('/',function(req, res) {
    res.sendFile(__dirname + '/client/openlife.html');
});

//Any path will lead to openlife.html
app.get('*', function(req, res) {
    res.sendFile(__dirname + '/client/openlife.html');
});