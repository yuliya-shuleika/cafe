const stars_count = 5
$(document).ready(function (){
    $('.give-feedback-button').click(function (){
        let feedback = document.getElementById('feedback')
        feedback.style.display = 'block';
    });
    $('.edit-close').click(function (){
        let feedback = document.getElementById('feedback')
        feedback.style.display = 'none';
    });
    $('.edit-star').hover( function (){
        let mark = this.id;
        let rating = this.closest('div');
        let stars = rating.getElementsByClassName('edit-star')
        let count
        let i = 0
        switch (mark) {
            case 'star_one':
                count = 1
                break;
            case 'star_two':
                count = 2
                break;
            case 'star_three':
                count = 3
                break;
            case 'star_four':
                count = 4
                break;
            case 'star_five':
                count = 5
                break;
        }
        while (i < count){
            stars[i].style.color = '#4CAF50';
            i++;
        }
        while (i < stars_count){
            stars[i].style.color = '#545454';
            i++;
        }
        console.log(count)
        rating.getElementsByTagName("input")[0].value = count;
    })
    let review_ratings = document.getElementsByClassName('review-rating')
    console.log(review_ratings)
    for(let i = 0; i < review_ratings.length; i++){
        let stars = review_ratings[i].getElementsByClassName('fa-star')
        let rating = review_ratings[i].getElementsByClassName('review-stars-count')[0].innerHTML
        console.log(rating)
        let j = 0
        while (j < rating){
            stars[j]. style.color = '#4CAF50';
            j++;
        }
    }
});

function validateGiveFeedbackForm(form) {
    console.log("rdieriooierio")
    let header = document.getElementById('add-review-header')
    let text = document.getElementById('add-review-text')
    let error = document.getElementById('add-review-error-label')
    let isValid = validateReviewFields(header, text)
    if (!isValid) {
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
}

function validateReviewFields(header, text){
    let isValid = true
    if (!validateHeader(header)){
        isValid = false
    }
    if (!validateText(text)){
        isValid = false
    }
    return isValid
}

function validateHeader(header){
    let isValid
    const regExp = /^.{1,50}$/
    if(regExp.test(header.value)){
        isValid = true
    } else {
        header.value = ""
        isValid = false
    }
    return isValid
}

function validateText(text){
    let isValid
    const regExp = /^(\s|.){1,500}$/
    if(regExp.test(text.value)){
        isValid = true
    } else {
        text.value = ""
        isValid = false
    }
    return isValid
}