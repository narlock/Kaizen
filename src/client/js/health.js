// Clock Logic
function showTime(){
    var date = new Date();
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    var session = "AM";
    
    if(h == 0){
        h = 12;
    }
    
    if(h > 12){
        h = h - 12;
        session = "PM";
    }
    
    h = (h < 10) ? "0" + h : h;
    m = (m < 10) ? "0" + m : m;
    s = (s < 10) ? "0" + s : s;
    
    var time = h + ":" + m + ":" + s + " " + session;
    document.getElementById("clock").innerText = time;
    document.getElementById("clock").textContent = time;
    
    setTimeout(showTime, 1000);
    
}

showTime();

let todaysDate = new Date();
let todaysDateFormatted = formatDate(todaysDate);

var waterGoal;
var calorieGoal;
var sleepGoal;
var todayWaterEntry;
var todayCalorieEntry;
var todaySleepEntry;

window.onload = function() {

    //Set today's date for h4
    document.getElementById('todayDate').textContent = todaysDateFormatted;

    //Get goals
    getWaterGoal();
    getCalorieGoal();
    getSleepGoal();

    //Get today's entry and fill in elements
    getTodayHealthEntryWater();
    getTodayHealthEntryCalorie();
    getTodayHealthEntrySleep();

    //Set the progress bars
    setWaterProgressBar();
    setCalorieProgressBar();
    setSleepProgressBar();
    
}

/* =========== SETUP FUNCTIONS =========== */

function getWaterGoal() {
    $.ajax({
        type: 'GET',
        url: 'getHealthWaterGoal',
        success: function(entry) {
            waterGoal = entry;

            //Set health attributes corresponding to waterGoal
            document.getElementById('goalWater').textContent = 
                (waterGoal.goal_units_to_complete + " " + waterGoal.goal_units);
            document.getElementById('goalUnitsWater').textContent = 
                (" " + waterGoal.goal_units);
        }
    });
}

function getCalorieGoal() {
    $.ajax({
        type: 'GET',
        url: 'getHealthCalorieGoal',
        success: function(entry) {
            calorieGoal = entry;

            //Set health attributes corresponding to calorieGoal
            document.getElementById('goalCalorie').textContent =
                (calorieGoal.goal_units_to_complete + " " + calorieGoal.goal_units);
            document.getElementById('goalUnitsCalorie').textContent = 
                (" " + calorieGoal.goal_units);
        }
    });
}

function getSleepGoal() {
    $.ajax({
        type: 'GET',
        url: 'getHealthSleepGoal',
        success: function(entry) {
            sleepGoal = entry;

            //Set health attributes corresponding to sleepGoal
            document.getElementById('goalSleep').textContent = 
                (sleepGoal.goal_units_to_complete + " " + sleepGoal.goal_units);
            document.getElementById('goalUnitsSleep').textContent =
                (" " + sleepGoal.goal_units);
        }
    });
}

function getTodayHealthEntryWater() {
    $.ajax({
        type: 'POST',
        url: 'getTodayHealthEntryWater',
        data: {
          'date': todaysDateFormatted
        },
        success: function(entry) {
            if(entry.length == 0) {
                console.log("Creating new Health Entry");
                //Create a water entry
                $.ajax({
                    type: 'POST',
                    url: 'createTodayHealthEntryWater',
                    data: {
                        'date': todaysDateFormatted
                    },
                    success: function(entry) {
                        //Upon creating, get the entry
                        $.ajax({
                            type: 'POST',
                            url: 'getTodayHealthEntryWater',
                            data: {
                                'date': todaysDateFormatted
                            },
                            success: function(entry) {
                                console.log("Got created health entry");
                                console.log(entry);
                                todayWaterEntry = entry;

                                //Set health attributes corresponding to waterEntry
                                document.getElementById('todayEntryWater').textContent =
                                todayWaterEntry.entry_units_completed;
                            }
                        });
                    }
                });
            } else {
                console.log("Loaded health entry");
                todayWaterEntry = entry;

                //Set health attributes corresponding to waterEntry
                document.getElementById('todayEntryWater').textContent =
                    todayWaterEntry.entry_units_completed;
            }
        }
    });
}

