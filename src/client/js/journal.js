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
    //Set hidden form div to today's date, noting that that will be updated
    var date = new Date();
    var current_date = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate(); //yyyy-mm-dd

    /**
     * Check if there is a journal entry for the current
     * day, if there is, grab it from mysql and populate
     * the journal.
     */
     $.ajax({
        type: 'POST',
        url: 'getJournalEntryByDate',
        data: {
          'date': current_date
        },
        success: function(entry) {
            setJournalInputValues(entry);
        }
    });
}

function populateJournalEntry(e) {
    $.ajax({
        type: 'POST',
        url: 'getJournalEntryByDate',
        data: {
          'date': e.target.value
        },
        success: function(entry) {
            setJournalInputValues(entry);
        }
    });
}

function setJournalInputValues(entry) {
    console.log(entry);
}