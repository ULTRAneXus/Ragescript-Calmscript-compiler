var var1 = 0
var var2 = 1
var var3 = 20
var var4 = 0
fun main() {
	//this code iteratively generates the var3rd fibonacci number, where the sequence is initialised with 0 and 1
	//subtract 2 from var3 because the first 2 numbers are already initialised
	var3 = var3 - (2)
	//check if var3 is even
	var4 = var3 % 2
	//if var3 is even, do 1 iteration of fibonacci
	if (var4 != 0) {
		var3 = var3 - (1)
		var1 = var1 + (var2)
	}
	//do 2 iterations of fibonacci until var3 = 0
	while (var3 != 0) {
		var1 = var1 + (var2)
		var2 = var1 + (var2)
		var3 = var3 - (2)
	}
	//print the result and newline
	print("${var2}")
	print("${Char(10)}")
}
