class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){

  }

  public static void main(String[] args) {


  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int [4];
    this.next = null;



  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){

    if(AminoAcidResources.getAminoAcidFromCodon(inCodon) == this.aminoAcid){
       incrCodon(inCodon, this.codons);
    }
    else if(this.next != null){
      this.next.addCodon(inCodon);
    }else{
      this.next = new AminoAcidLL(inCodon);
    }
  }
  /********************************************************************************************/

  public int[] incrCodon(String inCodon, String[] codonList){
    for(int i = 0; i < codonList.length; i ++){
      if(inCodon.equals(codonList[i])){
        counts[i]++;
      }
    }
    return counts;
  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    return 0;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    return 0;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    return new char[]{};
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    return new int[]{};
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    String firstCodon = inSequence.substring(0,3);
    AminoAcidLL head = new AminoAcidLL(firstCodon);
    AminoAcidLL iter = head;
    String codon = "";

    for(int i = 0; i < inSequence.length() /3; i++) {
      codon = inSequence.substring(3 * i, ((3 * i) + 3));
      iter.addCodon(codon);
    }

    while(iter!= null){
      System.out.print(iter.aminoAcid);
      iter =iter.next;
    }
    iter = head;
    System.out.println();
    for (int i = 0; i <iter.codons.length ; i++) {
      System.out.print(iter.codons[i] + ",");
    }
    System.out.println();
    for (int i = 0; i <iter.counts.length ; i++) {
      System.out.print(" " + iter.counts[i] + " ");
    }

    return iter;
  }
  /*********************************************************************************************/
//  public static void print(AminoAcidLL head){
//    AminoAcidLL iter = new AminoAcidLL();
//    iter = head;
//    while(iter != null){
//      System.out.println(AminoAcidResources.getAminoAcidFromCodon());
//      iter = iter.next;
//    }
//  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){
    return null;
  }
}