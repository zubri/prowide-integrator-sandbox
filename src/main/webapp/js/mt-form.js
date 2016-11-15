$(document).ready(function() {

	/*
	 * autocomplete CURRENCY
	 */
	$("input[xsdtype='Currency_Type']").addClass("currencyField");
	//add others here
	$(".currencyField").each(function (index, value){
		autocompleteCUR($(this));
	});

});