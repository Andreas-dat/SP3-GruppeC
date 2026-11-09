public enum Category {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    FILMNOIR("Film-Noir"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIFI("Sci-Fi"),
    SPORT("Sport"),
    TALKSHOW("Talk-Show"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private String category;

    Category(String category){
        this.category = category;
    }
}
