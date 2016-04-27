
var onoffswitch = document.getElementById("myonoffswitch");

onoffswitch.addEventListener("click", myFunction);

function myFunction() {
		var js = document.createElement("script");
		js.type = "text/javascript";
		js.src = "content_script.js";
	if(onoffswitch.checked){
		chrome.tabs.getSelected(null, function(tab) {
			  // Send a request to the content script.
			  chrome.tabs.sendRequest(tab.id, {action: "censor"}, function(response) {
				//censors the page
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

function walk(node)
{
	var child, next;

	switch ( node.nodeType )
	{
		case 1:  // Element
		case 9:  // Document
		case 11: // Document fragment
			child = node.firstChild;
			while ( child )
			{
				next = child.nextSibling;
				walk(child);
				child = next;
			}
			break;

		case 3: // Text node
			handleText(node);
			break;
	}
}



function handleText(textNode)
{
  var noun_arr = Array("time","issue","year","side","people","kind","way","head","day","house","man","service","thing","friend","woman","father","life","power","child","hour","world"	,"game","school"	,"line","state"	,"end","family"	,"member","student"	,"law","group"	,"car","country"	,"city","problem"	,"community","hand"	,"name","part"	,"president","place"	,"team","case"	,"minute","week"	,"idea","company"	,"kid","system"	,"body","program"	,"information","question"	,"back","work"	,"parent","government" ,"face","number"	,"others","night"	,"level","Mr"	,"office","point"	,"door","home"	,"health","water"	,"person","room"	,"art","mother"	,"war","area"	,"history","money"	,"party","storey"	,"result","fact"	,"change","month"	,"morning","lot"	,"reason","right"	,"research","study"	,"girl","book"	,"guy","eye"	,"food","job"	,"moment","word"	,"air","business"	,"teacher");

	var v = textNode.nodeValue;

  for (var i = 0; i < noun_arr.length; i++){
    v = v.replace(noun_arr[i], "X~X~X~X~X~X~X");
  }
	
	textNode.nodeValue = v;
}
