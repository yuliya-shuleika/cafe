const stars_count = 5
$(document).ready(function (){
    $('.give-feedback-button').click(function (){
        let feedback = document.getElementById('feedback')
        feedback.style.display = 'block';
    });
    $('.feedback-close').click(function (){
        let feedback = document.getElementById('feedback')
        feedback.style.display = 'none';
    });
    $('.feedback-star').hover( function (){
        let mark = this.id;
        let rating = this.closest('div');
        let stars = rating.getElementsByClassName('feedback-star')
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
        rating.getElementsByTagName("input").value = count;
    })
});