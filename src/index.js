/**
 * @author Anthony Narlock
 * index.js
 * 
 * The local webserver
 */

const express = require('express');           //for server
var bodyparser = require('body-parser');      //helps in extracting body
const url = require('url');                   //for splitting web addrs
const querystring = require('querystring');

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
    kanbanTitle = addEscapeCharacters(req.body.title);
    kanbanDesc = addEscapeCharacters(req.body.desc);

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
            \"${kanbanTitle}\",
            ${storyPriority},
            ${req.body.points},
            \"${kanbanDesc}\",
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
    kanbanTitle = addEscapeCharacters(req.body.title);
    kanbanDesc = addEscapeCharacters(req.body.desc);
    const sql = `
        UPDATE  kanban
        SET
                story_status=\"${req.body.status}\",
                story_title=\"${kanbanTitle}\",
                story_priority=${storyPriority},
                story_points=${req.body.points},
                story_desc=\"${kanbanDesc}\"
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

/**
 * GET /deleteDone
 * @brief deletes all completed stories
 */
app.get('/deleteDone', function(req, res) {
    const sql = `
        DELETE FROM kanban WHERE story_status=\"done\"
    `;
    dbCon.query(sql, function(err, stories) {
        if(err) {
            console.log("[OSLA/SERVER] deleteDone FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] deleteDone SUCCESS");
        res.send(stories);
    })
});

/* =========== HABITS RELATED METHODS =========== */

/**
 * GET /dbHabits
 * @brief Gets all haibts from database
 * @purpose Used to fill the kanban table
 */
app.get('/dbHabits', function(req, res) {
    const sql = 'SELECT * FROM habits';
    dbCon.query(sql, function(err, habits) {
        if(err) {
            console.log("[OSLA/SERVER] GET dbHabits FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET dbHabits SUCCESS");
        res.send(habits);
    });
});

/**
 * GET /dailyDbHabits
 * @brief Gets the backlog only kanban stories from database
 * @purpose to fill the backlog portion of kanban table
 */
 app.get('/dailyDbHabits', function(req, res) {
    var days = ['u', 'm', 't', 'w', 'h', 'f', 's'];
    var d = new Date();
    var dayName = days[d.getDay()]; 

    const sql = `SELECT * FROM habits WHERE habit_occurrence LIKE \"%${dayName}%\"`;
    dbCon.query(sql, function(err, habits) {
        if(err) {
            console.log("[OSLA/SERVER] GET dailyDbHabits FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET dailyDbHabits SUCCESS");
        res.send(habits);
    });
});

/**
 * /habitById
 * @brief Gets a habit by id
 */
app.post('/habitById', function(req, res) {
    const id = req.body.id;
    const sql = 'SELECT * FROM habits WHERE habit_id=\"' + id + '\";';
    dbCon.query(sql, function(err, story) {
        if(err) {
            console.log("[OSLA/SERVER] GET habitById FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] GET habitById SUCCESS");
        res.send(story[0]);
    });
});

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

    habitTitle = addEscapeCharacters(req.body.title);

    //Gets new date
    var date_format = new Date();
    var current_date = date_format.getFullYear() + "-" + date_format.getMonth() + "-" + date_format.getDate(); //yyyy-mm-dd
    console.log("Date being added...");
    console.log(current_date);

    const sql = `
        INSERT INTO habits (
            habit_title,
            habit_streak,
            habit_occurrence,
            habit_status,
            habit_date
        )
        VALUES (
            \"${habitTitle}\",
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

/**
 * POST /updateHabit
 * @brief Update habit to database
 */
 app.post('/updateHabit', function(req, res) {
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

    habitTitle = addEscapeCharacters(req.body.title);

    const sql = `
            UPDATE  habits
            SET
                    habit_title=\"${habitTitle}\",
                    habit_streak=${req.body.streak},
                    habit_occurrence=\"${occurrence}\",
                    habit_status=${req.body.status}
            WHERE   habit_id=${req.body.id}
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] updateHabit FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] updateHabit SUCCESS");
        res.redirect(302, '/allHabits');
    });
});

/**
 * POST checkHabit
 * @brief This will update the status of
 * a habit to complete
 */
