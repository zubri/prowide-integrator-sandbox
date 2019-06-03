/*
 * Example JS to control the style and behaviour of the MT message creation form.
 */
$(document).ready(function() {

	/*
	 * CURRENCY fields customization example:
	 * We call the autocomplete for currencies implemented in forms.js
	 */
	$(".currencyField").each(function (index, value){
		autocompleteCUR($(this));
	});

	/*
	 * BIC fields customization example:
	 * We add a "BIC" placeholder to denote the field expects a BIC.
	 * Here you may also add an autocomplete querying BICs from your
	 * own database or from Prowide Integrator SDK BIC directory.
	 */
	$(".bicField").each(function (index, value){
		$(this).attr("placeholder", "BIC")
	});

	/*
	 * Style customization from JS example:
	 * We add a custom style class using a JS jQuery selector on some elements
	 */
	$("div[level='1']").each(function() {
		$(this).find("label:first").addClass('root_element');
	});
	
	/*
	 * Fields visibility manipulation example:
	 * We hide several unused elements from the form
	 */
    $("div[xsdtype='InputIdentifier_Type']").hide();    // hide the direction select
    $("input[name='/Block2/MessageType-in']").prop('readonly', true);   // make message type readonly
	$("div[fieldname='Block1'] div[fieldname='ApplicationIdentifier']").hide();
	$("div[fieldname='Block1'] div[fieldname='ServiceIdentifier']").hide();
	$("div[fieldname='Block1'] div[fieldname='SessionNumber']").hide();
	$("div[fieldname='Block1'] div[fieldname='SequenceNumber']").hide();
	$("div[fieldname='F16a_1']").hide();
	$("div[fieldname='F16a_2']").hide();
	$("div[fieldname='F15a']").hide();

});