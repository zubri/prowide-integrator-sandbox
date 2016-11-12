$(document).ready(function() {

	/*
	 * form validate button action
	 */
	$("#validate").click(function (ev){
		/*
		 * first of all, always build message
		 */
		buildMessage();
		/*
		 * call jQuery validation plugin
		 */
		$("form").valid();
		/*
		 * prevent form submit because we just want to validate
		 */
		ev.preventDefault();
	});
	
	/*
	 * form save button action
	 */
	$("#save").click(function (ev){
		/*
		 * first of all, always build message
		 */
		buildMessage();
		/*
		 * call submit (this could also be made conditional to validation)
		 */
		$("form").submit();
	});
	
	/*
	 * Define an autocomplete for currencies
	 */
	var currencies = [ "USD", "EUR" ];
	$("input[xsdtype='Currency_Type']").autocomplete({
		source: currencies
	});
	
});