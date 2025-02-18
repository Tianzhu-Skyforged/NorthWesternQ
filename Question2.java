import java.util.*;

public class Question2 {
    public static void main(String[] args) {
        // Using Scanner for inputs
        Scanner scan = new Scanner(System.in);


        /*  Sample input : 3 6
            amelia bubba
            kiryu coco
            ollie udin
            PBBPFP          */

        //Step 1: Getting all the inputs
        int N = scan.nextInt();
        int Q = scan.nextInt();

        //Using LinkedList and HashMap to Store the line and couples

        List<String> congaline = new LinkedList<>();
        Map<String,String> couple = new HashMap<>();

        //Adding names to data structures through scanner
        for (int i = 0 ; i < N ; i++){
            String person1 = scan.next();
            String person2 = scan.next();
            congaline.add(person1);
            congaline.add(person2);
            couple.put(person1,person2);
            couple.put(person2,person1);
        }

        //instruction input(BFRCP)
        String instruction = scan.next();

        scan.close();

        //Step 2: Handel all operations(BFRCP)

        //Store Yelled names for output
        List<String> yell = new ArrayList<>();
        //Initialize mic position
        int mic = 0;

        for(char command : instruction.toCharArray()){
            switch (command){
                case 'F':
                    //Pass mic forward
                    mic--;
                    break;
                case 'B':
                    //Pass mic backward
                    mic++;
                    break;
                case 'R':
                    //pass the mic, then insert in the back, if it is at last, move to the front
                    String micHolder = congaline.get(mic);
                    if(mic >= congaline.size()-1){
                        mic = 0;
                    }
                    congaline.remove(micHolder);
                    congaline.add(micHolder);
                    break;
                case 'C':
                    // pass the mic, then get partner and his/her location, remove the micholder and insert after partner
                    micHolder = congaline.get(mic);
                    if(mic >= congaline.size()-1){
                        mic = 0;
                    }

                    String partner = couple.get(micHolder);
                    int partnerIndex = congaline.indexOf(partner);
                    congaline.remove(micHolder);
                    congaline.add(partnerIndex+1,micHolder);
                    //If the micHolder gets inserted to the front of the mic, the mic position should increase by one
                    //Otherwise the mic postion is not changed
                    if(partnerIndex < mic){
                        ++mic;
                    }
                    break;
                case 'P':
                    //find micHolder, get partner and add it to yell list
                    micHolder = congaline.get(mic);
                    partner = couple.get(micHolder);
                    yell.add(partner);
                    break;
            }
        }

        //Step 3 : Output
        for(String yelled : yell){
            System.out.println(yelled);
        }

        System.out.println();

        for(String names : congaline){
            System.out.println(names);
        }

    }
}
