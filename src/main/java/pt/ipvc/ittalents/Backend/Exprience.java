package pt.ipvc.ittalents.Backend;

import java.time.LocalDate;

public class Exprience {
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private int idSkill;

    public Exprience(String company, LocalDate startDate, LocalDate endDate, int idSkill) {
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idSkill = idSkill;
    }
    public String getCompany() {
        return company;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public int getIdSkill() {
        return idSkill;
    }
}
