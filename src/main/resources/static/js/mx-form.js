/*
 * Example JS to control the style and behaviour of the MX message creation form.
 */
$(document).ready(function() {

	/*
	 * Fields visibility manipulation example:
	 * We hide several unused elements from the form
	 */
	$("div[fieldname='AppHdr'] div[fieldname='CharSet']").hide();
	$("div[fieldname='AppHdr'] div[fieldname='CreDt']").hide();
	$("div[fieldname='AppHdr'] div[fieldname='MsgDefIdr']").hide();

	/*
	 * Style customization from JS example:
	 * We add a custom style class using a JS jQuery selector on some elements
	 */
	$( "div[fieldname='To'] label:first").addClass("firstNode");
	$( "div[fieldname='Fr'] label:first" ).addClass("firstNode");
	$( "div[fieldname='AppHdr'] label:first").addClass("root_element");
	$( "div[fieldname='Document'] label:first").addClass("root_element");

	/*
	 * CURRENCY fields customization example:
	 * We call the autocomplete for currencies implemented in forms.js
	 */
	$(".currencyField").each(function (index, value){
		autocompleteCUR($(this));
	});

});