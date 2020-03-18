import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AminoAcidLLTester {
    @Test
    public void AminoAcidLL(){
        String codon = "GCC";
        char expect = 'A';

        AminoAcidLL head = new AminoAcidLL(codon);
        assertEquals(expect, AminoAcidResources.getAminoAcidFromCodon(codon));
    }
    @Test
    public void AminoAcidLL2(){
        String sequence = "GCUGCCACGGAGCUUCGGAGCGAG";
        AminoAcidLL test = new AminoAcidLL();

        AminoAcidLL.createFromRNASequence(sequence);

        //assertEquals(result,AminoAcidLL.createFromRNASequence(sequence));

    }


}
