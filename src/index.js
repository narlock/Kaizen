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

/* =========== KANBAN RELATED METHODS =========== */

/**
 * GET /kanban
 * @brief Sends kanban landing page
 */
app.get('/kanban',function(req, res) {
    res.sendFile(__dirname + '/client/kanban.html');
});

/**
 * GET /kanbanStories
 * @brief Gets the kanban stories from database
 * @purpose Used to fill the kanban table
 */
app.get('/kanbanStories', function(req, res) {
    const sql = 'SELECT * FROM kanban';
    dbCon.query(sql, function(err, stories) {
        if(err) {
            console.log("[OSLA/SERVER] GET kanbanStories FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET kanbanStories SUCCESS");
        res.send(stories);
    });
});

/**
 * GET /backlogKanbanStories
 * @brief Gets the backlog only kanban stories from database
 * @purpose to fill the backlog portion of kanban table
 */
 app.get('/backlogKanbanStories', function(req, res) {
    const sql = 'SELECT * FROM kanban WHERE story_status=\"backlog\"';
    dbCon.query(sql, function(err, stories) {
        if(err) {
            console.log("[OSLA/SERVER] GET backlogKanbanStories FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET backlogKanbanStories SUCCESS");
        res.send(stories);
    });
});

/**
 * GET /todoKanbanStories
 * @brief Gets the backlog only kanban stories from database
 * @purpose to fill the backlog portion of kanban table
 */
 app.get('/todoKanbanStories', function(req, res) {
    const sql = 'SELECT * FROM kanban WHERE story_status=\"todo\"';
    dbCon.query(sql, function(err, stories) {
        if(err) {
            console.log("[OSLA/SERVER] GET todoKanbanStories FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET todoKanbanStories SUCCESS");
        res.send(stories);
    });
});

/**
 * GET /inprogKanbanStories
 * @brief Gets the backlog only kanban stories from database
 * @purpose to fill the backlog portion of kanban table
 */
 app.get('/inprogKanbanStories', function(req, res) {
    const sql = 'SELECT * FROM kanban WHERE story_status=\"inprog\"';
    dbCon.query(sql, function(err, stories) {
        if(err) {
            console.log("[OSLA/SERVER] GET inprogKanbanStories FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET inprogKanbanStories SUCCESS");
        res.send(stories);
    });
});

/**
 * GET /doneKanbanStories
 * @brief Gets the backlog only kanban stories from database
 * @purpose to fill the backlog portion of kanban table
 */
 app.get('/doneKanbanStories', function(req, res) {
    const sql = 'SELECT * FROM kanban WHERE story_status=\"done\"';
    dbCon.query(sql, function(err, stories) {
        if(err) {
            console.log("[OSLA/SERVER] GET doneKanbanStories FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET doneKanbanStories SUCCESS");
        res.send(stories);
    });
});

/**
 * POST /kanbanStory
 * @brief Gets a specific kanban story from database
 * @purpose Used to get a specific kanban story, perhaps after
 * updating the story, we can use this get to update it on page.
 * The use of this method will be in a post request made something
 * like the following with AJAX:
 * 
 * Note: This is a post request since using ajax to call
 * this method will result in an undefined request body
 * if using HTTP GET request...
 * 
 * @param req.body.id represents the id of the story
 * @return The story matching the provided id
 */
app.post('/kanbanStory', function(req, res) {
    console.log(req.body);
    const id = req.body.id;
    const sql = 'SELECT * FROM kanban WHERE story_id=\"' + id + '\";';
    console.log(sql);
    dbCon.query(sql, function(err, story) {
        if(err) {
            console.log("[OSLA/SERVER] GET kanbanStory FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET kanbanStory SUCCESS");
        res.send(story[0]);
    });
});

/**
 * POST /addKanbanStory
 * @brief Adds kanban story to database
 * @purpose Using a form, we can make the method of this form go
 * to this endpoint. The request body will be the details of the
 * form, which will be the story.
 * @param req.body the story to be added
 * @return redirects to kanban board
 */
app.post('/addKanbanStory', function(req, res) {
    console.log(req.body);
    var storyPriority = getStoryPriorityFromBody(req.body.priority);
    var date_format = new Date();
    var current_date = date_format.getFullYear() + "-" + date_format.getMonth() + "-" + date_format.getDate(); //yyyy-mm-dd
    const sql = `
        INSERT INTO kanban (
            story_status,
            story_title,
            story_priority,
            story_points,
            story_desc,
            story_creation_date
        )
        VALUES (
            \"${req.body.status}\",
            \"${req.body.title}\",
            ${storyPriority},
            ${req.body.points},
            \"${req.body.desc}\",
            \"${current_date}\"
        )
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] addKanbanStory FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] addKanbanStory SUCCESS");
        res.redirect(302, '/kanban');
    });
});

function getStoryPriorityFromBody(priorityString) {
    if(priorityString == "critical") { return 4; }
    else if(priorityString == "high") { return 3; }
    else if(priorityString == "medium") { return 2; }
    else if(priorityString == "low") { return 1; }
    else { return 2; }
}

/**
 * POST /updateKanbanStoryStatus
 * @brief Updates kanban story to database
 * @purpose Updating information of a story if story requirements
 * are modified.
 */
app.post('/updateKanbanStory', function(req, res) {
    const sql = `
        UPDATE  kanban
        SET     story_status=\"${req.body.newStatus}\"
        WHERE   story_id=${req.body.id}
    `;
    console.log(req.body);
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] updateKanbanStory FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] updateKanbanStory SUCCESS");
        res.send("success");
    });
});

/**
 * POST /updateKanbanStoryInfo
 * @brief Updates kanban story info to database
 * @purpose Updating information of a story if story requirements
 * are modified.
 */
 app.post('/updateKanbanStoryInfo', function(req, res) {
    var storyPriority = getStoryPriorityFromBody(req.body.priority);
    const sql = `
        UPDATE  kanban
        SET
                story_status=\"${req.body.status}\",
                story_title=\"${req.body.title}\",
                story_priority=${storyPriority},
                story_points=${req.body.points},
                story_desc=\"${req.body.desc}\"
        WHERE   story_id=${req.body.id}
    `;
    console.log(sql);
    console.log(req.body);
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] updateKanbanStory FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] updateKanbanStory SUCCESS");
        res.redirect(302, '/kanban');
    });
});

/**
 * POST /deleteKanbanStory
 * @brief Deletes kanban story from database
 * @purpose If a user wants to remove a story, or will be used if a 
 * story has been in 'done/completed' category for longer than
 * 3 days.
 */
app.post('/deleteKanbanStory', function(req, res) {
    const sql = `
        DELETE FROM kanban WHERE story_id=${req.body.id}
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] deleteKanbanStory FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] deleteKanbanStory SUCCESS");
        res.send(req.body.id);
    });
});

/* =========== HABITS RELATED METHODS =========== */

/**
 * POST /addHabit
 * @brief Adds habit to database
 * @purpose Using a form, we can make the method of this form go
 * to this endpoint. The request body will be the details of the
 * form, which will be the habit.
 * @param req.body the habit to be added
 * @return success will redirect to all habits
 */
 app.post('/addHabit', function(req, res) {
    console.log(req.body);
    
    //Gets the occurrence
    let occurrence = "";
    let bodyToString = JSON.stringify(req.body);
    if(bodyToString.includes("monday")) { occurrence = occurrence.concat("m"); }
    if(bodyToString.includes("tuesday")) { occurrence = occurrence.concat("t"); }
    if(bodyToString.includes("wednesday")) { occurrence = occurrence.concat("w"); }
    if(bodyToString.includes("thursday")) { occurrence = occurrence.concat("h"); }
    if(bodyToString.includes("friday")) { occurrence = occurrence.concat("f"); }
    if(bodyToString.includes("saturday")) { occurrence = occurrence.concat("s"); }
    if(bodyToString.includes("sunday")) { occurrence = occurrence.concat("u"); }
    if(occurrence == "") { occurrence = "mtwhfsu"; } //If none are selected, default to everyday

    //Gets new date
    var date_format = new Date();
    var current_date = date_format.getFullYear() + "-" + date_format.getMonth() + "-" + date_format.getDate(); //yyyy-mm-dd
    
    const sql = `
        INSERT INTO habits (
            habit_title,
            habit_streak,
            habit_occurrence,
            habit_status,
            habit_date
        )
        VALUES (
            \"${req.body.title}\",
            0,
            \"${occurrence}\",
            0,
            \"${current_date}\"
        )
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] addHabit FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] addHabit SUCCESS");
        res.redirect(302, '/allHabits');
    });
});

function getOccurrence(body) {
    
}

//All Habits page - habits.html
app.get('/allHabits',function(req, res) {
    res.sendFile(__dirname + '/client/allHabits.html');
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