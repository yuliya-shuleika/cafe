package com.yuliana.cafe.model.entity;

/**
 * Review entity.
 *
 * @author Yulia Shuleiko
 */
public class Review {

    private int reviewId;
    private String header;
    private String text;
    private int rating;
    private ReviewStatus status;

    /**
     * Constructs the {@code Review} object
     */
    public Review() {
    }

    /**
     * Constructs the {@code Review} object with given review's id,
     * header, text, rating, status.
     *
     * @param reviewId id of the review
     * @param header header of the review
     * @param text text of the review
     * @param rating rating (from 1 to 5 points) of the review
     * @param status {@code ReviewStatus} object represents status
     *                                   of the review (new, approved or rejected)
     */
    public Review(int reviewId, String header, String text, int rating, ReviewStatus status) {
        this.reviewId = reviewId;
        this.header = header;
        this.text = text;
        this.rating = rating;
        this.status = status;
    }

    /**
     * Constructs the {@code Review} object with given header, text, rating, status.
     *
     * @param header header of the review
     * @param text text of the review
     * @param rating rating (from 1 to 5 points) of the review
     * @param status {@code ReviewStatus} object represents status
     *                                   of the review (new, approved or rejected)
     */
    public Review(String header, String text, int rating, ReviewStatus status) {
        this.header = header;
        this.text = text;
        this.rating = rating;
        this.status = status;
    }

    /**
     * Getter method of the review's id.
     *
     * @return id of the review
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * Getter method of the header.
     *
     * @return header of the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Getter method of the text.
     *
     * @return text of the review.
     */
    public String getText() {
        return text;
    }

    /**
     * Getter method of the rating.
     *
     * @return rating (from 1 to 5 points) of the review
     */
    public int getRating() {
        return rating;
    }

    /**
     * Setter method of the header.
     *
     * @param header header of the review
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Setter method of the text.
     *
     * @param text text of the review
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter method of the rating.
     *
     * @param rating rating (from 1 to 5 points) of the review
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Getter method of the review's status.
     *
     * @return {@code ReviewStatus} object represents status
     * of the review (new, approved or rejected)
     */
    public ReviewStatus getStatus() {
        return status;
    }

    /**
     * Setter method of the review's status.
     *
     * @param status {@code ReviewStatus} object represents status
     *                                   of the review (new, approved or rejected)
     */
    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        if (reviewId != review.reviewId) return false;
        if (rating != review.rating) return false;
        if (header != null ? !header.equals(review.header) : review.header != null) return false;
        if (text != null ? !text.equals(review.text) : review.text != null) return false;
        return status == review.status;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("reviewId=").append(reviewId);
        sb.append(", header='").append(header).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