function getTodayHealthEntryCalorie() {
    $.ajax({
        type: 'POST',
        url: 'getTodayHealthEntryCalorie',
        data: {
          'date': todaysDateFormatted
        },
        success: function(entry) {
            if(entry.length == 0) {
                console.log("Creating new Calorie Entry");
                //Create calorie entry
                $.ajax({
                    type: 'POST',
                    url: 'createTodayHealthEntryCalorie',
                    data: {
                        'date': todaysDateFormatted
                    },
                    success: function(entry) {
                        //Upon creating, get the entry
                        $.ajax({
                            type: 'POST',
                            url: 'getTodayHealthEntryCalorie',
                            data: {
                                'date': todaysDateFormatted
                            },
                            success: function(entry) {
                                console.log("Got created calorie entry");
                                console.log(entry);
                                todayCalorieEntry = entry;

                                //Set health attributes corresponding to healthEntry
                                document.getElementById('todayEntryCalorie').textContent =
                                    todayCalorieEntry.entry_units_completed;
                            }
                        });
                    }
                });
            } else {
                todayCalorieEntry = entry;

                //Set health attributes corresponding to calorieEntry
                document.getElementById('todayEntryCalorie').textContent =
                    todayCalorieEntry.entry_units_completed;
            }
        }
    });
}

function getTodayHealthEntrySleep() {
    $.ajax({
        type: 'POST',
        url: 'getTodayHealthEntrySleep',
        data: {
          'date': todaysDateFormatted
        },
        success: function(entry) {
            if(entry.length == 0) {
                console.log("Creating new health entry");
                //Create a sleep entry
                $.ajax({
                    type: 'POST',
                    url: 'createTodayHealthEntrySleep',
                    data: {
                        'date': todaysDateFormatted
                    },
                    success: function(entry) {
                        //Upon creating, get the entry
                        $.ajax({
                            type: 'POST',
                            url: 'getTodayHealthEntrySleep',
                            data: {
                                'date': todaysDateFormatted
                            },
                            success: function(entry) {
                                console.log("Got created health entry");
                                todaySleepEntry = entry;

                                //Set health attributes correpsonding to sleepEntry
                                document.getElementById('todayEntrySleep').textContent =
                                    todaySleepEntry.entry_units_completed;
                            }
                        });
                    }
                });
            }
            todaySleepEntry = entry;

            //Set health attributes corresponding to sleepEntry
            document.getElementById('todayEntrySleep').textContent =
                todaySleepEntry.entry_units_completed;
        }
    });
}

/**
 * The initialization of the progress bars will require
 * calling the server to get the goal and the entry.
 * 
 * This is done to prevent potential race conditions when
 * setting a global entry and goal variable.
 */

function setWaterProgressBar() {
    /**
     * Will call getGoal, then will call getTodayEntry
     * Then will call Move function based off of
     * the current percentage completed as an integer.
     */

    $.ajax({
        type: 'GET',
        url: 'getHealthWaterGoal',
        success: function(localWaterGoal) {
            $.ajax({
                type: 'POST',
                url: 'getTodayHealthEntryWater',
                data: {
                  'date': todaysDateFormatted
                },
                success: function(localWaterEntry) {
                    progressNow = Math.floor((localWaterEntry.entry_units_completed / localWaterGoal.goal_units_to_complete) * 100);
                    moveWaterProgressBar(0, progressNow);
                    checkWaterCompletion(localWaterEntry, localWaterGoal);
                }
            });
        }
    });
}

function setCalorieProgressBar() {
    /**
     * Will call getGoal, then will call getTodayEntry
     * Then will call Move function based off of
     * the current percentage completed as an integer.
     */
     $.ajax({
        type: 'GET',
        url: 'getHealthCalorieGoal',
        success: function(localGoal) {
            $.ajax({
                type: 'POST',
                url: 'getTodayHealthEntryCalorie',
                data: {
                  'date': todaysDateFormatted
                },
                success: function(localEntry) {
                    progressNow = Math.floor((localEntry.entry_units_completed / localGoal.goal_units_to_complete) * 100);
                    moveCalorieProgressBar(0, progressNow);
                    checkCalorieCompletion(localEntry, localGoal);
                }
            });
        }
    });
}

