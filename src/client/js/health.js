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
    
}

function getWaterGoal() {
    $.ajax({
        type: 'GET',
        url: 'getHealthWaterGoal',
        success: function(entry) {
            waterGoal = entry;
            console.log(waterGoal);
        }
    });
}

function getCalorieGoal() {
    $.ajax({
        type: 'GET',
        url: 'getHealthCalorieGoal',
        success: function(entry) {
            calorieGoal = entry;
            console.log(waterGoal);
        }
    });
}

function getSleepGoal() {
    $.ajax({
        type: 'GET',
        url: 'getHealthSleepGoal',
        success: function(entry) {
            sleepGoal = entry;
            console.log(waterGoal);
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
            console.log(entry);
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
            console.log(entry);
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
            console.log(entry);
        }
    });
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
function move(current, percent) {
  if (i == 0) {
    i = 1;
    var elem = document.getElementById("myBar");
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

move(0, 45);