/**
 * @author Anthony Narlock
 * index.js
 * 
 * The local webserver
 */

const express = require('express');           //for server
var mysql = require('mysql');                 //connecting to database
var bodyparser = require('body-parser');      //helps in extracting body
const url = require('url');                   //for splitting web addrs

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

//Home Page - welcome.html
app.get('/',function(req, res) {
    res.sendFile(__dirname + '/client/welcome.html');
}); 