function setSleepProgressBar() {
    /**
     * Will call getGoal, then will call getTodayEntry
     * Then will call Move function based off of
     * the current percentage completed as an integer.
     */
     $.ajax({
        type: 'GET',
        url: 'getHealthSleepGoal',
        success: function(localGoal) {
            $.ajax({
                type: 'POST',
                url: 'getTodayHealthEntrySleep',
                data: {
                  'date': todaysDateFormatted
                },
                success: function(localEntry) {
                    progressNow = Math.floor((localEntry.entry_units_completed / localGoal.goal_units_to_complete) * 100);
                    moveSleepProgressBar(0, progressNow);
                    checkSleepCompletion(localEntry, localGoal);
                }
            });
        }
    });
}

/* =========== ACTION FUNCTIONS =========== */

/**
 * changeWaterEntryNumber
 * @param {} event 
 * @brief Will change the "onclick" number of the
 * add button to the number that is in the text field.
 * 
 * It will remove the onclick event to an error message
 * if a value that is not an integer is passed into
 * the text field
 */
function changeWaterEntryNumber(event) {
    unitsToAdd = parseInt(event.target.value);

    if(Number.isInteger(unitsToAdd)) {
        document.getElementById('waterAddButton').setAttribute
            ('onclick', 'addToWaterEntry(' + unitsToAdd + ')');
    } else {
        alert("You have entered an invalid entry.");
        document.getElementById('waterEntryText').value = "";
    }
}

/**
 * addToWaterEntry
 * @param {} event 
 * @brief Add number of units to water entry.
 * This will also update the entry numbers
 * and move the water progress bar.
 */
function addToWaterEntry(unitsToAdd) {
    progressBefore = Math.floor((todayWaterEntry.entry_units_completed / waterGoal.goal_units_to_complete) * 100);
    console.log(progressBefore);

    todayWaterEntry.entry_units_completed += unitsToAdd;
    $.ajax({
        type: 'POST',
        url: 'updateTodayWaterEntry',
        data: {
          'entry': todayWaterEntry,
          'dateString': document.getElementById('todayDate').textContent
        },
        success: function(entry) {
            document.getElementById('todayEntryWater').textContent =
                todayWaterEntry.entry_units_completed;

            progressAfter = Math.floor((todayWaterEntry.entry_units_completed / waterGoal.goal_units_to_complete) * 100);
            console.log(progressAfter);
            moveWaterProgressBar(progressBefore, progressAfter);
            checkWaterCompletion(todayWaterEntry, waterGoal);
        }
    });
}

/**
 * changeCalorieEntryNumber
 * @param {} event 
 * @brief Will change the "onclick" number of the
 * add button to the number that is in the text field.
 * 
 * It will remove the onclick event to an error message
 * if a value that is not an integer is passed into
 * the text field
 */
function changeCalorieEntryNumber(event) {
    unitsToAdd = parseInt(event.target.value);

    if(Number.isInteger(unitsToAdd)) {
        document.getElementById('calorieAddButton').setAttribute
            ('onclick', 'addToCalorieEntry(' + unitsToAdd + ')');
    } else {
        alert("You have entered an invalid entry.");
        document.getElementById('calorieEntryText').value = "";
    }
}

/**
 * addToCalorieEntry
 * @param {} event 
 * @brief Add number of units to calorie entry.
 * This will also update the entry numbers
 * and move the calorie progress bar.
 */
function addToCalorieEntry(unitsToAdd) {
    progressBefore = Math.floor((todayCalorieEntry.entry_units_completed / calorieGoal.goal_units_to_complete) * 100);
    console.log(progressBefore);

    todayCalorieEntry.entry_units_completed += unitsToAdd;
    $.ajax({
        type: 'POST',
        url: 'updateTodayCalorieEntry',
        data: {
          'entry': todayCalorieEntry,
          'dateString': document.getElementById('todayDate').textContent
        },
        success: function(entry) {
            document.getElementById('todayEntryCalorie').textContent =
                todayCalorieEntry.entry_units_completed;

            progressAfter = Math.floor((todayCalorieEntry.entry_units_completed / calorieGoal.goal_units_to_complete) * 100);
            console.log(progressAfter);
            moveCalorieProgressBar(progressBefore, progressAfter);
            checkCalorieCompletion(todayCalorieEntry, calorieGoal);
        }
    });
}

/**
 * changeSleepEntryNumber
 * @param {} event 
 * @brief Will change the "onclick" number of the
 * add button to the number that is in the text field.
 * 
 * It will remove the onclick event to an error message
 * if a value that is not an integer is passed into
 * the text field
 */
