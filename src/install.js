/**
 * @author narlock
 * Create the file osladb.db if install does not work
 * Connecting to OSLA database
 */
 const sqlite3 = require('sqlite3').verbose();

 const db = new sqlite3.Database("./osladb.db", sqlite3.OPEN_READWRITE, (err) => {
     if(err) return console.log("[OSLA] Database connection failure: " + err.message);
     console.log("[OSLA] Database connection success");
 });

//Creates contacts table
db.run('CREATE TABLE contacts(first_name, last_name, phone_number, email, address, birthday, note)');

//Create kanban data table
db.run('CREATE TABLE kanban(column_id, item_id, item_content)');

//Create Daily journal table
//TODO

//Create days since table
db.run('CREATE TABLE daystracker(id, title, start_date)');

//Create habit tracking table
//TODO