var url = chrome.extension.getURL('toolbar.html');
var height = '70px';
var iframe = "<iframe src='"+url+"' id='myOwnCustomFirstToolbar12345' style='height:"+height+"'></iframe>";

$('html').append(iframe);

/*$('body').css({
	'-webkit-transform': 'translateY('+height+')'
});*/

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
	var v = textNode.nodeValue;

	v = v.replace(/\bMaryland\b/g, "Banana");
	v = v.replace(/\bMARYLAND\b/g, "BANANA");
	v = v.replace(/\bMarylands\b/g, "Bananas");

	textNode.nodeValue = v;
}

