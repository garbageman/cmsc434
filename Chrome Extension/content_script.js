 //var url = chrome.extension.getURL('popup.html');
// var height = '50px';
 //var iframe = "<iframe src='"+url+"' id='myOwnCustomFirstToolbar12345' style='height:"+height+"'></iframe>";

 //$('html').append(iframe);

chrome.extension.onRequest.addListener(function(request, sender, sendResponse) {
 if (request.action == "censor")
   censor();
 if (request.action == "refresh") {
   document = chrome.extension.dom;
   document.body.appendChild(document.createElement('div'));
 }
});

function censor()
{
  // console.log('This is sadness');
  // console.log(document);
  chrome.extension.dom = document.cloneNode(true);
  // console.log('This is sadness');
  // console.log(chrome.extension.dom);
	walk(document.body);
	setTimeout(function () {
	  walk(document.body);
    console.log(chrome.extension.dom);
	}, 1000);

}

function walk(node)
{
	// Source: http://is.gd/mwZp7E

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
    var s = "";
		for (var j = 0; j < noun_arr[i].length; j++){
			s+= "■";
		}
    v = v.replace(" "+noun_arr[i]+" ", " "+s+" ");
  }
	textNode.nodeValue = v;
}
