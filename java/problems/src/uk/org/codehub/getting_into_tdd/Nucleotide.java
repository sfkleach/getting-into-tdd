package uk.org.codehub.getting_into_tdd;

import java.util.Map;

/*
Background
----------
DNA is made up of two strands that are sequences of 4 types of bases,
each of which is assigned a single character code.

    A = Adenine
    C = Cytosine
    G = Guanine
    T = Thymine

DNA sequences can be represented as sequences of these 4 letters (n.b. this is
an oversimplification). These represent the bases along just one strand as the
other can be easily inferred from the fact that the bases on the two strands
pair up with A<->T and C<->G.)

Our understanding of what is in a DNA sequence is built-up incrementally
by looking at repeated internal sequences. This is built up from the statistics
and indexes of short repeats.

The Problem
-----------
Our simple problem is, given a string that represents a DNA sequence, calculate
a table of how many times each sub-sequence of length-N nucleotides occurs in it.
For example, the table for the sub-sequences of length 2 in the tiny DNA
sequence 'aacact' would be:

    { "aa":1, "ac":2, "ca":1, "ct":1 }
*/


class Nucleotide {

	/**
	 * This is an example string for test convenience.
	 * 		From https://benchmarksgame-team.pages.debian.net/benchmarksgame/download/knucleotide-input.txt
	 * 		THREE Homo sapiens frequency, extracted for workshop to duck i/o issue.
	 */
	static String DNA_Sequence = 
			"aacacttcaccaggtatcgtgaaggctcaagattacccagagaacctttgcaatataaga" + 
			"atatgtatgcagcattaccctaagtaattatattctttttctgactcaaagtgacaagcc" + 
			"ctagtgtatattaaatcggtatatttgggaaattcctcaaactatcctaatcaggtagcc" + 
			"atgaaagtgatcaaaaaagttcgtacttataccatacatgaattctggccaagtaaaaaa" + 
			"tagattgcgcaaaattcgtaccttaagtctctcgccaagatattaggatcctattactca" + 
			"tatcgtgtttttctttattgccgccatccccggagtatctcacccatccttctcttaaag" + 
			"gcctaatattacctatgcaaataaacatatattgttgaaaattgagaacctgatcgtgat" + 
			"tcttatgtgtaccatatgtatagtaatcacgcgactatatagtgctttagtatcgcccgt" + 
			"gggtgagtgaatattctgggctagcgtgagatagtttcttgtcctaatatttttcagatc" + 
			"gaatagcttctatttttgtgtttattgacatatgtcgaaactccttactcagtgaaagtc" + 
			"atgaccagatccacgaacaatcttcggaatcagtctcgttttacggcggaatcttgagtc" + 
			"taacttatatcccgtcgcttactttctaacaccccttatgtatttttaaaattacgttta" + 
			"ttcgaacgtacttggcggaagcgttattttttgaagtaagttacattgggcagactcttg" + 
			"acattttcgatacgactttctttcatccatcacaggactcgttcgtattgatatcagaag" + 
			"ctcgtgatgattagttgtcttctttaccaatactttgaggcctattctgcgaaatttttg" + 
			"ttgccctgcgaacttcacataccaaggaacacctcgcaacatgccttcatatccatcgtt" + 
			"cattgtaattcttacacaatgaatcctaagtaattacatccctgcgtaaaagatggtagg" + 
			"ggcactgaggatatattaccaagcatttagttatgagtaatcagcaatgtttcttgtatt" + 
			"aagttctctaaaatagttacatcgtaatgttatctcgggttccgcgaataaacgagatag" + 
			"attcattatatatggccctaagcaaaaacctcctcgtattctgttggtaattagaatcac" + 
			"acaatacgggttgagatattaattatttgtagtacgaagagatataaaaagatgaacaat" + 
			"tactcaagtcaagatgtatacgggatttataataaaaatcgggtagagatctgctttgca" + 
			"attcagacgtgccactaaatcgtaatatgtcgcgttacatcagaaagggtaactattatt" + 
			"aattaataaagggcttaatcactacatattagatcttatccgatagtcttatctattcgt" + 
			"tgtatttttaagcggttctaattcagtcattatatcagtgctccgagttctttattattg" + 
			"ttttaaggatgacaaaatgcctcttgttataacgctgggagaagcagactaagagtcgga" + 
			"gcagttggtagaatgaggctgcaaaagacggtctcgacgaatggacagactttactaaac" + 
			"caatgaaagacagaagtagagcaaagtctgaagtggtatcagcttaattatgacaaccct" + 
			"taatacttccctttcgccgaatactggcgtggaaaggttttaaaagtcgaagtagttaga" + 
			"ggcatctctcgctcataaataggtagactactcgcaatccaatgtgactatgtaatactg" + 
			"ggaacatcagtccgcgatgcagcgtgtttatcaaccgtccccactcgcctggggagacat" + 
			"gagaccacccccgtggggattattagtccgcagtaatcgactcttgacaatccttttcga" + 
			"ttatgtcatagcaatttacgacagttcagcgaagtgactactcggcgaaatggtattact" + 
			"aaagcattcgaacccacatgaatgtgattcttggcaatttctaatccactaaagcttttc" + 
			"cgttgaatctggttgtagatatttatataagttcactaattaagatcacggtagtatatt" + 
			"gatagtgatgtctttgcaagaggttggccgaggaatttacggattctctattgatacaat" + 
			"ttgtctggcttataactcttaaggctgaaccaggcgtttttagacgacttgatcagctgt" + 
			"tagaatggtttggactccctctttcatgtcagtaacatttcagccgttattgttacgata" + 
			"tgcttgaacaatattgatctaccacacacccatagtatattttataggtcatgctgttac" + 
			"ctacgagcatggtattccacttcccattcaatgagtattcaacatcactagcctcagaga" + 
			"tgatgacccacctctaataacgtcacgttgcggccatgtgaaacctgaacttgagtagac" + 
			"gatatcaagcgctttaaattgcatataacatttgagggtaaagctaagcggatgctttat" + 
			"ataatcaatactcaataataagatttgattgcattttagagttatgacacgacatagttc" + 
			"actaacgagttactattcccagatctagactgaagtactgatcgagacgatccttacgtc" + 
			"gatgatcgttagttatcgacttaggtcgggtctctagcggtattggtacttaaccggaca" + 
			"ctatactaataacccatgatcaaagcataacagaatacagacgataatttcgccaacata" + 
			"tatgtacagaccccaagcatgagaagctcattgaaagctatcattgaagtcccgctcaca" + 
			"atgtgtcttttccagacggtttaactggttcccgggagtcctggagtttcgacttacata" + 
			"aatggaaacaatgtattttgctaatttatctatagcgtcatttggaccaatacagaatat" + 
			"tatgttgcctagtaatccactataacccgcaagtgctgatagaaaatttttagacgattt" + 
			"ataaatgccccaagtatccctcccgtgaatcctccgttatactaattagtattcgttcat" + 
			"acgtataccgcgcatatatgaacatttggcgataaggcgcgtgaattgttacgtgacaga" + 
			"gatagcagtttcttgtgatatggttaacagacgtacatgaagggaaactttatatctata" + 
			"gtgatgcttccgtagaaataccgccactggtctgccaatgatgaagtatgtagctttagg" + 
			"tttgtactatgaggctttcgtttgtttgcagagtataacagttgcgagtgaaaaaccgac" + 
			"gaatttatactaatacgctttcactattggctacaaaatagggaagagtttcaatcatga" + 
			"gagggagtatatggatgctttgtagctaaaggtagaacgtatgtatatgctgccgttcat" + 
			"tcttgaaagatacataagcgataagttacgacaattataagcaacatccctaccttcgta" + 
			"acgatttcactgttactgcgcttgaaatacactatggggctattggcggagagaagcaga" + 
			"tcgcgccgagcatatacgagacctataatgttgatgatagagaaggcgtctgaattgata" + 
			"catcgaagtacactttctttcgtagtatctctcgtcctctttctatctccggacacaaga" + 
			"attaagttatatatatagagtcttaccaatcatgttgaatcctgattctcagagttcttt" + 
			"ggcgggccttgtgatgactgagaaacaatgcaatattgctccaaatttcctaagcaaatt" + 
			"ctcggttatgttatgttatcagcaaagcgttacgttatgttatttaaatctggaatgacg" + 
			"gagcgaagttcttatgtcggtgtgggaataattcttttgaagacagcactccttaaataa" + 
			"tatcgctccgtgtttgtatttatcgaatgggtctgtaaccttgcacaagcaaatcggtgg" + 
			"tgtatatatcggataacaattaatacgatgttcatagtgacagtatactgatcgagtcct" + 
			"ctaaagtcaattacctcacttaacaatctcattgatgttgtgtcattcccggtatcgccc" + 
			"gtagtatgtgctctgattgaccgagtgtgaaccaaggaacatctactaatgcctttgtta" + 
			"ggtaagatctctctgaattccttcgtgccaacttaaaacattatcaaaatttcttctact" + 
			"tggattaactacttttacgagcatggcaaattcccctgtggaagacggttcattattatc" + 
			"ggaaaccttatagaaattgcgtgttgactgaaattagatttttattgtaagagttgcatc" + 
			"tttgcgattcctctggtctagcttccaatgaacagtcctcccttctattcgacatcgggt" + 
			"ccttcgtacatgtctttgcgatgtaataattaggttcggagtgtggccttaatgggtgca" + 
			"actaggaatacaacgcaaatttgctgacatgatagcaaatcggtatgccggcaccaaaac" + 
			"gtgctccttgcttagcttgtgaatgagactcagtagttaaataaatccatatctgcaatc" + 
			"gattccacaggtattgtccactatctttgaactactctaagagatacaagcttagctgag" + 
			"accgaggtgtatatgactacgctgatatctgtaaggtaccaatgcaggcaaagtatgcga" + 
			"gaagctaataccggctgtttccagctttataagattaaaatttggctgtcctggcggcct" + 
			"cagaattgttctatcgtaatcagttggttcattaattagctaagtacgaggtacaactta" + 
			"tctgtcccagaacagctccacaagtttttttacagccgaaacccctgtgtgaatcttaat" + 
			"atccaagcgcgttatctgattagagtttacaactcagtattttatcagtacgttttgttt" + 
			"ccaacattacccggtatgacaaaatgacgccacgtgtcgaataatggtctgaccaatgta" + 
			"ggaagtgaaaagataaatat";


	/**
	 * Compute the frequency distribution of subsequences of a given length in a
	 * stretch of DNA.
	 * @param dna The DNA sequence (String) we are interested in.
	 * @param length The length of the subsequences to generate.
	 * @return A map from subsequence to count (i.e. a bag) 
	 */
	public Map< String, Integer > tableOfSubsequences( String dna, int length ) {
		return null;
	}

}