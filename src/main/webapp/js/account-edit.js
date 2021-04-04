$(document).ready(function () {
    var addressAttr = '${user_address.getCity()}';
    let cityInput = document.getElementById('city-edit')
    cityInput.innerText = addressAttr
});