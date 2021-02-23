const categories = [...document.getElementsByClassName("category")];
const orderButtons = [...document.getElementsByClassName("orderButton")];
const pruductCards = [...document.getElementsByClassName("productCard")];

categories.forEach((element) => {
	const header = element.firstElementChild;
	header.addEventListener("click", () => {
		element.classList.toggle("opened");
	})
})

orderButtons.forEach((element) => {
	element.addEventListener("click", () => {
		if (!element.classList.contains("block")) {
			element.classList.toggle("clicked");
		}
	})
})

pruductCards.forEach((element) => {
	//element.getElementsByClassName("checkOrder")[0].disabled = 1;
	element.addEventListener("click", {
		handleEvent(event) {
			const productCard = event.currentTarget;
			const sizeButtons = [...productCard.getElementsByClassName("checkSize")];
			const isSizeChosen = sizeButtons.some(btn => btn.checked);
			const orderButton = productCard.getElementsByClassName("checkOrder")[0];
			const lableButton = productCard.getElementsByClassName("orderButton")[0];
			console.log(sizeButtons);
			if (isSizeChosen === false) {
				orderButton.disabled = 1;
			} else {
				lableButton.classList.remove("block");
				orderButton.disabled = 0;
			}
		}
	})
})

