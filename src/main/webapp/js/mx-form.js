$(document).ready(function() {

	/*
	 * Hide unused elements
	 */
	$("div[fieldname='AppHdr'] div[fieldname='CharSet']").hide();
	$("div[fieldname='AppHdr'] div[fieldname='CreDt']").hide();
	$("div[fieldname='AppHdr'] div[fieldname='MsgDefIdr']").hide();

	/*
	 * Add custom class for add style on form
	 */
	$( "div[fieldname='To'] label:first").addClass("firstNode");
	$( "div[fieldname='Fr'] label:first" ).addClass("firstNode");
	$( "div[fieldname='AppHdr'] label:first").addClass("fake_accordion_main_title");
	$( "div[fieldname='Document'] label:first").addClass("fake_accordion_main_title");

	/*
	 * autocomplete CURRENCY
	 */
	$("input[xsdtype='ActiveCurrencyCode']").addClass("currencyField");
	$("input[xsdtype='ActiveOrHistoricCurrencyCode']").addClass("currencyField");
	$("input[xsdtype='CurrencyCode']").addClass("currencyField");
	$(".currencyField").each(function (index, value){
		autocompleteCUR($(this));
	});

});