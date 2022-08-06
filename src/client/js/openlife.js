/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}
  
// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
    var openDropdown = dropdowns[i];
    if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
    }
    }
}
}

document.getElementById("incrementCounter").addEventListener("click", addIncrementDecrementCounterWidget);
function addIncrementDecrementCounterWidget() {
    const widgetDiv = document.createElement("div");
    widgetDiv.style.borderRadius = "25px";
    widgetDiv.style.background = "yellow";
    widgetDiv.style.padding = "20px";
    widgetDiv.style.width = "200px";
    widgetDiv.style.height = "150px";

    var counterParagraph = document.createElement("span");
    const counterButton = document.createElement("button");
        counterButton.innerText = "+";
    widgetDiv.appendChild(counterParagraph);
    widgetDiv.appendChild(counterButton);
    document.body.appendChild(widgetDiv);

    var count = 0;
    
    counterButton.addEventListener("click", function() {
        console.log(count);
        console.log(counterParagraph);
        count = count + 1;
        counterParagraph.innerHTML = count;
    });
}
