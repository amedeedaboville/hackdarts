import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class AcadoxProblem {
	public static void main(String[] args){
        	boolean printAnswer = true;
		String input = "";
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
			input = br.readLine();
		}
		catch(Exception e){
			
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		String[] arr = input.split(" ");
		int answer = 0;
		for (int i = 0; i<arr.length; i++){
			switch(arr[i]){
				case "+" : 
					if (queue.size()<2){
						System.out.println("ERROR");
                        			printAnswer = false;
					}
					else{
						while(!queue.isEmpty()){
							answer = queue.poll() + queue.poll();
						}
						queue.add(answer);
					}
					break;
				case "-" :
					if (queue.size()<2){
						System.out.println("ERROR");
                        			printAnswer = false;
					}
					else{
						while(!queue.isEmpty()){
							answer = queue.poll() - queue.poll();
						}
						queue.add(answer);
					}
					break;
				case "&" :
					queue.add(queue.poll() & queue.poll());
					break;
				case "X" :
					queue.add(queue.poll() ^ queue.poll());
					break;
				case "|" :
					queue.add(queue.poll() | queue.poll());
					break;
				case "~" : 
					queue.add(~queue.poll());
					break;
				default: 
					queue.add(Integer.parseInt(arr[i], 16));
					break;
			}
		}
		answer = queue.poll();
		if (answer<0){
			answer=0;
		}
		if (answer>65535){
			answer = 65535;
		}
        if (printAnswer){
            System.out.println(String.format(("%04X"), answer));
        }
	}
}

