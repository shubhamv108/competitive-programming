package code.shubham.oa.kredx;

public class MaxScoreBatsmen {
    public static void main(String[] args) {
        String createtable = "create table batsmen (name text, score INT(11), matchdate text);";
        String insert = " insert into batsmen values('A', 200, '10-Jan-2016');";
        String select =
                "select name " +
                "from batsmen " +
                "where Year(str_to_date(matchdate, '%d-%M-%Y')) = 2016 " +
                "and month(str_to_date(matchdate, '%d-%M-%Y')) = 1 " +
                "group by name " +
                "order by sum(score) desc " +
                "limit 1;";
    }
}
