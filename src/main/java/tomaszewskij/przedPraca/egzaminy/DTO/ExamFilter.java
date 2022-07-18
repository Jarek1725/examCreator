package tomaszewskij.przedPraca.egzaminy.DTO;

public class ExamFilter {
    private String category;
    private String school;

    public ExamFilter(String category, String school) {
        this.category = category;
        this.school = school;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
