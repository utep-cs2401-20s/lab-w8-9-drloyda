import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AminoAcidLLTester extends AminoAcidLL{
    @Test
    public void AminoAcidLL(){
        String codon = "GCC";
        char expect = 'A';

        AminoAcidLL head = new AminoAcidLL(codon);
        assertEquals(expect, AminoAcidResources.getAminoAcidFromCodon(codon));
    }
    @Test
    public void AminoAcidLL2(){
        String sequence = "UCGUGCGAG";
        AminoAcidLL test = createFromRNASequence(sequence);
        sort(test);
        System.out.println();
        System.out.println(test.isSorted());


//        System.out.println(test.aminoAcidList());
//        for (int i = 0; i < test.aminoAcidCounts().length; i++) {
//            System.out.print(test.aminoAcidCounts()[i]);
//        }




        //assertEquals(result,AminoAcidLL.createFromRNASequence(sequence));

    }


}