app.post('/checkHabit', function(req, res) {
    const sql = `
        UPDATE habits
        SET    habit_status=1
        WHERE  habit_id=${req.body.id}
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] checkHabit FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] checkHabit SUCCESS");
        res.send(req.body.id);
    });
});

/**
 * GET checkDateForHabits
 * @brief will check date for each habit, if it is a
 * new day from previous day, the status will be set to incomplete
 */
app.get('/checkDateForHabits', function(req, res) {
    var days = ['u', 'm', 't', 'w', 'h', 'f', 's'];
    var d = new Date();
    var dayName = days[d.getDay()]; 

    //Gets new date
    var date = new Date();

    var current_date_formatted = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate(); //yyyy-mm-dd
    current_date = date.getFullYear() + '-'
                + ('0' + (date.getMonth())).slice(-2) + '-'
                 + ('0' + date.getDate()).slice(-2);

    //var current_date = date_format.getFullYear() + "-" + date_format.getMonth() + "-" + date_format.getDate(); //yyyy-mm-dd
    

    selectSql = `SELECT * FROM habits WHERE habit_occurrence LIKE \"%${dayName}%\"`;
    dbCon.query(selectSql, function(err, habits) {
        if(err) {
            console.log("[OSLA/SERVER] checkDateForHabits:SelectAllHabits FAILURE");
            throw err;
        }
        //console.log(habits);
        habits.forEach(function(habit) {
            habitDate = new Date(habit.habit_date);
            habitDateString = habitDate.getFullYear() + '-'
                 + ('0' + (habitDate.getMonth()+1)).slice(-2) + '-'
                 + ('0' + habitDate.getDate()).slice(-2);
            //console.log(habitDateString);
            //console.log(current_date);
            //console.log(habitDateString != current_date);
            if(habitDateString != current_date) {
                //Uncheck the habit
                sql = `
                    UPDATE habits
                    SET    habit_status=0,
                           habit_date=\"${current_date_formatted}\"
                    WHERE  habit_id=${habit.habit_id}
                `
                dbCon.query(sql, function(err, result) {
                    if(err) {
                        console.log(`[OSLA/SERVER] checkDateForHabits:UpdateStatus:id=${habit.habit_id} FAILURE`);
                        throw err;
                    }
                });
            } 
        });
        console.log(`[OSLA/SERVER] checkDateForHabits SUCCESS`);
        res.send(habits);
    });
});

/**
 * POST updateHabitStreaks
 * @brief Will update the streaks of all habits
 * Will remove the streak if occurrence criteria is not satisfied
 * Will increment streak if occurrence criteria is satisfied
 */
app.get('/updateHabitStreaks', function(req, res) {
    //Compare today's date with the habit date
    
    //If the habit date is the SAME as the current date, don't do anything to habit streak
    //If the habit date is more than one day behind the current date, set habit streak to zero
    currentDate = new Date();
    currentDateFormatted = currentDate.getFullYear() + "-" + currentDate.getMonth() + "-" + currentDate.getDate(); //yyyy-mm-dd
    currentDateCompare = new Date(currentDateFormatted);

    var days = ['u', 'm', 't', 'w', 'h', 'f', 's'];
    var d = new Date();
    var dayName = days[d.getDay()]; 

    selectSql = `SELECT * FROM habits WHERE habit_occurrence LIKE \"%${dayName}%\"`;
    dbCon.query(selectSql, function(err, habits) {
        if(err) {
            console.log("[OSLA/SERVER] updateHabitStreaks:SelectHabits FAILURE");
            throw err;
        }
        habits.forEach(function(habit) {
            habitDate = new Date(habit.habit_date);
            habitDateFormatted = habitDate.getFullYear() + "-" + (habitDate.getMonth()+1) + "-" + habitDate.getDate(); //yyyy-mm-dd
            habitDateCompare = new Date(habitDateFormatted);

            diffTime = Math.abs(Date.parse(habitDateCompare) - Date.parse(currentDateCompare));
            diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24)); 
            console.log(diffDays);
            
            //If the habit date is exactly one day behind the current date AND status is complete, increment habit streak
            //TODO Add logic so that streaks are kept depending on
            //Occurrence and not dependent on everyday... This will work only for everyday
            //Need logic perhaps in a table to check if thurs, check that diffDays is 7 or something...

            everydayHabit = 'mtwhfsu'; //Difference is 1 for increment Streak
            weeklyHabit = ['m', 't', 'w', 'h', 'f', 's', 'u']; //Difference is 7 for increment Streak
            
            if(diffDays == 1 && habit.habit_status == 1 && everydayHabit == habit.habit_occurrence) {
                updateSql = `
                    UPDATE  habits
                    SET     habit_streak=${(habit.habit_streak + 1)}
                    WHERE   habit_id=${habit.habit_id}
                `
                dbCon.query(updateSql, function(err, result) {
                    if(err) { throw err; }
                });
            } else if(diffDays == 7 && habit.habit_status == 1 && weeklyHabit.includes(habit.habit_occurrence)) {
                console.log("here");
                updateSql = `
                    UPDATE  habits
                    SET     habit_streak=${(habit.habit_streak + 1)}
                    WHERE   habit_id=${habit.habit_id}
                `
                dbCon.query(updateSql, function(err, result) {
                    if(err) { throw err; }
                });
            } else if(diffDays == 0) {
                //Do Nothing! Same Day.
            } else {
                updateSql = `
                    UPDATE  habits
                    SET     habit_streak=0
                    WHERE   habit_id=${habit.habit_id}
                `
                dbCon.query(updateSql, function(err, result) {
                    if(err) { throw err; }
                });
            }
        });
        console.log(`[OSLA/SERVER] updateHabitStreaks SUCCESS`);
        res.send(habits);
    });
});

