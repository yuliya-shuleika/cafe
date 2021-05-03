function validateUserDishSearch(form){
    let dishPart = document.getElementById('user-search-dish')
    const regExp = /^.{1,30}$/
    if(regExp.test(dishPart.value)){
        form.submit()
    } else {
        dishPart.value = ""
    }
}
