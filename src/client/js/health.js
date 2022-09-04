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
let todaysDateFormatted = todaysDate.getFullYear() + "-" + (todaysDate.getMonth()+1) + "-" + todaysDate.getDate(); //yyyy-mm-dd

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
            todayWaterEntry = entry;

            //Set health attributes corresponding to waterEntry
            document.getElementById('todayEntryWater').textContent =
                todayWaterEntry.entry_units_completed;
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
            todayCalorieEntry = entry;

            //Set health attributes corresponding to calorieEntry
            document.getElementById('todayEntryCalorie').textContent =
                todayCalorieEntry.entry_units_completed;
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
}

function setCalorieProgressBar() {
    /**
     * Will call getGoal, then will call getTodayEntry
     * Then will call Move function based off of
     * the current percentage completed as an integer.
     */
}

function setSleepProgressBar() {
    /**
     * Will call getGoal, then will call getTodayEntry
     * Then will call Move function based off of
     * the current percentage completed as an integer.
     */
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
    console.log(unitsToAdd);
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
    console.log(unitsToAdd);
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
    console.log(unitsToAdd);
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

moveWaterProgressBar(0, 45);

var i = 0;
function moveCalorieProgressBar(current, percent) {
  if (i == 0) {
    i = 1;
    var elem = document.getElementById("myCalorieBar");
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

moveCalorieProgressBar(0, 45);