var url = chrome.extension.getURL('toolbar.html');
var height = '50px';
var iframe = "<iframe src='"+url+"' id='myOwnCustomFirstToolbar12345' style='height:"+height+"'></iframe>";

$('html').append(iframe);

walk(document.body);
setTimeout(function () {
	walk(document.body);
}, 1000);

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
    v = v.replace(noun_arr[i], "banana");
  }

	textNode.nodeValue = v;
}
