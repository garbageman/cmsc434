
var onoffswitch = document.getElementById("myonoffswitch");
var countrySelector = document.getElementById("countryID");

onoffswitch.addEventListener("click", onOffFunction);
countrySelector.addEventListener("click", myFunction2);

function onOffFunction() {
		var js = document.createElement("script");
		js.type = "text/javascript";
		js.src = "content_script.js";
	if(onoffswitch.checked){
		chrome.tabs.getSelected(null, function(tab) {
			  // Send a request to the content script.
			  chrome.tabs.sendRequest(tab.id, {action: "censor"}, function(response) {
				// censors the page
			  });
			});
	} else {
		chrome.tabs.getSelected(null, function(tab) {
			  // Send a request to the content script.
			  chrome.tabs.sendRequest(tab.id, {action: "refresh"}, function(response) {
				//refreshes the page
			  });
			});
	}
}

function myFunction2(){
	//
}
