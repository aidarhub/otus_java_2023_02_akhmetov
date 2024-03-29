package ru.otus.crm.model;

import java.lang.annotation.Inherited;
import java.util.Objects;

public class Manager {

    @Id
    private Long no;
    private String label;
    private String param1;

    public Manager() {
    }

    public Manager(String label) {
        this.label = label;
    }

    public Manager(Long no, String label, String param1) {
        this.no = no;
        this.label = label;
        this.param1 = param1;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "no=" + no +
                ", label='" + label + '\'' +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Manager manager = (Manager) o;
//        return Objects.equals(no, manager.getNo()) && Objects.equals(label, manager.getLabel() ) & Objects.equals(param1, manager.getParam1() );
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(no);
//    }
//
//    public Manager clone() {
//        return new Manager(this.no, this.label, this.param1);
//    }
}
