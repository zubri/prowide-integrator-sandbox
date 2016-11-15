$(document).ready(function() {

	/*
	 * Hide unused elements
	 */
	$("div[fieldname='AppHdr'] div[fieldname='CharSet']").hide();
	$("div[fieldname='AppHdr'] div[fieldname='CreDt']").hide();
	$("div[fieldname='AppHdr'] div[fieldname='MsgDefIdr']").hide();

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