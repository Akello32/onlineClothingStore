const categories = [...document.getElementsByClassName("category")];

categories.forEach((element) => {
	const header = element.firstElementChild;
	header.addEventListener("click", () => {
		console.log(element.classList.toggle("opened"));
	})
})