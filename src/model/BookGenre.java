package model;

public enum BookGenre {
    //genres that most of my books have, can of course simply be extended to include more genres
    Thriller, Romance, Fantasy, Mystery, SciFi, Novel, Drama, Western, Biography, NONE;

    @Override
    public String toString() {
        switch (this) {
            case Thriller:
                return "Thriller";
            case Romance:
                return "Romance";
            case Fantasy:
                return "Fantasy";
            case Mystery:
                return "Mystery";
            case SciFi:
                return "SciFi";
            case Novel:
                return "Novel";
            case Drama:
                return "Drama";
            case Western:
                return "Western";
            case Biography:
                return "Biography";
            case NONE:
                return "-";
            default:
                return "invalid";
        }
    }
}