package code.shubham.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsWithinTwoEditsOfDictionary {
    class Solution {
        public List<String> twoEditWords(String[] Q, String[] D) {
            Node trie = new Node();
            Arrays.stream(D).forEach(d -> trie.add(d, 0));
            return Arrays.stream(Q)
                    .filter(q -> trie.search(q, 0, 2))
                    .toList();
        }

         class Node {
             boolean isEOW;
             Node[] next = new Node[26];
             void add(String a, int idx) {
                 if (a.length() == idx) {
                     isEOW = true;
                     return;
                 }

                 int nextIdx = a.charAt(idx) - 'a';

                 if (next[nextIdx] == null)
                     next[nextIdx] = new Node();

                 next[nextIdx].add(a, idx + 1);
             }

             boolean search(String a, int idx, int allowedChnages) {
                 if (a.length() == idx)
                     return isEOW;

                 boolean matchFound = false;
                 int nextIdx = a.charAt(idx) - 'a';
                 if (next[nextIdx] != null)
                     matchFound = next[nextIdx].search(a, idx + 1, allowedChnages);

                 if (!matchFound && allowedChnages > 0)
                     for (int i = 0; i < 26; ++i)
                         if (next[i] != null && next[i].search(a, idx + 1, allowedChnages - 1))
                             return true;

                 return matchFound;
             }
         }
    }

    class Solution2 {
        public List<String> twoEditWords(String[] Q, String[] D) {
            ArrayList<String> result = new ArrayList<>();

            for (String q : Q)
                for (String d : D)
                    if (diffAtMostTwo(q, d)) {
                        result.add(q);
                        break;
                    }

            return result;
        }

        private boolean diffAtMostTwo(String a, String b) {
            int al = a.length();
            int allowedDiff = 2;
            for (int i = 0; i < al; ++i)
                if (a.charAt(i) != b.charAt(i)) {
                    if (allowedDiff > 0)
                        --allowedDiff;
                    else
                        return false;
                }

            return true;
        }
    }

    void main() {
        System.out.println(new WordsWithinTwoEditsOfDictionary().new Solution2().twoEditWords(
                new String[] { "word","note","ants","wood" },
                new String[] { "wood","joke","moat" }));
        System.out.println(new WordsWithinTwoEditsOfDictionary().new Solution2().twoEditWords(
                new String[] { "prfturjd","iarapqqk","aokbrtmx","yafmjorj","larakqqk","nliynmpm","isikkcws","laraeqqk" },
                new String[] { "apahhijt","larapqqk","isukkcws","siqqoacj","nloynmpm" }));
        System.out.println(new WordsWithinTwoEditsOfDictionary().new Solution2().twoEditWords(
                new String[] { "vnxzewdxqkejpjqmcqcxdpiphtixnq","zgyysamtjdyetonyjqxkldfxzahvem","migncyjjgfeaxksdbhwudtmcozkcxh","mwdditcnrhptxnpjpcvhzahldxhxvq","gwdditcnrhptxnkjpcvhzahldxhxvq","nlmrnkbtksebpqguuwwaulfqxonyza","amghsvretolaqezqpcmowbxousddkx","dqofevfxfgeitqktokcpcbucycmdzg","gksdcrxozufhwxslhngfjaoukdpuup","djyvlijtxlklsomfhxriweogeurtol","wkkuqtlivotauxazuytwvsstecnxzy","gwopdpfpwfgbbvheimnkjvphacupcl","gwopdpypwfgbbvheimnkjvpfacupcl" },
                new String[] { "rclkryumahxdvmthjtfumbtdffdhex","awuoolpalprslknaokjsgwvkzosrlf","vnxzewdxqkejpnqmcqcxdpiphtixnq","mivbfynmqojkngecwbecfyfbnoasjq","oqoluruumnvxgzvkgyvcqwrgfizyuc","iybmqepdlzjyvhxibjjqyuuguqitnk","cwoueckhajuimdejoqgbzvyvnlxhdu","cueddmcnwbpmzbkzusoulwjvthiyzm","gtfngdznwhtalclfsjbqoodapacwwo","fiybkesvttkfjzkcpswnerbukhkstk","ptuqretyumrevshcyunzzfqiaczkdr","sxyldfjvmgiarluxzvvxqwrfryufjm","upyiookoukvzqmjwalggvabnfkkdnu","mwtrkaufowwlndmqjnnsuvnyeaqeik","tmdzjwggonextqrpeqjalfwdqiktdj","frqzaokqjhrjpibdoxqcophsfrnmpt","tyzngxzpkawyxhkuciicbvyerhfyuv","cjbhatbozngvvrojvxipcmazupuael","creoivkgekcjpynirnlmoqwqytkeke","idvhwbkvlpfrckmxpdgzyzhjluiqly","egbiiwpcbtopmbdavhzwqazexdpnak","kqiqolvwldchudwijmcolyuzpjupcf","amgvpglzvbxaprgobnjbtiyvccnfww","imdvsrtwqoyakdqwpqwapaeaxypfxu","gwopdpypwfgbbvheimnkjvphacupcl","vemcsdhmqluiysfxbqxhqzwcryqfem","xsebefnkjbixuinwwsgcngqtlvyfns","qvpjxftrdfzcchkoqmfxyilcdbgzid","nvxwsuftxikvrnvhnpwonjrupbudbp","ufjwjleazdstoiliddexgmrirbydis","pxquzszkwjvqqfxomwgvbopvpxyiax","ethutzvzgrdtiiygztzcnnxgbjjmfa","dpjvauxkbbdscjwhvvypcjfvuwuvqk","rldkfttsjnbbfpduojbaclmiksxord","mhneabjsxzgagglqpnsujfjulqsuej","omaywmdvbmskicisvxlcapsfyydbvj","ivlqbysqvtvllrrwobadlmyuakxhme","axapcmzcvbfboyllnouxeebrmmhsyb","izhktkjmnzzajndkguxtfulripivyo","gwdditcnrhptxnpjpcvhzahldxhxvq","gxwuvoglqoubsshaaikpinkjborckj" }));
    }
}
