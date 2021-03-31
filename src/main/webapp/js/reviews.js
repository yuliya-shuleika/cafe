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