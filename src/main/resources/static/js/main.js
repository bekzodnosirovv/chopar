// import { popupBurger, popupNav, popupClose } from ".header.js";
const popupBurger = document.getElementById("header-box__burger"),
  popupNav = document.getElementById("header-nav"),
  headerBlockBox = document.getElementById("header-block__box"),
  popupClose = document.querySelector(".popup-close"),
  headerBlock = document.getElementById("header-block"),
  authorization = document.getElementById("authorization"),
  authorizationBtn = document.getElementById("authorization-btn"),
  authorizationBlock = document.getElementById("authorization-block"),
  authorizationClose = document.getElementsByClassName(
    "authorization-close"
  )[0];

popupBurger.onclick = function () {
  popupNav.style.display = "block";
  headerBlockBox.style.display = "none";
  popupBurger.style.display = "none";
};
popupClose.onclick = function () {
  popupNav.style.display = "none";
  popupBurger.style.display = "block";
  headerBlockBox.style.display = "flex";
};

authorizationBtn.addEventListener("click", openModal);
authorizationClose.addEventListener("click", closeMpdal);
window.addEventListener("click", clickOutside);

function openModal() {
  authorization.style.display = "flex";
}

function closeMpdal() {
  authorization.style.display = "none";
}

function clickOutside(e) {
  if (e.target == authorization) {
    authorization.style.display = "none";
  }
}
