package guessme;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
// 9632

public class BandC {
	 
    public static void main(String[] args) {

        Set<String> possibleNums = generatePossibleNums();
        int steps = 0;
        System.out.println("Bulls and cows là một trò chơi giải mã trong đó bạn nghĩ đến số có 4 chữ số và người khác cố gắng đoán nó");
        System.out.println("Bulls: Số đúng và đúng vị trí \nCows: Số đúng nhưng sai vị trí \n");
        System.out.println("=================================================================\n");
        System.out.println("Xin chào! Tôi là máy tính!\nBây giờ tôi sẽ nghĩ 1 con số...");
        Scanner reader = new Scanner(System.in);
        while (true) {
            steps++;
            Iterator<String> iter = possibleNums.iterator();
            String AIguess = iter.next();
            System.out.println("---***---");
            System.out.println("Số tôi đoán là: " + AIguess);
            System.out.print("Số số đúng và đúng vị trí(Bulls): ");
            int numberOfBulls = reader.nextInt();
            System.out.print("Số số đúng nhưng sai vị trí(Cows): ");
            int numberOfCows = reader.nextInt();
            removeWrongNums(new BcCount(numberOfBulls, numberOfCows), AIguess, possibleNums);
            if (numberOfBulls == 4) {
            	System.out.println("========================================");
            	System.out.println("Số bạn nghĩ là: " + AIguess);
                System.out.println("Tôi đã đoán với " + steps + " lần đoán!");
                break;
            }
        }
        reader.close();
    }

    public static BcCount calcBullandCowCount(String guess, String candidate) {
        BcCount bcCount = new BcCount(0, 0);
        for (int i = 0; i < candidate.length(); i++) {
            if (guess.charAt(i) == candidate.charAt(i)) {
                bcCount.bullCount++;
            } else if (guess.contains(String.valueOf(candidate.charAt(i)))) {
                bcCount.cowCount++;
            }
        }
        return bcCount;
    }

    public static Set<String> generatePossibleNums() {
        Set<String> set = new HashSet<String>();
        for (int i = 1023; i <= 9876; i++) {
            set.add(String.valueOf(i));
        }
        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            Set<Character> digits = new HashSet<>();
            for (char c : str.toCharArray()) {
                if (digits.contains(c)) {
                    iter.remove();
                    break;
                }
                digits.add(c);
            }
        }
        return set;
    }
    
    public static void removeWrongNums(BcCount guessBcCount, String guess,
            Set<String> possibleNums) {
        Iterator<String> iter = possibleNums.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            if (calcBullandCowCount(guess, str).equals(guessBcCount) == false) {
                iter.remove();
            }
        }
    }

}
