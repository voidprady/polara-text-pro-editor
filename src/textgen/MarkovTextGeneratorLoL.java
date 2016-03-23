package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] words = sourceText.split("[ ]+");
		
		if(sourceText.equals(" ")){
			throw new IndexOutOfBoundsException("Check Bounds");
		}
		else if(starter == "")
			starter = words[0];
		
		ListNode prevWord = new ListNode(starter);
	
		for(int i=1; i<words.length; i++){
			int flag=0;
			for(ListNode E : wordList){
				if(E.getWord().equals(prevWord.getWord())){
					E.addNextWord(words[i]);
					flag=1;
					break;
				}
			}
			if(flag==0){
				prevWord.addNextWord(words[i]);
				wordList.add(prevWord);
			}
			
			prevWord = new ListNode(words[i]);
			
			if(i==words.length-1){
				prevWord.addNextWord(starter);
				wordList.add(prevWord);
			}
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if(numWords == 0){
			return "";
		}
		
		String currWord = starter;
		String output = "";
		int wordsAdded = 1;
		output = output.concat(currWord)+" ";
		
		if(output.equals(" ")){
			return ""; 
		}
		else{
			while(numWords > wordsAdded)
			{
				for(ListNode F : wordList){
					if(F.getWord().equals(currWord)){
						String nextWord = F.getRandomNextWord(rnGenerator);
						output = output.concat(nextWord)+" ";
						currWord = new String(nextWord);
						wordsAdded++;
						break;
					}
				}
			}
			return output;
		}
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		this.wordList = new LinkedList<ListNode>();
		this.starter = "";
		this.train(sourceText);
	}
	
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
//		 feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello. Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
//		System.out.println(gen.generateText(20));
		String textString2 = 
				"You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say Hello, hello, hello. "+
				"I don't know why you say goodbye, I say Hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.train("");
		System.out.println(gen);
		System.out.println(gen.generateText(100));
     }

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int randomIndex = generator.nextInt(nextWords.size());
	    return nextWords.get(randomIndex);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


