package guessme;
// 5463
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static ArrayList<String> possib = new ArrayList<String>();
    public static void main(String args[]) {
        init();
        int steps = 0;
        System.out.println("Bulls and cows là một trò chơi giải mã trong đó bạn nghĩ đến số có 4 chữ số và người khác cố gắng đoán nó");
        System.out.println("Bulls: Số đúng và đúng vị trí \nCows: Số đúng nhưng sai vị trí \n");
        System.out.println("=================================================================\n");
        System.out.println("Xin chào! Tôi là máy tính!\nBây giờ tôi sẽ nghĩ 1 con số...");
        System.out.println("---***---");
        Scanner in = new Scanner(System.in);
        while (possib.size() > 1) {
        	steps++;
            String guess = possib.get((int)(Math.random()*possib.size()));		// Random 1 số bất kì trong mảng possib
            System.out.print(guess+" \n");
            System.out.print("Đúng số - Đúng vị trí: ");
            int bulls = in.nextInt();
            System.out.print("Đúng số - Sai vị trí: ");
            int cows = in.nextInt();
            for (int j = 0; j < possib.size(); j++)
                if (!check(possib.get(j),guess,cows,bulls)) {
                    possib.remove(j);
                    j--;
                }
        }
        // Ví dụ:
        // guess = cows1 và bulls1
        // abcd = cows2 và bulls2
        // Nếu cows1=bulls1 và cows2=bulls2 => Giữ
        // Ngược lại cows1!=bulls1 hoặc cows2!=bulls2 => possib.remove(j); (Xóa cái số ở vị trí đó trong mảng)
        
        for (int j = 0; j < possib.size(); j++) {	
        	System.out.print("========================\n");
            System.out.println("Số bạn nghĩ là: " + possib.get(j));
            System.out.println("Tôi đã đoán với " + steps + " lần đoán!");
        }
        in.close();
    }
    
    //Số S có 4 chữ số abcd
    //Liệt kê số và thêm từng số a b c d vào Mảng possib (4 số a b c d đều khác nhau) 
    static void init() {
        for (int a = 1; a <= 6; a++) { 								
            for (int b = 0; b <= 6; b++) {							
                if (b == a) continue;								
                for (int c = 0; c <= 6; c++) {						
                    if (c == b || c == a) continue;					
                    for (int d = 0; d <= 6; d++) {					
                        if (d == a || d == b || d == c) continue;
                        String cnt = ""+a+b+c+d;
                        possib.add(cnt); //possible = Các số có thể xảy ra
                    }
                }
            }
        }
    }
    // guess = 4079 0 = 4 | 1 = 0 | 2 = 7 | 3 = 9
    // ans   = 1234 
    //Boolean = Yes or No
    static boolean check(String ans,String guess,int cows,int bulls) {
        for (int i = 0; i < guess.length(); i++) {
            int ind = ans.indexOf(guess.charAt(i));		// ans.indexOf(guess.charAt(i)): Xét kí tự ở vị trí thứ i của chuỗi guess, tìm vị trí của nó ở trong ans 
            if (ind == i) bulls--;
            else if (ind >= 0) cows--;
        }
        return (cows == 0) && (bulls == 0);
    }
}