/*
 * Example JS to control the behaviour of the message creation page
 *
 * The buildMessage() function implementation is part of the guitools webjar
 */
$(document).ready(function() {

	// form validate button action
	$("#validate").click(function (ev){
		// first of all, always build message
		buildMessage();
		// call jQuery validation plugin
		$("form").valid();
		// prevent form submit because we just want to validate
		ev.preventDefault();
	});
	
	// form save button action
	$("#save").click(function (ev){
		// first of all, always build message
		buildMessage();
		// call submit
		$("form").submit();
	});
	
});
	
/*
 * Implements autocomplete for currency codes, using fixed values
 * This could be loaded from JSON service from backend
 */
function autocompleteCUR(input) {
	var currencies = [ "USD", "EUR" ];
	$(input).autocomplete({
		source: currencies
	});
}
