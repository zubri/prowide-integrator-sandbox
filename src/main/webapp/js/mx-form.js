$(document).ready(function() {

	/*
	 * Define an autocomplete for currencies
	 */
	var currencies = [ "USD", "EUR" ];
	$("input[xsdtype='Currency_Type']").autocomplete({
		source: currencies
	});
	
});