package code.shubham.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class TransactionRiskDetection {

    public class Transaction {
        private final String user;
        private final String deviceId;
        private final String creditCard;
        private final String ip;
        public Transaction(String user, String deviceId, String creditCard, String ip) {
            this.user = user;
            this.deviceId = deviceId;
            this.creditCard = creditCard;
            this.ip = ip;
        }

        public String getCreditCard() {
            return creditCard;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }
    }

    public class TransactionToParameterConvertor {
        public List<Parameter> convert(Transaction transaction) {
            List<Parameter> parameters = new ArrayList<>();
            addParameter(transaction.getUser(), ParameterType.USER, parameters);
            addParameter(transaction.getCreditCard(), ParameterType.CREDIT_CARD, parameters);
            addParameter(transaction.getIp(), ParameterType.IP, parameters);
            addParameter(transaction.getDeviceId(), ParameterType.DEVICE, parameters);
            return parameters;
        }

        private void addParameter(String value, ParameterType parameterType, List<Parameter> parameters) {
            if (value == null || value.isEmpty())
                return;
            parameters.add(new Parameter(value, parameterType));
        }
    }

    public enum ParameterType {
        USER, CREDIT_CARD, DEVICE, IP
    }

    private class Parameter {
        private final String value;
        private final ParameterType type;
        private Parameter(String value, ParameterType type) {
            this.value = value;
            this.type = type;
        }

        public ParameterType getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Parameter parameter = (Parameter) o;
            return Objects.equals(value, parameter.value) && type == parameter.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, type);
        }
    }

    private final Map<Parameter, Set<Parameter>> graph = new HashMap<>();
    private final Map<String, Double> riskScores = new HashMap<>();

    public TransactionRiskDetection(List<Transaction> transactions) {
        addTransactions(transactions);
    }

    private void addTransactions(List<Transaction> transactions) {
        transactions
                .stream()
                .map(transaction -> new TransactionToParameterConvertor().convert(transaction))
                .forEach(this::addToGraph);
    }

    private void addToGraph(List<Parameter> parameters) {
        int size = parameters.size();
        for (int i = 0; i < size; ++i) {
            Set<Parameter> neighbours = graph.computeIfAbsent(parameters.get(i), e -> new HashSet<>());
            for (int j = 0; j < size; ++j) {
                if (i == j)
                    continue;
                neighbours.add(parameters.get(j));
            }
        }
    }

    public void setUserRiskScore(String user, Double riskScore) {
        riskScores.put(user, riskScore);
    }

    public Boolean findRiskyUsersByUserAndAverageScore(String targetUser, Double averageScore) {
        return isUserGroupRiskyByScore(findRiskyUsersByUser(targetUser), averageScore);
    }

    public List<String> findRiskyUsersByUser(String targetUser) {
        Set<String> users = new HashSet<>();
        Queue<Parameter> q = new LinkedList<>();
        Set<Parameter> v = new HashSet<>();
        q.offer(new Parameter(targetUser, ParameterType.USER));
        v.add(q.peek());
        while (!q.isEmpty()) {
            Parameter p = q.poll();
            if (ParameterType.USER.equals(p.getType()))
                users.add(p.getValue());
            for (Parameter next : graph.getOrDefault(p, Collections.emptySet()))
                if (v.add(next))
                    q.offer(next);
        }

        return users.stream().toList();
    }


    public boolean isUserGroupRiskyByScore(List<String> userGroup, Double averageScore) {
        return userGroup.stream()
                .mapToDouble(user -> riskScores.getOrDefault(user, 0.0D))
                .filter(score -> score > 0.0D)
                .average()
                .orElse(0.0D) > averageScore;
    }

}
