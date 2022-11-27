package code.shubham.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSort {

    private static class Employee {
        private String name;
        private String department;

        private Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }

        @Override
        public String toString() {
            return name + " : " + department;
        }
    }
}
