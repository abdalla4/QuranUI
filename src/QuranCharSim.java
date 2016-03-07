public class QuranCharSim {
	private String SurahName1;
	private String Ayah1;
	private String SurahName2;
	private String Ayah2;
	private int NoOfMatchingChars;
	private int PercentageOfMatchingCharsInSentence1;
	private int PercentageOfMatchingCharsInSentence2;
	
	public QuranCharSim(String SurahName1, String Ayah1, String SurahName2, String Ayah2, int NoOfMatchingChars, int PercentageOfMatchingCharsInSentence1, int PercentageOfMatchingCharsInSentence2) {
		setSurahName1(SurahName1);
		setAyah1(Ayah1);
		setSurahName2(SurahName2);
		setAyah2(Ayah2);
		setNoOfMatchingChars(NoOfMatchingChars);
		setPercentageOfMatchingCharsInSentence1(PercentageOfMatchingCharsInSentence1);
		setPercentageOfMatchingCharsInSentence2(PercentageOfMatchingCharsInSentence2);
	}
	
	@Override
	public String toString(){
		return "QuranCharSim [SurahName1=" + SurahName1 + ", Ayah1=" + Ayah1 + ", SurahName2="
				+ SurahName2 + ", Ayah2=" + Ayah2 + ", NoOfMatchingChars=" + NoOfMatchingChars + ", PercentageOfMatchingCharsInSentence1=" + PercentageOfMatchingCharsInSentence1+ ", PercentageOfMatchingCharsInSentence2=" + PercentageOfMatchingCharsInSentence2 + "]";
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

	public int getNoOfMatchingChars() {
		return NoOfMatchingChars;
	}

	public void setNoOfMatchingChars(int noOfMatchingChars) {
		NoOfMatchingChars = noOfMatchingChars;
	}

	public int getPercentageOfMatchingCharsInSentence1() {
		return PercentageOfMatchingCharsInSentence1;
	}

	public void setPercentageOfMatchingCharsInSentence1(
			int percentageOfMatchingCharsInSentence1) {
		PercentageOfMatchingCharsInSentence1 = percentageOfMatchingCharsInSentence1;
	}

	public int getPercentageOfMatchingCharsInSentence2() {
		return PercentageOfMatchingCharsInSentence2;
	}

	public void setPercentageOfMatchingCharsInSentence2(
			int percentageOfMatchingCharsInSentence2) {
		PercentageOfMatchingCharsInSentence2 = percentageOfMatchingCharsInSentence2;
	}
}