package code.randomcode;

import java.util.List;
import java.util.stream.Collectors;

public class dasd {

    class SampleClass {
        private String testString;
        private List<dasd.Label> labels;

        public String getTestString() {
            return testString;
        }

        public void setTestString(String testString) {
            this.testString = testString;
        }

        public List<dasd.Label> getLabels() {
            return labels;
        }

        public void setLabels(List<dasd.Label> labels) {
            this.labels = labels;
        }
    }

    class Label {
        private String labelName;
        private String labelValue;

        public String getName() {
            return labelName;
        }

        public String getValue() {
            return labelValue;
        }
    }

    public static void main(String[] args) {
        List<SampleClass> sampleClassList = null;
        sampleClassList.stream().filter(entry ->
                entry.getLabels().stream().filter(label ->
                        label.getName().equals("some_name") &&
                                label.getValue().equals("some_value")).count() > 0).findFirst();
    }
}
