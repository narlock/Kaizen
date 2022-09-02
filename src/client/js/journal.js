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

    //Set the current value of the journal to today's date
    var setDate = current_date.replace(/(^|\D)(\d)(?!\d)/g, '$10$2');
    document.getElementById('datePicker').value = setDate;

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
            setJournalInputValues(entry, setDate);
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

function setJournalInputValues(entry, setDate) {
    /**
     * If there is not an entry for the specified date,
     * the entry variable will be equal to the empty string.
     * 
     * The journal will simply just be a blank journal entry,
     * but the date will remain the same date so if it is
     * updated, the entry will be updated as well.
     * 
     * If the entry is already in the database, the form
     * fields will be populated with its corresponding
     * fields. The user will then be able to edit the journal
     * entry.
     */
    
    if(entry == "") {
        //Entry does not exist in database, only set hidden date to
        //date from setDate
    } else {
        //Entry exists in database and is stored as entry
    }
}