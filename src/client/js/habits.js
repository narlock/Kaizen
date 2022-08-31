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

window.onload = function() {
    populateHabitsTable();
}

function populateHabitsTable() {
    /**
     * TODO
     * Populate the habits table
     * 
     * Check/update the streak status of daily habits
     * 
     * Add only the habits that correspond to the current day
     * 
     * If a habit is already complete, make sure it is checked and the
     * attribute 'onclick' = 'false' is added, so it cannot be re-updated.
     */
}

function updateHabit(event) {
    //Get the checkbox
    checkBox = event.path[0];

    //Once a habit is checked, it cannot be unchecked!
    checkBox.setAttribute('onclick', 'return false');

    //Id of checkbox corresponds to habit_id in db
    id = checkBox.getAttribute('id');
    console.log(id);

    /*
     *  Will update the status of the event to complete
     *
     *  Will check previous day with current day, if they
     *  are less than 24 hours apart and do not appear on
     *  the same calendar day, then the streak will be incremented
     * 
     *  If the previous day is more than 24 hours apart,
     *  the streak will equal 0.
     */
    
}