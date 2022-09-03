/**
 * @author Anthony Narlock
 * @github narlock
 * 
 * This is the setup file for OSLA.
 * 
 * Before starting, you will need to create
 * a MySQL database. You can do this by opening
 * your MySQL and writing the following query:
 * 
 * CREATE DATABASE osla;
 * 
 * and executing.
 * 
 * You will also need to have Node.js
 * installed. This is what we will use
 * to run the local web server and
 * run the setup file.
 * 
 * Next, you will need to install the mysql
 * dependency to run this JavaScript file.
 * Type the following command in terminal:
 * 
 * npm install mysql
 * 
 * Now that we have the mysql dependency
 * installed, make sure your MySQL server
 * is running. You can now run the setup:
 * 
 * node setup.js
 * 
 * After this is completed, open src/index.js
 * and update the dbCon object to match
 * the one in this file. You will then
 * need to install the dependencies:
 * 
 * npm install mysql
 * npm install express
 */
console.log("[OSLA/Setup] Beginning OSLA Setup...");

const mysql = require('mysql');

//TODO replace these with your MySQL setup
const dbCon = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "osla"
});

console.log("[OSLA/Setup] Attempting MySQL Database connection...");
dbCon.connect(function (err) {
    if(err) { 
        console.log("[OSLA/Setup] Connection to database FAILURE");
        throw err; 
    }
    console.log("[OSLA/Setup] Connection to database SUCCESS");

    console.log("[OSLA/Setup] Creating Kanban MySQL Table...");
    sql = `
        CREATE TABLE kanban (
            story_id                INT NOT NULL AUTO_INCREMENT,
            story_status            VARCHAR(20),
            story_title             VARCHAR(100),
            story_priority          INT UNSIGNED,
            story_points            INT UNSIGNED,
            story_desc              LONGTEXT,
            story_creation_date     DATE NOT NULL,
            story_due_date          DATE,
            PRIMARY KEY (story_id)
        )
    `;
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/Setup] Creation of Kanban MySQL Table FAILURE");
            throw err;
        }
        console.log("[OSLA/Setup] Creation of Kanban MySQL Table SUCCESS");
    });

    console.log("[OSLA/Setup] Creating Habits MySQL Table...");
    sql = `
        CREATE TABLE habits (
            habit_id                INT NOT NULL AUTO_INCREMENT,
            habit_title             VARCHAR(100),
            habit_streak            INT UNSIGNED,
            habit_occurrence        VARCHAR(20),
            habit_status            INT UNSIGNED,
            habit_date              DATE,
            PRIMARY KEY (habit_id)
        )
    `;
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/Setup] Creation of Habits MySQL Table FAILURE");
            throw err;
        }
        console.log("[OSLA/Setup] Creation of Habits MySQL Table SUCCESS");
    });

    console.log("[OSLA/Setup] Creating Health Entry MySQL Table...");
    sql = `
        CREATE TABLE hentry (
            entry_type              VARCHAR(20),
            entry_date              DATE NOT NULL,
            entry_units_completed   INT UNSIGNED
        )
    `;
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/Setup] Creation of Health Entry MySQL Table FAILURE");
            throw err;
        }
        console.log("[OSLA/Setup] Creation of Health Entry MySQL Table SUCCESS");
    });

    console.log("[OSLA/Setup] Creating Health Goals MySQL Table...");
    sql = `
        CREATE TABLE hgoals (
            goal_title              VARCHAR(20),
            goal_type               VARCHAR(20),
            goal_units_to_complete  INT UNSIGNED,
            goal_units              VARCHAR(20)
        )
    `;
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/Setup] Creation of Health Goals MySQL Table FAILURE");
            throw err;
        }
        console.log("[OSLA/Setup] Creation of Health Goals MySQL Table SUCCESS");
    });

    console.log("[OSLA/Setup] Creating Journal Entry MySQL Table...");
    sql = `
        CREATE TABLE journal (
            entry_date              DATE NOT NULL,
            entry_how_was_day       INT UNSIGNED,
            entry_events            LONGTEXT,
            entry_stresses          LONGTEXT,
            entry_gratefulness      LONGTEXT,
            entry_goals             LONGTEXT,
            PRIMARY KEY (entry_date)
        )
    `;
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/Setup] Creation of Journal Entry MySQL Table FAILURE");
            throw err;
        }
        console.log("[OSLA/Setup] Creation of Journal Entry MySQL Table SUCCESS");
    });

    console.log("[OSLA/Setup] Creating Relationships MySQL Table...");
    sql = `
        CREATE TABLE relationships (
            contact_id              INT NOT NULL AUTO_INCREMENT,
            contact_name            VARCHAR(50),
            contact_birthday        DATE,
            contact_phone           VARCHAR(50),
            contact_email           VARCHAR(100),
            contact_fb              VARCHAR(50),
            contact_whatsapp        VARCHAR(50),
            contact_discord         VARCHAR(50),
            contact_note            MEDIUMTEXT,
            PRIMARY KEY(contact_id)
        )
    `;
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/Setup] Creation of Relationships MySQL Table FAILURE");
            throw err;
        }
        console.log("[OSLA/Setup] Creation of Relationships MySQL Table SUCCESS");
    });

    console.log("[OSLA/Setup] Closing database connection...");
    dbCon.end();

    console.log("[OSLA/Setup] OSLA Setup has completed.");
})