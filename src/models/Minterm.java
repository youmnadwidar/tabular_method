package models;

public class Minterm implements IMinterm {
	protected int bits;
	protected int value;
	protected boolean checked;

	

	public Minterm(int bits, int value) {
		this.bits = bits;
		this.value = value;
		this.checked = false;
	}

	public boolean isChecked() {
		return this.checked;
	}

	private double log(double a, double b) {
		return Math.log(a) / Math.log(b);
	}

	public boolean isAdjacent(int value) {
		int hammingDistance = this.value ^ value;
		double power = log(hammingDistance, 2);
		long powerRounded = Math.round(power);
		String powerRoundedString = Long
				.toString(powerRounded);
		Double powerRoundedDouble = Double
				.valueOf(powerRoundedString);
		return Double.compare(power,
				powerRoundedDouble) == 0;

	}

	public int getValue() {
		return this.value;
	}

	public int getBits() {
		return this.bits;
	}

	@Override
	public MintermReduced reduce(Minterm term) {

		if (!this.isAdjacent(term.getValue())) {
			return null;
		}
		int reducedValue = Math.min(this.value,
				term.getValue());
		int hammingDistance = this.value ^ term.getValue();

		MintermReduced reduced = new MintermReduced(
				this.bits, reducedValue, hammingDistance);
		this.checked=true;
		term.checked=true;
		return reduced;
	}
	
	public String numericalForm(){
		return String.valueOf(this.value);
	}
	
	public String toString(boolean isAlphabet){
		if(!isAlphabet){
			return numericalForm();
		}
		return alphabetString();
	}
	public String toString(){
		return alphabetString();
	}

	private String alphabetString() {
		StringBuilder sb = new StringBuilder();
		for(int i =0 ; i<this.bits;i++){
			int literal = 'A'+i;
			sb.append(Character.toChars(literal));
			if(isNegated(i)){
				sb.append("'");
			}
		}
		return sb.length() == 0 ? "1" : sb.toString();
	}

	protected boolean isNegated(int i) {
		String binaryRepresentation = Integer.toBinaryString(this.value);
		if(i < this.bits - binaryRepresentation.length()){
			return true;
		}
		return binaryRepresentation.charAt(i - ( this.bits - binaryRepresentation.length()) ) == '0';
	}

	

}
