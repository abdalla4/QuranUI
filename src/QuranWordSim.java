public class QuranWordSim {
	private String SurahName1;
	private String Ayah1;
	private String SurahName2;
	private String Ayah2;
	private int NoOfMatchingWords;
	private int PercentageOfMatchingWordsInSentence1;
	private int PercentageOfMatchingWordsInSentence2;
	private int Connectedness1;
	private int Connectedness2;
	private int DegreeOfOrderness;
	
	public QuranWordSim(String SurahName1, String Ayah1, String SurahName2, String Ayah2, int NoOfMatchingWords, int PercentageOfMatchingWordsInSentence1, int PercentageOfMatchingWordsInSentence2, int Connectedness1, int Connectedness2, int DegreeOfOrderness){
		setSurahName1(SurahName1);
		setAyah1(Ayah1);
		setSurahName2(SurahName2);
		setAyah2(Ayah2);
		setNoOfMatchingWords(NoOfMatchingWords);
		setPercentageOfMatchingWordsInSentence1(PercentageOfMatchingWordsInSentence1);
		setPercentageOfMatchingWordsInSentence2(PercentageOfMatchingWordsInSentence2);
		setConnectedness1(Connectedness1);
		setConnectedness2(Connectedness2);
		setDegreeOfOrderness(DegreeOfOrderness);
	}
	
	@Override
	public String toString(){
		return "QuranWordSim [SurahName1=" + SurahName1 + ", Ayah1=" + Ayah1 + ", SurahName2="
				+ SurahName2 + ", Ayah2=" + Ayah2 + ", NoOfMatchingWords=" + NoOfMatchingWords + ", PercentageOfMatchingWordsInSentence1=" + PercentageOfMatchingWordsInSentence1+ ", PercentageOfMatchingWordsInSentence2=" + PercentageOfMatchingWordsInSentence2 + "]";
	}

	public String getSurahName1() {
		return SurahName1;
	}

	public void setSurahName1(String surahName1) {
		if (surahName1 == null || surahName1.length() == 0 )
			throw new IllegalArgumentException();
		this.SurahName1 = surahName1;
	}

	public String getAyah1() {
		return Ayah1;
	}

	public void setAyah1(String ayah1) {
		if (ayah1 == null || ayah1.length() == 0 )
			throw new IllegalArgumentException();
		this.Ayah1 = ayah1;
	}

	public String getSurahName2() {
		return SurahName2;
	}

	public void setSurahName2(String surahName2) {
		if (surahName2 == null || surahName2.length() == 0 )
			throw new IllegalArgumentException();
		this.SurahName2 = surahName2;
	}

	public String getAyah2() {
		return Ayah2;
	}

	public void setAyah2(String ayah2) {
		if (ayah2 == null || ayah2.length() == 0 )
			throw new IllegalArgumentException();
		this.Ayah2 = ayah2;
	}

	public int getNoOfMatchingWords() {
		return NoOfMatchingWords;
	}

	public void setNoOfMatchingWords(int noOfMatchingWords) {
		this.NoOfMatchingWords = noOfMatchingWords;
	}

	public int getPercentageOfMatchingWordsInSentence1() {
		return PercentageOfMatchingWordsInSentence1;
	}

	public void setPercentageOfMatchingWordsInSentence1(
			int percentageOfMatchingWordsInSentence1) {
		this.PercentageOfMatchingWordsInSentence1 = percentageOfMatchingWordsInSentence1;
	}

	public int getPercentageOfMatchingWordsInSentence2() {
		return PercentageOfMatchingWordsInSentence2;
	}

	public void setPercentageOfMatchingWordsInSentence2(
			int percentageOfMatchingWordsInSentence2) {
		this.PercentageOfMatchingWordsInSentence2 = percentageOfMatchingWordsInSentence2;
	}
	
	public int getConnectedness1(){
		return Connectedness1;
	}
	
	public void setConnectedness1(int connectedness1){
		this.Connectedness1 = connectedness1;
	}
	
	public int getConnectedness2(){
		return Connectedness2;
	}
	
	public void setConnectedness2(int connectedness2){
		this.Connectedness2 = connectedness2;
	}
	
	public int getDegreeOfOrderness(){
		return DegreeOfOrderness;
	}
	
	public void setDegreeOfOrderness(int degreeOfOrderness){
		this.DegreeOfOrderness = degreeOfOrderness;
	}
}