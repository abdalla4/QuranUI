public class QuranClass {
	private int AyahSerialNo;
	private String Ayah;
	private int SurahSerialNo;
	private int RubSerialNo;
	private int AyahNoWithSurah;
	private int AyahNoWithinRub;

	public QuranClass(int AyahSerialNo, String Ayah, int SurahSerialNo, int RubSerialNo, int AyahNoWithSurah, int AyahNoWithinRub) {
		setAyahSerialNo(AyahSerialNo);
		setAyah(Ayah);
		setSurahSerialNo(RubSerialNo);
		setRubSerialNo(RubSerialNo);
		setAyahNoWithSurah(AyahNoWithSurah);
		setAyahNoWithinRub(AyahNoWithinRub);
	}
	
	@Override
	public String toString(){
		return "QuranClass [AyahSerialNo=" + AyahSerialNo + ", Ayah=" + Ayah + ", SurahSerialNo="
				+ SurahSerialNo + ", RubSerialNo=" + RubSerialNo + ", AyahNoWithSurah=" + AyahNoWithSurah + ", AyahNoWithinRub=" + AyahNoWithinRub+ "]";
	}

	public int getAyahSerialNo() {
		return AyahSerialNo;
	}

	public void setAyahSerialNo(int ayahSerialNo) {
		this.AyahSerialNo = ayahSerialNo;
	}

	public String getAyah() {
		return Ayah;
	}

	public void setAyah(String ayah) {
		if (ayah == null || ayah.length() == 0 )
			throw new IllegalArgumentException();
		this.Ayah = ayah;
	}

	public int getSurahSerialNo() {
		return SurahSerialNo;
	}

	public void setSurahSerialNo(int surahSerialNo) {
		this.SurahSerialNo = surahSerialNo;
	}

	public int getRubSerialNo() {
		return RubSerialNo;
	}

	public void setRubSerialNo(int rubSerialNo) {
		this.RubSerialNo = rubSerialNo;
	}

	public int getAyahNoWithSurah() {
		return AyahNoWithSurah;
	}

	public void setAyahNoWithSurah(int ayahNoWithSurah) {
		this.AyahNoWithSurah = ayahNoWithSurah;
	}

	public int getAyahNoWithinRub() {
		return AyahNoWithinRub;
	}

	public void setAyahNoWithinRub(int ayahNoWithinRub) {
		this.AyahNoWithinRub = ayahNoWithinRub;
	}
}
