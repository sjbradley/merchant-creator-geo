package com.cooksys.merchantcreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.cooksys.merchantcreator.model.MerchantLocation;

public class MerchantGenerator {

	private static final String[] person = { "Steve", "Tim", "Randall", "Raymond", "Sam", "Anita", "Gary", "Mike",
			"Matt" };
	private static final String[] suffix = { "corp", "mart", " LLC", "'s Shoppe", "bank" };
	private static final String[] streetName = { " Washington ", " Bruce ", " Spring ", " Netflix ", " Bunny ",
			" Raymond " };
	private static final String[] streetType = { "Dr.", "Rd.", "Ave.", "Circle", "Way", "St.", "Place" };
	private static final String[] cityPrefix = { "Timmy", "Bruce", "Spring", "Netflix", "Bunny", "Raymond" };
	private static final String[] citySuffix = { "ton", "town", "field", "lake" };
	private static final String[] state = { "AZ", "TN", "TX" };
	private static final Map<String, String[]> zipMap;
	private static final long START_ID = 1000001;
	
	private final Random random = new Random();

	static {
		zipMap = new HashMap<String, String[]>();
		
		final String[] azZips = { "85007", "85017", "85031", "85041" };
		zipMap.put("AZ", azZips);

		final String[] tnZips = { "73264", "72384", "72338", "72348" };
		zipMap.put("TN", tnZips);

		final String[] txZips = { "75211", "75007", "75137", "75229" };
		zipMap.put("TX", txZips);
	}
	
	public MerchantLocation generateMerchant(long id) {
		final String state = generateState();
		return new MerchantLocation(id, generateStoreName(), generateAddress(), generateCity(),
				state, generateZip(state), generatePhone(), generateHours(), generateLattitude(),
				generateLongitude());
	}
	
	private String generateStoreName() {
		return person[random.nextInt(person.length)] + suffix[random.nextInt(suffix.length)];
	}

	private String generateAddress() {
		return random.nextInt(50001) + streetName[random.nextInt(streetName.length)]
				+ streetType[random.nextInt(streetType.length)];
	}

	private String generateCity() {
		return cityPrefix[random.nextInt(cityPrefix.length)] + citySuffix[random.nextInt(citySuffix.length)];
	}

	private String generateState() {
		return state[random.nextInt(state.length)];
	}

	private String generateZip(final String state) {
		final String[] zips = zipMap.get(state);

		return zips[random.nextInt(zips.length)];
	}

	private String generatePhone() {
		return String.format("(%03d) %03d-%04d", (int) Math.floor(999 * random.nextDouble()),
				(int) Math.floor(999 * random.nextDouble()), (int) Math.floor(9999 * random.nextDouble()));
	}

	private String generateHours() {
		final Integer open = random.nextInt(24) + 1;
		final Integer close = random.nextInt(24) + 1;
		if (open == close) {
			return "Open 24 Hours";
		} else {
			return "Open: " + formatHour(open) + " - " + formatHour(close);
		}
	}

	private String formatHour(final int hour) {
		final StringBuilder sb = new StringBuilder();
		if (hour > 12) {
			sb.append(hour - 12);
			sb.append(":00");
			sb.append("pm");
		} else {
			sb.append(hour);
			sb.append(":00");
			sb.append("pm");
		}
		return sb.toString();
	}

	private double generateLattitude() {
		return random.nextDouble() * (-170) + 85;
	}

	private double generateLongitude() {
		return random.nextDouble() * (-360) + 180;
	}
}