function changeSleepEntryNumber(event) {
    unitsToAdd = parseInt(event.target.value);

    if(Number.isInteger(unitsToAdd)) {
        document.getElementById('sleepAddButton').setAttribute
            ('onclick', 'addToSleepEntry(' + unitsToAdd + ')');
    } else {
        alert("You have entered an invalid entry.");
        document.getElementById('sleepEntryText').value = "";
    }
}

/**
 * addToSleepEntry
 * @param {} event 
 * @brief Add number of units to sleep entry.
 * This will also update the entry numbers
 * and move the sleep progress bar.
 */
function addToSleepEntry(unitsToAdd) {
    console.log(todaySleepEntry.entry_units_completed);
    progressBefore = Math.floor((todaySleepEntry.entry_units_completed / sleepGoal.goal_units_to_complete) * 100);
    console.log(progressBefore);

    todaySleepEntry.entry_units_completed += unitsToAdd;
    $.ajax({
        type: 'POST',
        url: 'updateTodaySleepEntry',
        data: {
          'entry': todaySleepEntry,
          'dateString': document.getElementById('todayDate').textContent
        },
        success: function(entry) {
            document.getElementById('todayEntrySleep').textContent =
                todaySleepEntry.entry_units_completed;

            progressAfter = Math.floor((todaySleepEntry.entry_units_completed / sleepGoal.goal_units_to_complete) * 100);
            console.log(progressAfter);
            moveSleepProgressBar(progressBefore, progressAfter);
            checkSleepCompletion(todaySleepEntry, sleepGoal);
        }
    });
}

function checkWaterCompletion(entry, goal) {
    if(entry.entry_units_completed >= goal.goal_units_to_complete) {
        document.getElementById('waterGoalComplete').style.display = 'flex';
    }
}

function checkCalorieCompletion(entry, goal) {
    if(entry.entry_units_completed >= goal.goal_units_to_complete) {
        document.getElementById('calorieGoalComplete').style.display = 'flex';
    }
}

function checkSleepCompletion(entry, goal) {
    if(entry.entry_units_completed >= goal.goal_units_to_complete) {
        document.getElementById('sleepGoalComplete').style.display = 'flex';
    }
}

/**
 * move
 * current: current completed percent (45 is 45% complete)
 * percent: percent to move the bar to
 * 
 * Idea will be to set the "add" button to do all of this functionality,
 * when an amount is added to the goal it will calculate the percentage,
 * then make that into an integer, (45% = 45), then it will call
 * move(currentPercentage, currentPercentage + 45) if we added 45% of our goal.
 */
var i = 0;
function moveWaterProgressBar(current, percent) {
  if (i == 0) {
    i = 1;
    var elem = document.getElementById("myWaterBar");
    var width = current;
    var id = setInterval(frame, 45);
    function frame() {
      if (width >= percent) {
        clearInterval(id);
        i = 0;
      } else {
        //Prevents bar going further than 100 percent
        if(!(width >= 100)) {
            width++;
        }
        
        elem.style.width = width + "%";
        elem.innerHTML = width + "%";
      }
    }
  }
}

var j = 0;
function moveCalorieProgressBar(current, percent) {
  if (j == 0) {
    j = 1;
    var elem = document.getElementById("myCalorieBar");
    var width = current;
    var id = setInterval(frame, 45);
    function frame() {
      if (width >= percent) {
        clearInterval(id);
        j = 0;
      } else {
        //Prevents bar going further than 100 percent
        if(!(width >= 100)) {
            width++;
        }
        
        elem.style.width = width + "%";
        elem.innerHTML = width + "%";
      }
    }
  }
}

var k = 0;
function moveSleepProgressBar(current, percent) {
  if (k == 0) {
    k = 1;
    var elem = document.getElementById("mySleepBar");
    var width = current;
    var id = setInterval(frame, 45);
    function frame() {
      if (width >= percent) {
        clearInterval(id);
        k = 0;
      } else {
        //Prevents bar going further than 100 percent
        if(!(width >= 100)) {
            width++;
        }
        
        elem.style.width = width + "%";
        elem.innerHTML = width + "%";
      }
    }
  }
}

/**
 * formatDate
 * @param {} date to format
 * @returns formatted date in YYYY-MM-DD for mysql
 */
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}