import java.lang.reflect.*;

public class Api {

	public static void populateTarget(Object src, Object dest) {
		
		try {

			Class<? extends Object> sourceClass = src.getClass();

			Field[] fields = sourceClass.getDeclaredFields();

			Class<? extends Object> targetClass = dest.getClass();
			Field[] fieldsTarget = targetClass.getDeclaredFields();

			for (int i = 0; i < fields.length && i < fieldsTarget.length; i++) {
				fields[i].setAccessible(true);

				fieldsTarget[i].setAccessible(true);
				/*
				 * System.out.println("Field Name-->" + fields[i].getName() +
				 * "\t" + "Field Type-->" + fields[i].getType().getName() + "\t"
				 * + "Field Value-->" + fields[i].get(src));
				 * 
				 * 
				 * System.out.println("Field Name-->" +
				 * fieldsTarget[i].getName() + "\t" + "Field Type-->" +
				 * fieldsTarget[i].getType().getName() + "\t" + "Field Value-->"
				 * + fieldsTarget[i].get(dest));
				 */

				
	/* Bu if bloðunda ,fieldlarýn typelarý ayný mý kontrol ettirdim.Eðer aynýysa,
	 * Targettaki field alanýný, sourcedaki deðeriyle set ettim.Typelar ayný deðilse ,yani String -Long 
	 * gibi,o zaman eþleþmeyenler için null ata dedim.
	 */
				if (fields[i].getType().getName()
						.equals(fieldsTarget[i].getType().getName()))
					fieldsTarget[i].set(dest, fields[i].get(src));
				else
					fieldsTarget[i].set(dest, null);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}