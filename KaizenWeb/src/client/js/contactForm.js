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

function getContactFromDb(e) {
    //e.target.value will be the contacts username
    $.ajax({
        type: 'POST',
        url: 'getContact',
        data: {
          'contactName': e.target.value
        },
        success: function(contact) {
            setFormInputValues(contact);
        }
    });
}

function setFormInputValues(contact) {
    console.log(contact);
    errorMessageElement = document.getElementById('errorMessage');
    //If the contact is null, then there was no contact with specified name
    if(contact == "") {
        errorMessageElement.classList.remove('hidden');
        errorMessageElement.textContent = "No contact was found with name " + document.getElementById('contactSearch').value;

        //ENSURE CREATE MODE
        document.getElementById('contactId').value = "";
        document.getElementById('formTitle').textContent = "Create Contact";
        document.getElementById('submitButton').textContent = "Create Contact";

        //POPULATES WITH BLANK
        document.getElementById('contactName').value = "";
        document.getElementById('contactBirthday').value = "";
        document.getElementById('contactPhone').value = "";
        document.getElementById('contactMessenger').value = "";
        document.getElementById('contactWhatsapp').value = "";
        document.getElementById('contactDiscord').value = "";
        document.getElementById('contactEmail').value = "";
        document.getElementById('contactNote').value = "";
    } 
    //If contact was found, then create contact with given information
    else {
        if(!errorMessageElement.classList.contains('hidden')) { errorMessageElement.classList.add('hidden'); }
        //UPDATE MODE
        document.getElementById('contactId').value = contact.contact_id;
        document.getElementById('formTitle').textContent = "Update Contact";
        document.getElementById('submitButton').textContent = "Update Contact";

        //POPULATES WITH CONTACT FIELD
        document.getElementById('contactName').value = contact.contact_name;

        birthdayDate = new Date(contact.contact_birthday);
        birthdayDateString = birthdayDate.getFullYear() + "-" + (birthdayDate.getMonth()+1) + "-" + birthdayDate.getDate(); //yyyy-mm-dd
        setDate = birthdayDateString.replace(/(^|\D)(\d)(?!\d)/g, '$10$2');
        document.getElementById('contactBirthday').value = setDate;

        //For birthday, this will require some manipulation
        document.getElementById('contactPhone').value = contact.contact_phone;
        document.getElementById('contactMessenger').value = contact.contact_fb;
        document.getElementById('contactWhatsapp').value = contact.contact_whatsapp;
        document.getElementById('contactDiscord').value = contact.contact_discord;
        document.getElementById('contactEmail').value = contact.contact_email;
        document.getElementById('contactNote').value = contact.contact_note;
    }
}