/**
 * POST /deleteHabit
 * @brief Deletes habit
 */
 app.post('/deleteHabit', function(req, res) {
    const sql = `
        DELETE FROM habits WHERE habit_id=${req.body.id}
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.log("[OSLA/SERVER] deleteHabit FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] deleteHabit SUCCESS");
        res.send(req.body.id);
    });
});

/* =========== JOURNAL RELATED METHODS =========== */

app.post('/getJournalEntryByDate', function(req, res) {

    //write sql to get the date
    sql = `
        SELECT * FROM journal WHERE entry_date=\"${req.body.date}\"
    `
    dbCon.query(sql, function(err, entries) {
        if(err) {
            console.error("[OSLA/SERVER] getJournalEntryByDate FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] getJorunalEntryByDate SUCCESS");
        res.send(entries[0]);
    });
});

app.post('/updateJournalEntry', function(req, res) {
    
    sql = `
    SELECT * FROM journal WHERE entry_date=\"${req.body.date}\"
    `
    dbCon.query(sql, function(err, entries) {
        if(err) {
            console.error("[OSLA/SERVER] updateJournalEntry:searchEntry FAILURE");
            throw err;
        }
        if(entries[0] == undefined) {
            //check if journal entry is new, if it is, use insert
            insertSql = `
                INSERT INTO journal
                SET     entry_date=\"${req.body.date}\",
                        entry_how_was_day=\"${req.body.howWasDay}\",
                        entry_events=\"${addEscapeCharacters(req.body.eventsOfDay)}\",
                        entry_stresses=\"${addEscapeCharacters(req.body.stresses)}\",
                        entry_gratefulness=\"${addEscapeCharacters(req.body.gratefulness)}\",
                        entry_goals=\"${addEscapeCharacters(req.body.goals)}\"
            `
            dbCon.query(insertSql, function(err, result) {
                if(err) {
                    console.error("[OSLA/SERVER] updateJournalEntry:insert FAILURE");
                    throw err;
                }
                console.log("[OSLA/SERVER] updateJournalEntry SUCCESS");
                res.redirect(302, '/journal');
            });
        } else {
            //if not, update journal entry
            updateSql = `
                UPDATE journal
                SET entry_how_was_day=\"${req.body.howWasDay}\",
                    entry_events=\"${addEscapeCharacters(req.body.eventsOfDay)}\",
                    entry_stresses=\"${addEscapeCharacters(req.body.stresses)}\",
                    entry_gratefulness=\"${addEscapeCharacters(req.body.gratefulness)}\",
                    entry_goals=\"${addEscapeCharacters(req.body.goals)}\"
                WHERE entry_date=\"${req.body.date}\"
            `
            dbCon.query(updateSql, function(err, result) {
                if(err) {
                    console.error("[OSLA/SERVER] updateJournalEntry:update FAILURE");
                    throw err;
                }
                console.log("[OSLA/SERVER] updateJournalEntry SUCCESS");
                res.redirect(302, '/journal');
            });
        }
    });
});

/* =========== HEALTH RELATED METHODS =========== */

/* =========== RELATIONSHIP RELATED METHODS =========== */

app.get('/getAllContacts', function(req, res) {
    sql = `SELECT * FROM relationships`;
    dbCon.query(sql, function(err, contacts) {
        if(err) {
            console.error("[OSLA/SERVER] getAllContacts FAILURE");
            throw err;
        }
        res.send(contacts);
    });
});

app.get('/getContactsUpcomingBirthday', function(req, res) {
    sql = `SELECT * FROM relationships`;
    dbCon.query(sql, function(err, contacts) {
        if(err) {
            console.error("[OSLA/SERVER] getAllContacts FAILURE");
            throw err;
        }
        //Now contacts is an object with all of the users
        //create new array, iterate through each contact,
        //add contacts to the list that have upcoming birthday
        //(within 30 days of current day)
        contactsWithUpcomingBirthday = [];

        futureDate = new Date();
        futureDate.setDate(futureDate.getDate() + 30);
        futureDateFormatted = futureDate.getFullYear() + "-" + (futureDate.getMonth()+1) + "-" + futureDate.getDate(); //yyyy-mm-dd
        futureDateCompare = new Date(futureDateFormatted);

        contacts.forEach(function(contact) {
            contactBirthday = new Date(contact.contact_birthday);
            contactBirthday.setFullYear(futureDate.getFullYear());
            contactBirthdayFormatted = contactBirthday.getFullYear() + "-" + (contactBirthday.getMonth()+1) + "-" + contactBirthday.getDate(); //yyyy-mm-dd
            contactBirthdayCompare = new Date(contactBirthdayFormatted);

            diffTime = (Date.parse(contactBirthdayCompare) - Date.parse(futureDateCompare));
            diffDays = (diffTime / (1000 * 60 * 60 * 24)); 
            console.log(diffDays);

            if(diffDays >= -30 && diffDays <= 0) {
                //Contact is in date range to be on upcoming birthdays
                contact['diffDays'] = Math.abs(diffDays);
                contactsWithUpcomingBirthday.push(contact);
            }
        });

        res.send(contactsWithUpcomingBirthday);
    });
});

app.post('/createUpdateRelationship', function(req, res) {
    //If an id exists, then we know we are updating
    if(req.body.id) {
        //Update
        sql = `
            UPDATE relationships
            SET contact_name=\"${addEscapeCharacters(req.body.name)}\",
                contact_birthday=\"${req.body.birthday}\",
                contact_phone=\"${req.body.phone}\",
                contact_email=\"${req.body.email}\",
                contact_fb=\"${req.body.messenger}\",
                contact_whatsapp=\"${req.body.whatsapp}\",
                contact_discord=\"${req.body.discord}\",
                contact_note=\"${addEscapeCharacters(req.body.note)}\"
            WHERE contact_id=${req.body.id}
        `;
        dbCon.query(sql, function(err, result) {
            if(err) {
                console.error("[OSLA/SERVER] createUpdateRelationship FAILURE");
                throw err;
            }
        });
    } else {
        //Creating
        selectSql = `
            SELECT * FROM relationships WHERE contact_name=\"${req.body.name}\"
        `
        sql = `
            INSERT INTO relationships
            SET contact_name=\"${addEscapeCharacters(req.body.name)}\",
                contact_birthday=\"${req.body.birthday}\",
                contact_phone=\"${req.body.phone}\",
                contact_email=\"${req.body.email}\",
                contact_fb=\"${req.body.messenger}\",
                contact_whatsapp=\"${req.body.whatsapp}\",
                contact_discord=\"${req.body.discord}\",
                contact_note=\"${addEscapeCharacters(req.body.note)}\"
        `;
        //Check if a user already exists with the username, if it does, then return errorMessage
        dbCon.query(selectSql, function(err, contact) {
            if(err) {
                console.error("[OSLA/SERVER] createUpdateRelationship FAILURE");
                throw err;
            }
            if(contact.length == 0) {
                console.log("here");
                dbCon.query(sql, function(err, result) {
                    if(err) {
                        console.error("[OSLA/SERVER] createUpdateRelationship FAILURE");
                        throw err;
                    }
                });
            } else {
                console.error("[OSLA/SERVER] Contact was not added since they already exist.");
            }
        });
    }
    console.error("[OSLA/SERVER] createUpdateRelationship SUCCESS");
    res.redirect(302, '/relationships');
});

app.post('/getContact', function(req, res) {
    //req.body.contactName is contact name
    sql = `
        SELECT * FROM relationships WHERE contact_name=\"${req.body.contactName}\"
    `
    dbCon.query(sql, function(err, contacts) {
        if(err) {
            console.error("[OSLA/SERVER] getContact FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] getContact SUCCESS");
        res.send(contacts[0]);
    });
});

app.post('/deleteContactById', function(req, res) {
    sql = `
        DELETE FROM relationships WHERE contact_id=${req.body.id}
    `
    dbCon.query(sql, function(err, result) {
        if(err) {
            console.error("[OSLA/SERVER] deleteContactById FAILURE");
            throw err;
        }
        console.log("[OSLA/SERVER] deleteContactById SUCCESS");
        res.send(result);
    });
});

/* =========== HTML PAGE RELATED METHODS =========== */

//Create Form for Relationship - createForm.html
app.get('/contactForm', function(req, res) {
    res.sendFile(__dirname + '/client/contactForm.html');
});

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

/* =========== SERVER-SIDE HELPER METHODS =========== */

/**
 * addEscapeCharacter
 * @param {} str 
 * @returns str with escape sequence characters
 */
function addEscapeCharacters( str ) {
    return (str + '').replace(/[\\"']/g, '\\$&').replace(/\u0000/g, '\\0');
}