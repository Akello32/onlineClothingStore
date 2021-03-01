const rows = [...document.getElementsByClassName("productCard")];
const totalPriceInput = document.getElementsByClassName("priceOrderClass")[0];

rows.forEach((element) => {
	const input = element.getElementsByClassName("numberInput")[0];
	const price = element.getElementsByClassName("finalPrice")[0];
	const startPrice = +price.textContent;
	price.textContent = startPrice;
	calcFinalPrice();

	input.addEventListener("change", (e) => {
		if (!Number.isNaN(+e.target.value)) {
			price.textContent = +e.target.value * startPrice;
			calcFinalPrice();
		}
	})
})

function calcFinalPrice() {
	const finalPriceElemets = [...document.getElementsByClassName("finalPrice")];
	const total = [...document.getElementsByClassName("totalPrice")];
	let totalPrice = 0;

	finalPriceElemets.forEach((element) => {
		totalPrice += +element.textContent;
	})

	total.forEach((element) => {
		element.textContent = totalPrice;
	})
	
	totalPriceInput.value = total[1].textContent;
	console.log(totalPriceInput);
}