var chartDataStr = decodeHtml(chartDataX); // chartDataX sebagai argumen di method decodeHtml ini dari variable yg di define di home.html
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for(var i=0; i < arrayLength; i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
	type: 'pie',
	// The data for our dataset
	data: {
		labels: labelData, //['Januari', 'February', 'March'],
		datasets: [{
			label: 'My First dataset',
			backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
	        data: numericData //[5, 10, 5]
	    }]
	},
	
	// Configuration options go here
	options: {
		title: {
			display: true,
			text: 'Project Statuses'
		}
	}
});

// [{"value": 1, "label": "COMPLETED"}, {"value": 2, "label": "INPROGRESS"}, {"value": 1, "label": "NOTSTARTED"}]
function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}