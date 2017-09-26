package lab07;

import java.util.Stack;
import java.lang.*;

public class Postfixer {
	
	public static void main(String[] args) {
		if (InfixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> your answer should have been 12");
        
        if (InfixEvaluator("10 - 2") != 8)
            System.err.println("test1 failed --> your answer should have been 12");
        
        if (InfixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test2 failed --> your answer should have been 212");
        
        if (InfixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test3 failed --> your answer should have been 1400");
        
        if (InfixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test4 failed --> your answer should have been 100");
        
        System.out.println("Testing Done!!!");
        
	       if (!PostfixConvertor(new String("(4+5)")).equals("45+"))
	            System.err.println("test1 failed --> should have been 45+");
	        
	        if (!PostfixConvertor(new String("((4+5)*6)")).equals("45+6*"))
	            System.err.println("test2 failed --> should have been 45+6*");
	        
	        if (!PostfixConvertor(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
	            System.err.println("test3 failed --> should have been 456*7/+8-");
	        
	        if (!PostfixConvertor(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
	            System.err.println("test4 failed --> should have been 4567-*+8/");
	        
	        if (!PostfixConvertor(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
	            System.err.println("test5 failed --> should have been 987*654^/3*-2*+");
	        
	        System.out.println("Testing Done!!!");
	}
	
	public static double InfixEvaluator(String line) {
		
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();
		double num = 0.0;
		String theone = "";
		String OPERATORS = "()+-*/^";
		int[] precedence = {-1, -1, 1, 1, 2, 2, 3};
		while(data.hasNext()) {
			
			if(data.peek().compareTo("(") != 0 && data.peek().compareTo(")") != 0) {
				if(data.peek().compareTo("+") !=0 && data.peek().compareTo("-") != 0 && data.peek().compareTo("/") != 0
						&& data.peek().compareTo("^") != 0 && data.peek().compareTo("*") != 0) {
					theone = data.next();
					num = Double.parseDouble(theone);
					operands.push(num);
					
				}
			}
			if(data.hasNext()) {
			if(data.peek().compareTo("(")==0) {
				operators.push(data.next());
			}
			if(data.peek().compareTo(")")==0) {
				if(operators.isEmpty()) {
					operators.push(data.next());
					operators.pop();
				}
				if(!operators.isEmpty()) {
				if(operators.peek().compareTo("(") != 0) {
					String anotherone = operators.pop();
					double n1 = operands.pop();
					double n2 = operands.pop();
					double theRes = 0.0;
					if(anotherone.compareTo("*") == 0) {
						theRes = n2 * n1;
					}
					if(anotherone.compareTo("/") == 0) {
						theRes = n2 / n1;
					}
					if(anotherone.compareTo("-") == 0) {
						theRes = n2 - n1;
					}
					if(anotherone.compareTo("+") == 0) {
						theRes = n2 + n1;
					}
					if(anotherone.compareTo("^") == 0) {
						theRes = Math.pow(n2,n1);
					}
					operands.push(theRes);
				}
				else {
					operators.pop();
				}
				}
			}
			if(data.hasNext()) {
			if(data.peek().compareTo("+")==0 || data.peek().compareTo("-")==0 || data.peek().compareTo("/")==0
						|| data.peek().compareTo("^")==0 || data.peek().compareTo("*")==0) {
				
				if(!operators.isEmpty()) {
					if(precedence[OPERATORS.indexOf(operators.peek())] >= 
							precedence[OPERATORS.indexOf(data.peek())]) {
						String anotherone = operators.pop();
						double n1 = operands.pop();
						double n2 = operands.pop();
						double theRes = 0.0;
						if(anotherone.compareTo("*") == 0) {
							theRes = n2 * n1;
						}
						if(anotherone.compareTo("/") == 0) {
							theRes = n2 / n1;
						}
						if(anotherone.compareTo("-") == 0) {
							theRes = n2 - n1;
						}
						if(anotherone.compareTo("+") == 0) {
							theRes = n2 + n1;
						}
						if(anotherone.compareTo("^") == 0) {
							theRes = Math.pow(n2,n1);
						}
						operands.push(theRes);
						operators.push(data.next());	
					}
					else 
					{
						operators.push(data.next()); //ask if i should use peek or next
					}
			
					}	
				else{
					operators.push(data.next());
					}
		}
			}
	}
}
			
		while(!operators.isEmpty()) {
			String anotherone = operators.pop();
			double n1 = operands.pop();
			double n2 = operands.pop();
			double theRes = 0.0;	
			if(anotherone.compareTo("*") == 0) {
				theRes = n2 * n1;
			}
			if(anotherone.compareTo("/") == 0) {
				theRes = n2 / n1;
			}
			if(anotherone.compareTo("-") == 0) {
				theRes = n2 - n1;
			}
			if(anotherone.compareTo("+") == 0) {
				theRes = n2 + n1;
			}
			if(anotherone.compareTo("^") == 0) {
				theRes = Math.pow(n2,n1);
			}
			operands.push(theRes);
		}
		double finalresult = operands.pop();
		return finalresult;
		
	}
	
	
	public static String PostfixConvertor(String line) {
		
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		String postfix = "";
		String OPERATORS = "()+-*/^";
		int[] precedence = {-1, -1, 1, 1, 2, 2, 3};
		
		while(data.hasNext()) {
			
			if(data.peek().compareTo("(") != 0 && data.peek().compareTo(")") != 0) {
				if(data.peek().compareTo("+") !=0 && data.peek().compareTo("-") != 0 && data.peek().compareTo("/") != 0
						&& data.peek().compareTo("^") != 0 && data.peek().compareTo("*") != 0) {
					postfix += data.next();
				}
			}
			
			if(data.hasNext()) {
			if(data.peek().compareTo("+")==0 || data.peek().compareTo("-")==0 || data.peek().compareTo("/")==0
						|| data.peek().compareTo("^")==0 || data.peek().compareTo("*")==0) {
				
				if(!operators.isEmpty()) {
				if(precedence[OPERATORS.indexOf(operators.peek())] >= 
							precedence[OPERATORS.indexOf(data.peek())]) {
					String two = operators.pop();
					postfix += two;
				}
				else {
					operators.push(data.next());
				}
				}
				else {
					operators.push(data.next());
				}
			}
			
			else if(data.peek().compareTo("(") == 0) {
				operators.push(data.next());
			}
			
			else if(data.peek().compareTo(")") == 0) {
				if(operators.peek().compareTo("(") != 0) {
					String one = operators.pop();
					postfix += one;
				}
				else if(operators.peek().compareTo("(") == 0) {
					operators.pop();
					data.next();
				}
			}
		    }
		}
	return postfix;
  }
}
