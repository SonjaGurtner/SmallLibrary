package model;

public enum BookRatingType {
    Ungraded("-"), Excellent("1"), Good("2"), Satisfactory("3"), Sufficient("4"), Insufficient("5");
    private String value;

    BookRatingType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}