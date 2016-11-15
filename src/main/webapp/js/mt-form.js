$(document).ready(function() {

	/*
	 * autocomplete CURRENCY
	 */
	$("input[xsdtype='Currency_Type']").addClass("currencyField");
	//add others here
	$(".currencyField").each(function (index, value){
		autocompleteCUR($(this));
	});

	/*
	 * Add custom class for add style on form
	 */
	$("div[level='1']").each(function() {
		$(this).find("label:first").addClass('root_element');
	});

});