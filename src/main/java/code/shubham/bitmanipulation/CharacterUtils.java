package code.shubham.bitmanipulation;

public class CharacterUtils {

    public static char flipCase(char a) {
//        return (char) (a ^ (1 << 5));
        return (char) (a ^ 0x20);
    }

    public static void main(String[] args) {
        System.out.println(CharacterUtils.flipCase('a'));
        System.out.println(CharacterUtils.flipCase('A'));
    }

}
