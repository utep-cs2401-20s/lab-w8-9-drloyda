import java.util.Arrays;

class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){
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
    //checks if the aminoAcid of the codon is in the list
    if(AminoAcidResources.getAminoAcidFromCodon(inCodon) == this.aminoAcid){
       incrCodon(inCodon);
    }
    //if not then it moves onto the next node
    else if(this.next != null){
      this.next.addCodon(inCodon);
    }
    //if there is no next node, then it creates a new one
    else{
      this.next = new AminoAcidLL(inCodon);
      next.incrCodon(inCodon);
    }
  }
  /********************************************************************************************/

  public int[] incrCodon(String inCodon){

    //goes through the codon list of the aminoAcid and increments the count if at the same index of inCodon
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
    //adds all of the values of an aminoAcids count array
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

    int comp;
    if(inList.next == null){
      return totalCount();
    }
    if(next == null){
      return this.aminoAcidCompare(inList.next);
    }

    //if the nodes are matching
    if(this.aminoAcid == inList.aminoAcid){
      if(this.totalCount() < inList.totalCount()){
        next.aminoAcidCompare(inList.next);
        return comp = totalDiff(inList) *-1;

      }else {
        next.aminoAcidCompare(inList.next);
        return comp = totalDiff(inList.next);
      }
    }

    return 0;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    int comp;
    if(inList.next == null){
      return totalCount();
    }
    if(next == null){
      return this.codonCompare(inList.next);
    }

    if(this.aminoAcid == inList.aminoAcid){
      if(this.totalCount() < inList.totalCount()){
        next.codonCompare(inList.next);
        return comp = codonDiff(inList) *-1;

      }else {
        next.codonCompare(inList.next);
        return comp = codonDiff(inList.next);
      }
    }

    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){

    if(next == null){
      return new char [] {this.aminoAcid};
    }
    //creates initial array with the last aminoAcid of the list
    char [] a = next.aminoAcidList();
    //creates a new array that one more in length the ^^
    char [] n = new char [a.length+1];

    //assigns first element of the array as the previous element of the current one
    n[0] = this.aminoAcid;

    //populates the array
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
    //creates initial array with the last count of the list
    int [] a = next.aminoAcidCounts();
    //creates a new array that one more in length the ^^
    int [] n = new int [a.length+1];

    //assigns first element of the array as the previous element of the current one
    n[0] = this.totalCount();

    //populates the array
    for (int i = 1; i < n.length ; i++) {
      n[i] = a[i-1];
    }
    return n;
  }

  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    //traverses through the list if the next char is a greater value and not at the end of the list
    if(next != null && this.aminoAcid < next.aminoAcid){
      return next.isSorted();
    }
    //if at the end of the list, then it means its sorted
    if(next == null){
      return true;
    }
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    //creates the head, which is the first codon of sequence
    String firstCodon = inSequence.substring(0,3);
    AminoAcidLL head = new AminoAcidLL(firstCodon);
    //creates the iterator
    AminoAcidLL iter = head;
    String codon = "";

    //traverses the sequence by only looking at three letters each time, then creating a node from them
    for(int i = 0; i < inSequence.length() /3; i++) {
      codon = inSequence.substring(3 * i, ((3 * i) + 3));
      iter.addCodon(codon);
    }

    iter  = head;
    return iter;
  }

  /*********************************************************************************************/

  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){
    //if the list is already sorted, then no need to run method
    if(inList.isSorted()){
      return inList;
    }

    //traverses through the list by looking at current node and the next one
    for(AminoAcidLL i = inList; i.next != null; i = i.next){
      for(AminoAcidLL k = i.next; k != null; k = k.next){
        //if the current node is greater than the next, then it swaps places with the next one
        if(i.aminoAcid > k.aminoAcid){
          char temp = i.aminoAcid;
          i.aminoAcid = k.aminoAcid;
          k.aminoAcid = temp;
        }
      }
    }
    return inList;
  }
}