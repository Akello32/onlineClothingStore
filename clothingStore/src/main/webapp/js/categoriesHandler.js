const categories = [...document.getElementsByClassName("category")];
const orderButtons = [...document.getElementsByClassName("orderButton")];

categories.forEach((element) => {
	const header = element.firstElementChild;
	header.addEventListener("click", () => {
		console.log(element.classList.toggle("opened"));
	})
})

orderButtons.forEach((element) => {
	element.addEventListener("click", () => {
		console.log(element.classList.toggle("clicked"));
	})
})