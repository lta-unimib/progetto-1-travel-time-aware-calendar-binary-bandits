// Get the current date
var currentDate = new Date();

// Extract the month and year
var currMonth = currentDate.getMonth();
var currYear = currentDate.getFullYear();

var firstDayOfTheMonth = new Date(currYear, currMonth, 1).getDay();
var lastDayOfTheMonth = new Date(currYear, currMonth, 0).getDay();
console.log(firstDayOfTheMonth);

const months =["January","February","March","April","May","June","July","August","September","October","November","December"];

// Update the content of the element
document.getElementById("current-date").innerHTML = months[currMonth] + " " + currYear;

  // Get the number of days in the month
var daysInMonth = new Date(currYear, currMonth + 1, 0).getDate();


//renders calendar page
function render(daysInMonth) {
	var firstDayOfTheMonth = new Date(currYear, currMonth, 1).getDay();
	var lastDayOfLastMonth = new Date(currYear, currMonth, 0).getDate();
	var lastDayOMonth = new Date(currYear, currMonth, daysInMonth).getDay();

// Generate HTML for each day of the month
	var con = 0;
	var html = "<div class='row px-2 text-center mb-4'>";
	//da modificare per firstday of month
	for (var day = firstDayOfTheMonth; day > 0; day--) {
		con++;	
  		html += "<div class = 'col'>" + (lastDayOfLastMonth - day + 1) + "</div>";
  		if(con%7 == 0&& day != daysInMonth){
  			html += "</div><div class = 'row px-2 text-center mb-4'>";
  		}

	}

	for (var day = 1; day <= daysInMonth; day++) {
		con++;	
  		html += "<div class = 'col'>" + day + "</div>";
  		if(con%7 == 0&& day != daysInMonth){
  			html += "</div><div class = 'row px-2 text-center mb-4'>";
  		}

	}

	for (var day = lastDayOMonth; day < 6; day++) {
		con++;	
  		html += "<div class = 'col'>" + (day - lastDayOMonth +1) + "</div>";
  		if(con%7 == 0&& day != daysInMonth){
  			html += "</div><div class = 'row px-2 text-center mb-4'>";
  		}

	}

	console.log(html);
	var calenderRow = document.getElementById("calender");
	// Update the content of the element with the generated HTML
calenderRow.innerHTML = html;
}

// Get the number of days in the month
var daysInMonth = new Date(currYear, currMonth + 1, 0).getDate();

render(daysInMonth);

const arrowLeft = document.querySelector('.bi-arrow-left');


arrowLeft.addEventListener('click', function() {
	currMonth = currMonth -1;
	if(currMonth<0){
		date = new Date(currYear, currMonth);
		currYear = date.getFullYear();
		currMonth = date.getMonth();
	}
	// Get the number of days in the month
 	var daysInMonth = new Date(currYear, currMonth + 1, 0).getDate();

	document.getElementById("current-date").innerHTML = months[currMonth] + " " + currYear;
	// Generate HTML for each day of the month
	render(daysInMonth);

  console.log(currMonth);
});

const arrowRight = document.querySelector('.bi-arrow-right');

arrowRight.addEventListener('click', function() {
	currMonth = currMonth +1;
	if(currMonth>11){
		date = new Date(currYear, currMonth);
		currYear = date.getFullYear();
		currMonth = date.getMonth();
	}
	// Get the number of days in the month
var daysInMonth = new Date(currYear, currMonth + 1, 0).getDate();
document.getElementById("current-date").innerHTML = months[currMonth] + " " + currYear;

// Generate HTML for each day of the month
render(daysInMonth);

  console.log(currMonth);
  console.log(arrowRight);
});

