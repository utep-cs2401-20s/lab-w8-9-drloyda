import java.util.Arrays;

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
    counts = new int [codons.length];
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
       incrCodon(inCodon);
    }
    else if(this.next != null){
      this.next.addCodon(inCodon);
    }else{
      this.next = new AminoAcidLL(inCodon);
      next.incrCodon(inCodon);
    }
  }
  /********************************************************************************************/

  public int[] incrCodon(String inCodon){

    for(int i = 0; i < this.codons.length; i ++){
      if(inCodon.equals(this.codons[i])){
        counts[i]++;
      }
    }
    return counts;
  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){

    int total = 0;
    for(int i = 0; i < this.counts.length;i++){
      total += this.counts[i];
    }
    return total;
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
    int comp = 0;
    if(inList == null){
      comp += this.totalCount();
    }
    if(this == null){
      comp += this.aminoAcidCompare(inList.next);
    }
    if(this == inList){
      next.aminoAcidCompare(inList);
      next.aminoAcidCompare(inList.next);

    }else{
      next.aminoAcidCompare(inList);
      aminoAcidCompare(inList.next);
    }

    return comp;
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
    if(next == null){
      return new char [] {this.aminoAcid};
    }
    char [] a = next.aminoAcidList();
    char [] n = new char [a.length+1];

    n[0] = this.aminoAcid;

    for (int i = 1; i < n.length ; i++) {
      n[i] = a[i-1];
    }


    return n;
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    if(next == null){
      return new int [] {this.totalCount()};
    }
    int [] a = next.aminoAcidCounts();
    int [] n = new int [a.length+1];

    n[0] = this.totalCount();

    for (int i = 1; i < n.length ; i++) {
      n[i] = a[i-1];
    }


    return n;
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){


    if(next != null && this.aminoAcid < next.aminoAcid){
      return next.isSorted();
    }
    if(next == null){
      return true;
    }
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
    iter  = head;
//    while (iter!=null){
//      System.out.print(iter.aminoAcid);
//      iter = iter.next;
//    }
    System.out.println(iter.aminoAcid);
    return iter;
  }

  /*********************************************************************************************/

  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){
    if(inList.isSorted()){
      return inList;
    }
    char[] acids = inList.aminoAcidList();
    AminoAcidLL i;
    AminoAcidLL k;

    for(i = inList; i.next != null; i = i.next){
      for(k = i.next; k != null; k = k.next){
        if(i.aminoAcid > k.aminoAcid){
          char temp = i.aminoAcid;
          i.aminoAcid = k.aminoAcid;
          k.aminoAcid = temp;
        }
      }
    }
    System.out.println(inList.aminoAcid);
//    while(inList != null){
//      System.out.print(inList.aminoAcid);
//      inList= inList.next;
//    }
    return inList;
  }
}