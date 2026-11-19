package code.shubham.java;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamAPIPractice {

    private class Department {
        private String id;
        private String name;
    }

    private class Employee {
        private final String id;
        private String name;
        private Department department;
        private BigDecimal salary;

        private Employee(String id) {
            this.id = id;
        }

        public Department getDepartment() {
            return department;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public Map<Department, String> getHighestSalaryByDepartment(List<Employee> employees) {
        return
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.reducing((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()) <= 0 ? e1 : e2),
                                        optionalEmployee -> optionalEmployee.map(Employee::getName).orElseThrow())));
    }

    private static class HighestSalaryAccumulator {
        private Employee employee;
    }

    private class HighestEmployeeSalaryForDepartmentCollector implements Collector<Employee, HighestSalaryAccumulator, String> {

        @Override
        public Supplier supplier() {
            return HighestSalaryAccumulator::new;
        }

        @Override
        public BiConsumer<HighestSalaryAccumulator, Employee> accumulator() {
            return (acc, employee) -> {
                if (acc.employee == null || employee.getSalary().compareTo(acc.employee.getSalary()) > 0)
                    acc.employee = employee;
            };
        }

        @Override
        public BinaryOperator<HighestSalaryAccumulator> combiner() {
            return (left, right) -> {
                if (left.employee == null) {
                    return right;
                }
                if (right.employee != null
                        && right.employee.getSalary()
                        .compareTo(left.employee.getSalary()) > 0) {
                    left.employee = right.employee;
                }
                return left;
            };
        }

        @Override
        public Function<HighestSalaryAccumulator, String> finisher() {
            return acc -> {
                if (acc.employee == null)
                    throw new NoSuchElementException("No employee found");
                return acc.employee.getName();
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }

}
