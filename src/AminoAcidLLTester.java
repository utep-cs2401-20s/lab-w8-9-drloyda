import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AminoAcidLLTester extends AminoAcidLL{

    //TEST CASE 1:
    @Test
    public void creatFromRNASequenceTest(){
        //inputting the sequence taken from the README
        String sequence = "GCUACGGAGCUUCGGAGCUAG";
        AminoAcidLL test = createFromRNASequence(sequence);
        //What i expect
        char [] result = {'A','T','E','L','R','S','*'};

        assertArrayEquals(result,test.aminoAcidList());
        //The test passed, meaning that it was able to successfully read the
        //codons from the sequence and turn them into AminoAcid nodes,
        //including the stop
    }
    /************************************************************************/
    //TEST CASE 2:
    @Test
    public void AminoAcidListTest(){
        //The list contains the AminoAcid nodes : S - C - E - V - P
        String sequence = "AGCUGCGAGGUGCCG";
        AminoAcidLL test = createFromRNASequence(sequence);
        //What i expect
        char [] result = {'S','C','E','V','P'};

        assertArrayEquals(result,test.aminoAcidList());
        //The test passed, meaning that the method was able to turn the aminoAcids
        //in the list into a corresponding char[]

    }
    /************************************************************************/
    //TEST CASE 3:
    @Test
    public void addCodonTest(){
        //The sequnece multiple V and D AminoAcids within it
        String sequence = "GUGGAGGACGUUGAUAGGGUC"; //{V,E,D,V,D,R,V
        AminoAcidLL test = createFromRNASequence(sequence);
        //What i expect
        char [] result = {'V','E','D'};

        assertArrayEquals(result,test.aminoAcidList());
        //The test passes, meaning that the method was able to
        //not create duplicate nodes and add to their counts
        // if the sequence had the same AminoAcids
    }

    /************************************************************************/
    //TEST CASE 4:
    @Test
    public void AminoAcidCountsTest(){
        //The sequence has two S AminoAcids, meaning the list should have two counts for it
        //and one count for the other aminoAcids
        String sequence = "AGCGAGUUGAGC"; //{S,E,L,S}
        AminoAcidLL test = createFromRNASequence(sequence);//{S,E,L}
        //what i expect
        int [] result = {2,1,1};

        assertArrayEquals(result,test.aminoAcidCounts());
        //the test passed, meaning that the method was able to
        // get the total counts from the amino acids and turn it
        //into a corresponding int[]
    }
    /************************************************************************/
    //TEST CASE 5:
    @Test
    public void sortTest(){
        // The list originally not sorted
        String sequence = "ACUCCAAUGGUG"; //{T,M,P,V}
        AminoAcidLL test = createFromRNASequence(sequence);
        //what i expect
        char [] result = {'M','P','T','V'};

        assertArrayEquals(result,sort(test).aminoAcidList());
        //the test passed and was able to sort the nodes by moving them
        //into their appropriate spots
    }
    /************************************************************************/
    //TEST CASE 6:
    @Test
    public void isSortedTest(){
        //The list is not sorted
        String sequence = "UGGCUGUUUCAA"; //{W,L,F.Q}
        AminoAcidLL test = createFromRNASequence(sequence);

        assertEquals(false, test.isSorted());
        //the test passed, meaning it was able to recursively traverse
        //the list and check if the nodes are in alphanumeric order
    }
    /************************************************************************/
    //TEST CASE 7:
    @Test
    public void isSortedTest2(){
        //The list is originally not sorted
        String sequence = "UGGCUGUUUCAA"; //{W,L,F.Q}
        AminoAcidLL test = createFromRNASequence(sequence);
        //I then sort the list
        sort(test);

        assertEquals(true, test.isSorted());
        //the test passed, meaning it was able to recursively traverse
        //the list and check if the nodes are in alphanumeric order
    }
    /************************************************************************/
    //TESTCASE 8;
    @Test
    public void totalCountTest(){
        //The sequence has three G aminoAcids, meaning three counts for it
        String sequence = "GGGGGAGGC"; //{G,G,G}
        AminoAcidLL test = createFromRNASequence(sequence);
        //what i expect
        int[] result = {3};

        assertArrayEquals(result, test.aminoAcidCounts());
        //the test passed, meaning it was able to add up all
        //the counts for the aminoAcid
    }
    /************************************************************************/
    //TESTCASE 9;
    @Test
    public void AminoAcidCompareTest(){
        //the This list that contains one extra G aminoAcid
        String sequence = "GGGGGAGAGGCUCGGA"; //{G,G,E,A,R,
        AminoAcidLL test = createFromRNASequence(sequence);
        sort(test);

        //the inList that will compare to the this list
        String sequence2 = "GGAGAGGCUCGG";//{G,E,A,R
        AminoAcidLL test2 = createFromRNASequence(sequence);
        sort(test2);

        assertEquals(1 ,test.aminoAcidCompare(test2));
        //The test passed, meaining that the method was able to compare
        //and subtract the aminoAcids of the two lists
    }
    /************************************************************************/
    //TESTCASE 9;
    @Test
    public void codonCCompareTest(){
        //incomplete
        //the This list that contains one extra G aminoAcid
        String sequence = "GGGGGAGAGGCUCGGA"; //{G,G,E,A,R,
        AminoAcidLL test = createFromRNASequence(sequence);
        sort(test);

        //the inList that will compare to the this list
        String sequence2 = "GGAGAGGCUCGG";//{G,E,A,R
        AminoAcidLL test2 = createFromRNASequence(sequence);
        sort(test2);

        assertEquals(1 ,test.codonCompare(test2));
        
    }


}
