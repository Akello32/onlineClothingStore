const sizeButtons = [...document.getElementsByClassName("sizeWrap")];

const numbers = document.getElementsByClassName("numbers")[0];
const orderButton = document.getElementsByClassName("orderButton")[0];

if (sizeButtons == null) {
	console.log("sizeBut = null");
	numbers.textContent = "ТОВАР ЗАКОНЧИЛСЯ";
	orderButton.classList.toggle("ended");
} else {
	numbers.textContent = "Есть в наличии";
}