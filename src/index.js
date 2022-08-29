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
const mysql = require('mysql');

//TODO replace these with your MySQL setup
const dbCon = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "osla"
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
    res.sendFile(__dirname + '/client/index.html');
});

//Kanban page - kanban.html
app.get('/kanban',function(req, res) {
    res.sendFile(__dirname + '/client/kanban.html');
});

//Habits page - habits.html
app.get('/habits',function(req, res) {
    res.sendFile(__dirname + '/client/habits.html');
});

//Health page - health.html
app.get('/health',function(req, res) {
    res.sendFile(__dirname + '/client/health.html');
});

//Journal page - journal.html
app.get('/journal',function(req, res) {
    res.sendFile(__dirname + '/client/journal.html');
});

//Relationships Page - relationships.html
app.get('/relationships',function(req, res) {
    res.sendFile(__dirname + '/client/relationships.html');
});

//Any path will lead to openlife.html
app.get('*', function(req, res) {
    res.sendFile(__dirname + '/client/index.html');
});