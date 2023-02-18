// Get the current date
var currentDate = new Date();

// Extract the month and year
var currMonth = currentDate.getMonth();
var currYear = currentDate.getFullYear();

const months =["January","February","March","April","May","June","July","August","September","October","November","December"];

// Update the content of the element
document.getElementById("current-date").innerHTML = months[currentDate.getMonth()] + " " + currYear;





// Get the number of days in the month
var daysInMonth = new Date(currYear, currMonth + 1, 0).getDate();

// Generate HTML for each day of the month
var con = 0;
var html = "<div class='row px-2 text-center'>";
for (var day = 1; day <= daysInMonth; day++) {
	con++;	
  	html += "<div class = 'col'>" + day + "</div>";
  	if(con%7 == 0&& day != daysInMonth){
  		html += "</div><div class = 'row px-2 text-center'>";
  	}
}

console.log(html);
var calenderRow = document.getElementById("calender");
// Update the content of the element with the generated HTML
calenderRow.innerHTML = html;